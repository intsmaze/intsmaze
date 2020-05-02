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

package com.intsmaze.lsmdb.test.base;

import com.intsmaze.lsmdb.test.helper.TestFileHelper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.IOException;

public class FileTest {

    @BeforeClass
    public static void beforeClass() throws IOException {
        TestFileHelper.createTestDirectory();
        TestFileHelper.cleanUpTestFiles();
    }

    @AfterClass
    public static void afterClass() throws IOException {
        TestFileHelper.cleanUpTestFiles();
    }

    @Before
    public void beforeTest() throws IOException {
        TestFileHelper.createTestDirectory();
    }

    @After
    public void afterTest() throws IOException {
        TestFileHelper.cleanUpTestFiles();
    }
}
