
package io.github.jhale1805.torcharrow;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;

import org.junit.*;
import static org.junit.Assert.*;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TorchArrowStackTest {

    private ServerMock server;
    private TorchArrowPlugin plugin;

    @BeforeClass
    public static void setUpClass() {
        
    }

    @Before
    public void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(TorchArrowPlugin.class);
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
        TorchArrowStack test = new TorchArrowStack(4);
        TorchArrowStack test2 = new TorchArrowStack(8);
        ItemStack arrow = new ItemStack(Material.ARROW, 4);

        assertTrue(test.isSimilar(test2));
        assertFalse(test.isSimilar(arrow));
    }

    @Test
    public void test_equals() {
        TorchArrowStack test = new TorchArrowStack(4);
        TorchArrowStack test2 = new TorchArrowStack(8);
        ItemStack arrow = new ItemStack(Material.ARROW, 4);
        
        assertTrue(test.equals(test));
        assertFalse(test.equals(test2));
        assertFalse(test.isSimilar(arrow));
    }

}