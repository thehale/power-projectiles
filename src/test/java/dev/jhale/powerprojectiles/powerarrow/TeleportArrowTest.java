/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.powerarrow;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import dev.jhale.powerprojectiles.PowerProjectilePlugin;

import org.junit.*;

import static org.mockito.Mockito.*;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;

public class TeleportArrowTest {

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
    public void test_onThisProjectileHit() {
        // Setup mocks
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        Location mockLocation = mock(Location.class);
        Player mockPlayer = mock(Player.class);
        when(mockEvent.getEntity().getLocation()).thenReturn(mockLocation);
        when(mockEvent.getEntity().getShooter()).thenReturn(mockPlayer);
        // Execute test
        new TeleportArrow().onThisProjectileHit(mockEvent);
        // Verify results
        verify(mockPlayer).teleport(mockLocation);
        verify(mockPlayer).damage(anyDouble());
    }

}