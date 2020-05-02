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

package com.intsmaze.lsmdb.test.performance.db;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.jordanwilliams.heftydb.compact.CompactionStrategies;
import com.jordanwilliams.heftydb.db.Config;
import com.jordanwilliams.heftydb.db.DB;
import com.jordanwilliams.heftydb.db.HeftyDB;
import com.intsmaze.lsmdb.test.generator.KeyValueGenerator;
import com.intsmaze.lsmdb.test.helper.PerformanceHelper;
import com.intsmaze.lsmdb.test.helper.TestFileHelper;
import com.jordanwilliams.heftydb.util.ByteBuffers;

import java.nio.ByteBuffer;

public class WritePerformance {

    private static final int RECORD_COUNT = 1 * 1000000;

    public static void main(String[] args) throws Exception {
        TestFileHelper.createTestDirectory();
        TestFileHelper.cleanUpTestFiles();
        KeyValueGenerator keyValueGenerator = new KeyValueGenerator();
        ByteBuffer testValueBuffer = keyValueGenerator.testValue(100);

        Config config = new Config.Builder().directory(TestFileHelper.TEMP_PATH).memoryTableSize(16384000)
                .tableCacheSize(512000000).indexCacheSize(64000000).tableBlockSize(16384).compactionStrategy
                        (CompactionStrategies.SIZE_TIERED_COMPACTION_STRATEGY).indexBlockSize(32768).maxWriteRate
                        (Integer.MAX_VALUE).build();

        //Write
        DB db = HeftyDB.open(config);

        MetricRegistry metrics = new MetricRegistry();
        ConsoleReporter reporter = PerformanceHelper.consoleReporter(metrics);
        Timer writeTimer = metrics.timer("writes");

        for (int i = 0; i < RECORD_COUNT; i++) {
            Timer.Context watch = writeTimer.time();
            db.put(ByteBuffers.fromString(i + ""), testValueBuffer.slice());
            watch.stop();
        }

        reporter.report();
        db.close();
    }
}
