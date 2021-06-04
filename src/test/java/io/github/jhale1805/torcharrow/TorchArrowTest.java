
package io.github.jhale1805.torcharrow;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import io.github.jhale1805.PowerProjectilePlugin;
import io.github.jhale1805.powerarrow.TorchArrow;

import org.junit.*;
import static org.junit.Assert.*;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TorchArrowTest {

    private ServerMock server;
    private PowerProjectilePlugin plugin;

    @BeforeClass
    public static void setUpClass() {
        
    }

    @Before
    public void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(PowerProjectilePlugin.class);
    }

    @After
    public void tearDown() {
        MockBukkit.unmock();
    }

    @AfterClass
    public static void tearDownClass() {
        
    }

    @Test 
    public void test_isSimilar() {
        TorchArrow test = new TorchArrow(4);
        TorchArrow test2 = new TorchArrow(8);
        ItemStack arrow = new ItemStack(Material.ARROW, 4);

        assertTrue(test.isSimilar(test2));
        assertFalse(test.isSimilar(arrow));
    }

    @Test
    public void test_equals() {
        TorchArrow test = new TorchArrow(4);
        TorchArrow test2 = new TorchArrow(8);
        ItemStack arrow = new ItemStack(Material.ARROW, 4);
        
        assertTrue(test.equals(test));
        assertFalse(test.equals(test2));
        assertFalse(test.isSimilar(arrow));
    }

}