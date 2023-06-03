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

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.EntityShootBowEvent;

public class SniperArrowTest {

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
    public void test_onThisProjectileShot_noPower() {
        // Set up mocks
        EntityShootBowEvent mockEvent = mock(EntityShootBowEvent.class, RETURNS_DEEP_STUBS);
        Arrow mockArrow = mock(Arrow.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getBow().containsEnchantment(Enchantment.ARROW_DAMAGE)).thenReturn(false);
        when(mockEvent.getProjectile()).thenReturn(mockArrow);
        // Execute test
        new SniperArrow().onThisProjectileShot(mockEvent);
        // Verify results
        verify(mockArrow, never()).setGravity(anyBoolean());
        verify(mockArrow.getVelocity(), never()).multiply(anyDouble());
    }

    // @Test
    // public void test_onThisProjectileShot_withPower() {
    //     // Set up mocks
    //     EntityShootBowEvent mockEvent = mock(EntityShootBowEvent.class, RETURNS_DEEP_STUBS);
    //     Arrow mockArrow = mock(Arrow.class, RETURNS_DEEP_STUBS);
    //     when(mockEvent.getBow().containsEnchantment(Enchantment.ARROW_DAMAGE)).thenReturn(true);
    //     when(mockEvent.getProjectile()).thenReturn(mockArrow);
    //     // Execute test
    //     new SniperArrow().onThisProjectileShot(mockEvent);
    //     // Verify results
    //     verify(mockArrow).setGravity(false);
    //     verify(mockArrow.getVelocity()).multiply(anyDouble());
    // }

}