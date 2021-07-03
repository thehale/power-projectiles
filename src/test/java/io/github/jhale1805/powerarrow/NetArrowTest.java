
package io.github.jhale1805.powerarrow;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import io.github.jhale1805.PowerProjectilePlugin;
import io.github.jhale1805.util.Utilities;

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
        when(mockEvent.getHitBlock()).thenReturn(null);
        // Execute test
        try (MockedStatic<Utilities> mockUtils = mockStatic(Utilities.class)) {
            mockUtils.when(() -> Utilities.getImpactLocation(any(ProjectileHitEvent.class)))
                .thenReturn(mock(Location.class));
            new NetArrow().onThisProjectileHit(mockEvent);
            // Verify results
            mockUtils.verify(() -> Utilities.replaceAirWith(
                eq(Material.COBWEB), any(Location.class), anyDouble()));
        }
    }

    @Test
    public void test_onThisProjectileHit_noHitLocation() {
        // Setup mocks
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getHitBlock()).thenReturn(null);
        // Execute test
        try (MockedStatic<Utilities> mockUtils = mockStatic(Utilities.class)) {
            mockUtils.when(() -> Utilities.getImpactLocation(any(ProjectileHitEvent.class)))
                .thenReturn(null);
            new NetArrow().onThisProjectileHit(mockEvent);
            // Verify results
            mockUtils.verify(() -> Utilities.replaceAirWith(
                any(Material.class), any(Location.class), anyDouble()),
                never());
        }
    }

}