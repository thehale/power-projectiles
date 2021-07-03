package io.github.jhale1805.powerarrow;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import io.github.jhale1805.PowerProjectilePlugin;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.bukkit.inventory.Recipe;

/**
 * Tests the implementations of PowerArrow's abstract methods of all
 * registered arrows to make sure they follow the specifications in
 * the javadocs.
 */
@RunWith(Parameterized.class)
public class RegisteredArrowsTest {
    
    private static ServerMock mockServer;

    static {
        mockServer = spy(new ServerMock());  // Fill in ServerMock's missing methods.
        MockBukkit.mock(mockServer);
        MockBukkit.load(PowerProjectilePlugin.class);
    }

    @Parameters(name="{0}")
    public static Object[] data() {
        return PowerArrowRegistry.getPowerArrows();
    }

    @Parameter
    public PowerArrow arrowUnderTest;

    @BeforeClass
    public static void setUpClass() {
        
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
    public void test_getName() {
        String name = arrowUnderTest.getName();
        assertTrue("PowerArrow names must be snake_case (no spaces).",
            !name.contains(" "));
        assertTrue("PowerArrow names must be snake_case (no capital letters).",
            name.equals(name.toLowerCase()));
    }
    
    @Test
    public void test_getUsageInstructions() {
        String[] instructions = arrowUnderTest.getUsageInstructions();
        assertTrue("Instructions cannot be > 3 lines.",
            instructions.length <= 3);
        for (String line : instructions) {
            assertTrue("Instruction lines cannot be > 20 characters.",
                line.length() <= 20);
        }
    }

    @Test
    public void test_getRecipe() {
        Recipe recipe = arrowUnderTest.getRecipe();
        assertNotNull("A PowerArrow's recipe cannot be `null`.", recipe);
        assertTrue("A PowerArrow's recipe cannot yield alternate items.",
            recipe.getResult().isSimilar(arrowUnderTest));
    }

}