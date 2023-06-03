/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.util;

import org.junit.*;

import static org.junit.Assert.*;

public class IncrementorTest {

    @BeforeClass
    public static void setUpClass() {
        
    }

    @Before
    public void setUp() {
        
    }

    @After
    public void tearDown() {
        
    }

    @AfterClass
    public static void tearDownClass() {
        
    }

    @Test
    public void test_ascending() {
        Incrementor ascTest = new Incrementor(1, 5);
        int count = 0;
        for(Integer i : ascTest)
            count++;
        assertEquals(5, count);
    }

    @Test
    public void test_descending() {
        Incrementor descTest = new Incrementor(5, 1);
        int count = 0;
        for(Integer i : descTest)
            count++;
        assertEquals(5, count);
    }

    @Test
    public void test_positiveToNegative() {
        Incrementor descTest = new Incrementor(1, -1);
        int count = 0;
        for(Integer i : descTest)
            count++;
        assertEquals(3, count);
    }

    @Test
    public void test_negativeToPositive() {
        Incrementor descTest = new Incrementor(-1, 1);
        int count = 0;
        for(Integer i : descTest)
            count++;
        assertEquals(3, count);
    }

    

}