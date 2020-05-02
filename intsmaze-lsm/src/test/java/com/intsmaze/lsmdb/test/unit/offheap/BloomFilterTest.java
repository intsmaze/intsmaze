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

package com.intsmaze.lsmdb.test.unit.offheap;

import com.intsmaze.lsmdb.test.generator.KeyValueGenerator;
import com.jordanwilliams.heftydb.data.Key;
import com.jordanwilliams.heftydb.offheap.BloomFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class BloomFilterTest {

    @Test
    public void readWriteTest() {
        Set<Key> testKeys = testKeys();
        BloomFilter.Builder testFilterBuilder = new BloomFilter.Builder(1000, 0.01);

        for (Key key : testKeys) {
            testFilterBuilder.put(key);
        }

        BloomFilter testFilter = testFilterBuilder.build();

        for (Key key : testKeys) {
            Assert.assertTrue("Key is in filter", testFilter.mightContain(key));
        }
    }

    private static Set<Key> testKeys() {
        KeyValueGenerator generator = new KeyValueGenerator();
        Set<Key> testDataSet = new HashSet<Key>();

        for (int i = 0; i < 1000; i++) {
            testDataSet.add(new Key(generator.testKey(255, 0), i));
        }

        return testDataSet;
    }
}
