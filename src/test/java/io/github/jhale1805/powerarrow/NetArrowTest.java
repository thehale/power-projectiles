
/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.github.jhale1805.powerarrow;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import io.github.jhale1805.PowerProjectilePlugin;
import io.github.jhale1805.util.BlockTools;
import io.github.jhale1805.util.EventTools;

import org.junit.*;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.entity.ProjectileHitEvent;

public class NetArrowTest {

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
        try (MockedStatic<BlockTools> mockUtils = mockStatic(BlockTools.class)) {
            new NetArrow().onThisProjectileHit(mockEvent);
            // Verify results
            mockUtils.verify(() -> BlockTools.replaceAirWith(
                eq(Material.COBWEB), any(Location.class), anyDouble()));
        }
    }

    @Test
    public void test_onThisProjectileHit_noHitLocation() {
        // Setup mocks
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getHitBlock()).thenReturn(null);
        when(mockEvent.getHitEntity()).thenReturn(null);
        // Execute test
        try (MockedStatic<BlockTools> mockUtils = mockStatic(BlockTools.class)) {
            new NetArrow().onThisProjectileHit(mockEvent);
            // Verify results
            mockUtils.verify(() -> BlockTools.replaceAirWith(
                any(Material.class), any(Location.class), anyDouble()),
                never());
        }
    }

}