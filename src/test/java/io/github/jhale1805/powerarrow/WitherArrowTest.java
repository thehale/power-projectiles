
package io.github.jhale1805.powerarrow;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import io.github.jhale1805.PowerProjectilePlugin;

import org.junit.*;

import static org.mockito.Mockito.*;

import org.bukkit.entity.WitherSkull;
import org.bukkit.event.entity.EntityShootBowEvent;

public class WitherArrowTest {

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
    public void test_onThisProjectileShot() {
        // Setup mocks
        EntityShootBowEvent mockEvent = mock(EntityShootBowEvent.class, RETURNS_DEEP_STUBS);
        // Execute test
        new WitherArrow().onThisProjectileShot(mockEvent);
        // Verify results
        verify(mockEvent.getEntity()).launchProjectile(WitherSkull.class);
        verify(mockEvent).setProjectile(any(WitherSkull.class));
        verify(mockEvent, never()).setCancelled(anyBoolean());
    }

}