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

package com.intsmaze.lsmdb.test.integration;

import com.intsmaze.lsmdb.test.generator.TupleGenerator;
import com.intsmaze.lsmdb.test.helper.CompareHelper;
import com.intsmaze.lsmdb.test.base.ParameterizedIntegrationTest;
import com.jordanwilliams.heftydb.data.Tuple;
import com.jordanwilliams.heftydb.db.Config;
import com.jordanwilliams.heftydb.db.HeftyDB;
import com.jordanwilliams.heftydb.db.Record;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ReadWriteTest extends ParameterizedIntegrationTest {

    public ReadWriteTest(List<Tuple> tuples, Config config) throws IOException {
        super(tuples, config);
    }

    @Test
    public void readWriteTest() throws Exception {
        writeRecords();

        db = HeftyDB.open(config);

        for (Tuple tuple : TupleGenerator.latest(tuples, Long.MAX_VALUE)) {
            Record record = db.get(tuple.key().data());
            CompareHelper.compareKeyValue(tuple, record);
        }

        db.close();
    }
}
