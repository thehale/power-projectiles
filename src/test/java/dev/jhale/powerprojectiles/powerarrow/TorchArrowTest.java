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
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.event.entity.ProjectileHitEvent;

public class TorchArrowTest {

    private static ServerMock mockServer;

    @BeforeClass
    public static void setUpClass() {
        mockServer = spy(new ServerMock());  // Fill in ServerMock's missing methods.
        MockBukkit.mock(mockServer);
        MockBukkit.load(PowerProjectilePlugin.class);
    }

    @AfterClass
    public static void tearDownClass() {
        MockBukkit.unmock();
    }

    @Test
    public void test_onThisProjectileHit_noFlame() {
        // Setup mocks
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getEntity().getFireTicks()).thenReturn(0);
        // Execute test
        new TorchArrow().onThisProjectileHit(mockEvent);
        // Verify results
        verify(mockEvent.getEntity().getWorld(),
            never()).dropItemNaturally(any(Location.class), any(TorchArrow.class));
        verify(mockEvent.getHitBlock().getRelative(any(BlockFace.class)),
            never()).setBlockData(any(BlockData.class));
    }

    @Test
    public void test_onThisProjectileHit_noHitBlock() {
        // Setup mocks
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getEntity().getFireTicks()).thenReturn(10);
        when(mockEvent.getHitBlock()).thenReturn(null);
        // Execute test
        new TorchArrow().onThisProjectileHit(mockEvent);
        // Verify results
        verify(mockEvent.getEntity().getWorld(),
            never()).dropItemNaturally(any(Location.class), any(TorchArrow.class));
        // Deliberately omitted because `getHitBlock()` returns a null.
        // verify(mockEvent.getHitBlock().getRelative(any(BlockFace.class)),
        //     never()).setBlockData(any(BlockData.class));
    }

    @Test
    public void test_onThisProjectileHit_noHitBlockFace() {
        // Setup mocks
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getEntity().getFireTicks()).thenReturn(10);
        when(mockEvent.getHitBlockFace()).thenReturn(null);
        // Execute test
        new TorchArrow().onThisProjectileHit(mockEvent);
        // Verify results
        verify(mockEvent.getEntity().getWorld(),
            never()).dropItemNaturally(any(Location.class), any(TorchArrow.class));
        verify(mockEvent.getHitBlock().getRelative(any(BlockFace.class)),
            never()).setBlockData(any(BlockData.class));
    }

    @Test
    public void test_onThisProjectileHit_hitBlockBottom() {
        // Setup mocks
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getEntity().getFireTicks()).thenReturn(10);
        when(mockEvent.getHitBlockFace()).thenReturn(BlockFace.DOWN);
        // Execute test
        new TorchArrow().onThisProjectileHit(mockEvent);
        // Verify results
        verify(mockEvent.getEntity().getWorld(),
            times(1)).dropItemNaturally(any(Location.class), any(TorchArrow.class));
        verify(mockEvent.getHitBlock().getRelative(any(BlockFace.class)),
            never()).setBlockData(any(BlockData.class));
    }

    @Test
    public void test_onThisProjectileHit_placeTorch_UP() {
        // Setup mocks
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getEntity().getFireTicks()).thenReturn(10);
        when(mockEvent.getHitBlockFace()).thenReturn(BlockFace.UP);
        BlockData mockTorch = mock(BlockData.class);
        doReturn(mockTorch).when(mockServer).createBlockData(Material.TORCH);
        // Execute test
        new TorchArrow().onThisProjectileHit(mockEvent);
        // Verify results
        verify(mockEvent.getEntity().getWorld(),
            never()).dropItemNaturally(any(Location.class), any(TorchArrow.class));
        verify(mockEvent.getHitBlock().getRelative(BlockFace.UP),
            times(1)).setBlockData(mockTorch);
    }

    @Test
    public void test_onThisProjectileHit_placeTorch_SIDE() {
        // Setup mocks
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getEntity().getFireTicks()).thenReturn(10);
        when(mockEvent.getHitBlockFace()).thenReturn(BlockFace.NORTH);
        BlockData mockTorch = mock(BlockData.class, withSettings().extraInterfaces(Directional.class));
        doReturn(mockTorch).when(mockServer).createBlockData(Material.WALL_TORCH);
        // Execute test
        new TorchArrow().onThisProjectileHit(mockEvent);
        // Verify results
        verify(mockEvent.getEntity().getWorld(),
            never()).dropItemNaturally(any(Location.class), any(TorchArrow.class));
        verify(mockEvent.getHitBlock().getRelative(BlockFace.NORTH),
            times(1)).setBlockData(mockTorch);
    }

}