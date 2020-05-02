/*
 * Copyright (c) 2014. Jordan Williams
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intsmaze.lsmdb.test.endurance;

import com.intsmaze.lsm.db.db.DB;
import com.intsmaze.lsm.db.db.LsmDB;
import com.intsmaze.lsm.db.db.Record;
import com.intsmaze.lsm.db.db.Snapshot;
import com.intsmaze.lsmdb.test.helper.TestFileHelper;
import com.intsmaze.lsmdb.test.generator.ConfigGenerator;
import com.intsmaze.lsmdb.test.generator.KeyValueGenerator;
import com.intsmaze.lsmdb.test.helper.StopWatch;
import com.intsmaze.lsm.db.util.ByteBuffers;
import com.intsmaze.lsm.db.util.CloseableIterator;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class EnduranceTest {

    private static final int THREAD_COUNT = 16;
    private static final int RUNTIME_MINUTES = 60;
    private static final int LOAD_LEVEL = 2;
    private static final int VALUE_SIZE = 1000;
    private static final int THREAD_SLEEP_TIME = 10;
    private static final int KEY_DUPLICATION_PROBABILITY = 20;

    private static final Random random = new Random(System.nanoTime());

    private static final AtomicLong maxSnapshotId = new AtomicLong();
    private static final AtomicLong maxKey = new AtomicLong();
    private static final AtomicLong maxVisibleKey = new AtomicLong();

    private static long generateKey(){
        int next = random.nextInt(100);

        if (next <= KEY_DUPLICATION_PROBABILITY){
            return (long) (random.nextDouble() * (maxVisibleKey.get()));
        }

        return maxSnapshotId.incrementAndGet();
    }

    public static void main(String[] args) throws Exception {
        TestFileHelper.createTestDirectory();
        TestFileHelper.cleanUpTestFiles();

        final AtomicBoolean finished = new AtomicBoolean();

        final KeyValueGenerator keyValueGenerator = new KeyValueGenerator();
        final Random random = new Random(System.nanoTime());

        final ExecutorService writeExecutor = Executors.newFixedThreadPool(THREAD_COUNT);
        final ExecutorService readExecutor = Executors.newFixedThreadPool(THREAD_COUNT);
        final ExecutorService scanExecutor = Executors.newFixedThreadPool(THREAD_COUNT);

        final DB db = LsmDB.open(ConfigGenerator.enduranceConfig());

        for (int i = 0; i < THREAD_COUNT; i++) {
            writeExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            if (finished.get()) {
                                return;
                            }

                            for (int i = 0; i < LOAD_LEVEL; i++) {
                                long generatedKey = generateKey();
                                String nextKey = Long.toString(generatedKey);
                                Snapshot maxSnapshot = db.put(ByteBuffers.fromString(nextKey),
                                        keyValueGenerator.testValue(VALUE_SIZE));

                                long currentMaxKey = maxVisibleKey.get();

                                if (generatedKey > currentMaxKey){
                                    maxVisibleKey.compareAndSet(currentMaxKey, generatedKey);
                                }

                                long currentMaxSnapshotId = maxSnapshotId.get();

                                if (maxSnapshot.id() > currentMaxSnapshotId) {
                                    maxSnapshotId.compareAndSet(currentMaxSnapshotId, maxSnapshot.id());
                                }
                            }

                            Thread.sleep(THREAD_SLEEP_TIME);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            //Make sure some keys have been written
            while (maxVisibleKey.get() < 1000){
                Thread.sleep(10);
            }

            readExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            if (finished.get()) {
                                return;
                            }

                            for (int i = 0; i < LOAD_LEVEL; i++) {
                                long randomKey = (long) (random.nextDouble() * (maxVisibleKey.get()));
                                String nextKey = Long.toString(randomKey);
                                db.get(ByteBuffers.fromString(nextKey));
                            }

                            Thread.sleep(THREAD_SLEEP_TIME);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            scanExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        long randomKey = (long) (random.nextDouble() * (maxVisibleKey.get()));
                        String nextKey = Long.toString(randomKey);
                        CloseableIterator<Record> scanIterator = db.ascendingIterator(ByteBuffers.fromString(nextKey)
                                , new Snapshot(maxSnapshotId.get()));
                        long maxScanSnapshotId = 0;
                        int iteratorCount = 0;

                        while (true) {
                            if (finished.get()) {
                                return;
                            }

                            for (int i = 0; i < LOAD_LEVEL; i++) {
                                if (scanIterator.hasNext() && iteratorCount < 10) {
                                    Record record = scanIterator.next();
                                    maxScanSnapshotId = Math.max(maxScanSnapshotId, record.snapshot().id());
                                    iteratorCount++;
                                } else {
                                    scanIterator.close();
                                    iteratorCount = 0;
                                    randomKey = (long) (random.nextDouble() * (maxVisibleKey.get()));
                                    nextKey = Long.toString(randomKey);
                                    scanIterator = db.ascendingIterator(ByteBuffers.fromString(nextKey),
                                            new Snapshot(maxSnapshotId.get()));
                                }
                            }

                            Thread.sleep(THREAD_SLEEP_TIME);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        StopWatch watch = StopWatch.start();

        while (watch.elapsedMinutes() < RUNTIME_MINUTES) {
            Thread.sleep(1000);
        }

        finished.set(true);

        writeExecutor.shutdown();
        readExecutor.shutdown();
        scanExecutor.shutdown();

        writeExecutor.awaitTermination(30, TimeUnit.SECONDS);
        readExecutor.awaitTermination(30, TimeUnit.SECONDS);
        scanExecutor.awaitTermination(30, TimeUnit.SECONDS);

        db.close();

        TestFileHelper.cleanUpTestFiles();
    }
}
