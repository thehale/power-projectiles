/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.powerarrow;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import dev.jhale.powerprojectiles.PowerProjectilePlugin;
import dev.jhale.powerprojectiles.util.LocationTools;

import org.junit.*;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;

public class SwapArrowTest {

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
    public void test_onThisProjectileHit_hitLivingEntity() {
        // Setup mocks
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        Player mockPlayer = mock(Player.class);
        LivingEntity mockHitEntity = mock(LivingEntity.class);
        when(mockEvent.getEntity().getShooter()).thenReturn(mockPlayer);
        when(mockEvent.getHitEntity()).thenReturn(mockHitEntity);
        try (MockedStatic<LocationTools> mockUtils = mockStatic(LocationTools.class)) {
            // Execute test
            new SwapArrow().onThisProjectileHit(mockEvent);
            // Verify results
            mockUtils.verify(() -> LocationTools.swapEntities(mockPlayer, mockHitEntity));
        }
        verify(mockPlayer).damage(anyDouble());
        verify(mockHitEntity).damage(anyDouble());
    }

    @Test
    public void test_onThisProjectileHit_hitNonLivingEntity() {
        // Setup mocks
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        Player mockPlayer = mock(Player.class);
        Entity mockHitEntity = mock(Entity.class);
        when(mockEvent.getHitEntity()).thenReturn(mockHitEntity);
        when(mockEvent.getEntity().getShooter()).thenReturn(mockPlayer);
        try (MockedStatic<LocationTools> mockUtils = mockStatic(LocationTools.class)) {
            // Execute test
            new SwapArrow().onThisProjectileHit(mockEvent);
            // Verify results
            mockUtils.verify(() -> 
            LocationTools.swapEntities(
                    any(Entity.class),
                    any(Entity.class)
                ), never());
        }
        verify(mockPlayer, never()).damage(anyDouble());
    }

}