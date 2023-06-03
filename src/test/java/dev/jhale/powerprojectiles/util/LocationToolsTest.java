/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.util;

import org.junit.*;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.bukkit.Location;
import org.bukkit.World;

public class LocationToolsTest {

    private World mockWorld = mock(World.class);
    private String MOCK_WORLD_NAME = "Test World";

    @BeforeClass
    public static void setUpClass() {
        
    }

    @Before
    public void setUp() {
        when(mockWorld.getName()).thenReturn(MOCK_WORLD_NAME);
    }

    @After
    public void tearDown() {
        
    }

    @AfterClass
    public static void tearDownClass() {
        
    }

    @Test
    public void test_stringify() {
        Location testLoc = new Location(mockWorld, 0, 0, 0);
        assertEquals("[" + MOCK_WORLD_NAME + "](0.0, 0.0, 0.0)", LocationTools.stringify(testLoc));
    }

    @Test
    public void test_blockCoordsOf() {
        Location baseLoc = new Location(mockWorld, 0.5, 0.5, 0.5);
        Location blockified = LocationTools.blockCoordsOf(baseLoc);
        // Ensure the original Location is untouched
        assertEquals(0.5, baseLoc.getX(), 0.001);
        assertEquals(0.5, baseLoc.getY(), 0.001);
        assertEquals(0.5, baseLoc.getZ(), 0.001);
        // Ensure the new Location is raw block coordinates.
        assertEquals(0, blockified.getX(), 0.001);
        assertEquals(0, blockified.getY(), 0.001);
        assertEquals(0, blockified.getZ(), 0.001);
    }

    @Test
    public void test_addImmutable() {
        Location baseLoc = new Location(mockWorld, 0.5, 0.5, 0.5);
        Location blockified = LocationTools.addImmutable(baseLoc, 1, 1, 1);
        // Ensure the original Location is untouched
        assertEquals(0.5, baseLoc.getX(), 0.001);
        assertEquals(0.5, baseLoc.getY(), 0.001);
        assertEquals(0.5, baseLoc.getZ(), 0.001);
        // Ensure the new Location is raw block coordinates.
        assertEquals(1.5, blockified.getX(), 0.001);
        assertEquals(1.5, blockified.getY(), 0.001);
        assertEquals(1.5, blockified.getZ(), 0.001);
    }

}