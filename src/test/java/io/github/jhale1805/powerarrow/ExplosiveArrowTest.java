
package io.github.jhale1805.powerarrow;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import io.github.jhale1805.PowerProjectilePlugin;
import io.github.jhale1805.util.EventTools;

import org.junit.*;

import static org.mockito.Mockito.*;

import org.mockito.MockedStatic;
import org.bukkit.Location;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ExplosiveArrowTest {

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
    public void test_onThisProjectileHit_noFlame() {
        // Setup mocks
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getEntity().getFireTicks()).thenReturn(0);
        // Execute test
        new ExplosiveArrow().onThisProjectileHit(mockEvent);
        // Verify that none of the createExplostion methods were called: https://stackoverflow.com/a/46038313/14765128
        verifyNoInteractions(mockEvent.getEntity().getWorld());
    }

    @Test
    public void test_onThisProjectileHit_noHitLocation() {
        // Setup mocks
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getEntity().getFireTicks()).thenReturn(10);
        // Execute test
        try (MockedStatic<EventTools> mockUtils = mockStatic(EventTools.class)) {
            mockUtils.when(() -> EventTools.getImpactLocation(any(ProjectileHitEvent.class)))
                .thenReturn(null);
            new ExplosiveArrow().onThisProjectileHit(mockEvent);
            // Verify that none of the createExplostion methods were called: https://stackoverflow.com/a/46038313/14765128
            verifyNoInteractions(mockEvent.getEntity().getWorld());
        }
    }
    
    @Test
    public void test_onThisProjectileHit_hitLocation() {
        // Setup mocks
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        Projectile mockArrow = mockEvent.getEntity();
        Location mockLocation = mock(Location.class);
        when(mockEvent.getEntity().getFireTicks()).thenReturn(10);
        // Execute test
        try (MockedStatic<EventTools> mockUtils = mockStatic(EventTools.class)) {
            mockUtils.when(() -> EventTools.getImpactLocation(any(ProjectileHitEvent.class)))
                .thenReturn(mockLocation);
            new ExplosiveArrow().onThisProjectileHit(mockEvent);
            // Verify results
            verify(mockEvent.getEntity().getWorld()).createExplosion(
                eq(mockLocation), anyFloat(), anyBoolean(), anyBoolean(), eq(mockArrow));
        }
    }

}