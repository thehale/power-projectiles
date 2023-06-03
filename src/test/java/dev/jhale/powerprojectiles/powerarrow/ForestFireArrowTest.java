
/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.powerarrow;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import dev.jhale.powerprojectiles.PowerProjectilePlugin;
import dev.jhale.powerprojectiles.util.BlockTools;

import org.junit.*;

import static org.mockito.Mockito.*;
import org.mockito.MockedStatic;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ForestFireArrowTest {

    private static ServerMock mockServer;

    @BeforeClass
    public static void setUpClass() {
        mockServer = spy(new ServerMock());  // Fill in ServerMock's missing methods.
        MockBukkit.mock(mockServer);
        MockBukkit.load(PowerProjectilePlugin.class);
    }

    @Before
    public void setUp() {
        
    }

    @After
    public void tearDown() {
        
    }

    @AfterClass
    public static void tearDownClass() {
        MockBukkit.unmock();
    }

    @Test
    public void test_onThisProjectileHit_hitLocation() {
        // Setup mocks
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        Location mockHitLocation = mock(Location.class);
        when(mockEvent.getHitBlock()).thenReturn(null);
        when(mockEvent.getHitEntity().getLocation()).thenReturn(mockHitLocation);
        // Execute test
        try (MockedStatic<BlockTools> mockBlockTools = mockStatic(BlockTools.class)) {
            new ForestFireArrow().onThisProjectileHit(mockEvent);
            // Verify results
            mockBlockTools.verify(() -> BlockTools.replaceAirWith(
                eq(Material.FIRE), any(Location.class), anyDouble()));
        }
    }

    @Test
    public void test_onThisProjectileHit_noHitLocation() {
        // Setup mocks
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getHitBlock()).thenReturn(null);
        when(mockEvent.getHitEntity()).thenReturn(null);
        // Execute test
        try (MockedStatic<BlockTools> mockBlockTools = mockStatic(BlockTools.class)) {
            new ForestFireArrow().onThisProjectileHit(mockEvent);
            // Verify results
            mockBlockTools.verify(() -> BlockTools.replaceAirWith(
                any(Material.class), any(Location.class), anyDouble()),
                never());
        }
    }

}