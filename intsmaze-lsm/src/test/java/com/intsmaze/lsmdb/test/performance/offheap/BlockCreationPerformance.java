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

package com.intsmaze.lsmdb.test.performance.offheap;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.jordanwilliams.heftydb.data.Key;
import com.jordanwilliams.heftydb.index.IndexBlock;
import com.jordanwilliams.heftydb.index.IndexRecord;
import com.jordanwilliams.heftydb.offheap.SortedByteMap;
import com.jordanwilliams.heftydb.offheap.MemoryPointer;
import com.intsmaze.lsmdb.test.generator.KeyValueGenerator;
import com.intsmaze.lsmdb.test.helper.PerformanceHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockCreationPerformance {

    public static void main(String[] args) {
        MetricRegistry metrics = new MetricRegistry();
        ConsoleReporter reporter = PerformanceHelper.consoleReporter(metrics);
        Timer timer = metrics.timer("blockCreationTime");

        KeyValueGenerator generator = new KeyValueGenerator();
        List<Key> keys = new ArrayList<Key>();

        for (int i = 0; i < 64000; i++) {
            keys.add(new Key(generator.testKey(32, 0), i));
        }

        Collections.sort(keys);

        IndexBlock.Builder blockBuilder = new IndexBlock.Builder();

        for (Key key : keys) {
            blockBuilder.addRecord(new IndexRecord(key, 0, 128));
        }

        IndexBlock block = blockBuilder.build();
        MemoryPointer blockPointer = block.memory();

        int iterations = 10000000;

        for (int i = 0; i < iterations; i++) {
            Timer.Context watch = timer.time();
            block = new IndexBlock(new SortedByteMap(blockPointer));
            watch.stop();
        }

        reporter.report();
    }

}
