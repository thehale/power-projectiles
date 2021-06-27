package io.github.jhale1805.powerarrow;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import io.github.jhale1805.PowerProjectilePlugin;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.MetadataValue;

public class PowerArrowTest {

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
    public void test_getRecipeKey() {
        TorchArrow mockArrow = spy(new TorchArrow());
        when(mockArrow.getName()).thenReturn("test_arrow").getMock();
        
        NamespacedKey key = mockArrow.getRecipeKey();

        assertEquals("recipe/test_arrow", key.getKey());
    }

    @Test
    public void test_getItemKey() {
        TorchArrow mockArrow = spy(new TorchArrow());
        when(mockArrow.getName()).thenReturn("test_arrow").getMock();
        
        NamespacedKey key = mockArrow.getItemKey();

        assertEquals("test_arrow", key.getKey());
    }

    @Test
    public void test_getItemMeta() {
        TorchArrow mockArrow = spy(new TorchArrow());
        when(mockArrow.getName()).thenReturn("test_arrow").getMock();
        when(mockArrow.getUsageInstructions()).thenReturn(new String[]{"Test Instructions"});
        
        ItemMeta metadata = mockArrow.getItemMeta();

        assertTrue(metadata.getDisplayName().endsWith("Test Arrow"));
        assertTrue(metadata.getLore().contains("Test Instructions"));
    }

    @Test
    public void test_getEntityMetadata() {
        TorchArrow mockArrow = spy(new TorchArrow());
        when(mockArrow.getName()).thenReturn("test_arrow");
        
        MetadataValue metadata = mockArrow.getEntityMetadata();

        assertEquals("test_arrow", metadata.value());
    }

    @Test
    public void test_onEntityShootBow_unapplicableEvent() {
        TorchArrow mockArrow = spy(new TorchArrow());
        ItemMeta otherMeta = new NetArrow().getItemMeta();
        EntityShootBowEvent mockEvent = mock(EntityShootBowEvent.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getConsumable().getItemMeta()).thenReturn(otherMeta);
        
        mockArrow.onEntityShootBow(mockEvent);

        verify(mockArrow, never()).onThisProjectileShot(any(EntityShootBowEvent.class));
    }

    @Test
    public void test_onEntityShootBow_applicableEvent() {
        TorchArrow mockArrow = spy(new TorchArrow());
        ItemMeta sameMeta = mockArrow.getItemMeta();
        MetadataValue sameEntityMeta = mockArrow.getEntityMetadata();
        EntityShootBowEvent mockEvent = mock(EntityShootBowEvent.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getConsumable().getItemMeta()).thenReturn(sameMeta);
        
        mockArrow.onEntityShootBow(mockEvent);

        verify(mockEvent, times(1)).setConsumeItem(true);
        verify(mockEvent.getProjectile()).setMetadata(eq("effect"), 
            argThat(entityMeta -> entityMeta.asString().equals(sameEntityMeta.asString()))
        );
        verify(mockArrow).onThisProjectileShot(any(EntityShootBowEvent.class));
    }

    @Test
    public void test_onThisProjectileHit_unapplicableEvent() {
        TorchArrow mockArrow = spy(new TorchArrow());
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        doReturn(false).when(mockArrow).isCorrespondingArrowOf(any(Entity.class));
        
        mockArrow.onProjectileHit(mockEvent);

        verify(mockArrow, never()).onThisProjectileHit(any(ProjectileHitEvent.class));
        verify(mockEvent.getEntity(), never()).remove();
    }

    @Test
    public void test_onThisProjectileHit_applicableEvent() {
        TorchArrow mockArrow = spy(new TorchArrow());
        ProjectileHitEvent mockEvent = mock(ProjectileHitEvent.class, RETURNS_DEEP_STUBS);
        doReturn(true).when(mockArrow).isCorrespondingArrowOf(any(Entity.class));
        
        mockArrow.onProjectileHit(mockEvent);

        verify(mockArrow).onThisProjectileHit(mockEvent);
        verify(mockEvent.getEntity()).remove();
    }

    @Test
    public void test_onPlayerAdvancementDone_unapplicableEvent(){
        TorchArrow mockArrow = spy(new TorchArrow());
        Advancement mockEnchanter = mock(Advancement.class);
        doReturn(mockEnchanter).when(mockServer).getAdvancement(any(NamespacedKey.class));
        PlayerAdvancementDoneEvent mockEvent = mock(PlayerAdvancementDoneEvent.class, RETURNS_DEEP_STUBS);
        Advancement mockOtherAdvancement = mock(Advancement.class);
        when(mockEvent.getAdvancement()).thenReturn(mockOtherAdvancement);
        
        mockArrow.onPlayerAdvancementDone(mockEvent);

        verify(mockEvent.getPlayer(), never()).discoverRecipe(any(NamespacedKey.class));
    }

    @Test
    public void test_onPlayerAdvancementDone_applicableEvent(){
        TorchArrow mockArrow = spy(new TorchArrow());
        Advancement mockEnchanter = mock(Advancement.class);
        doReturn(mockEnchanter).when(mockServer).getAdvancement(any(NamespacedKey.class));
        PlayerAdvancementDoneEvent mockEvent = mock(PlayerAdvancementDoneEvent.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getAdvancement()).thenReturn(mockEnchanter);
        
        mockArrow.onPlayerAdvancementDone(mockEvent);

        verify(mockEvent.getPlayer()).discoverRecipe(mockArrow.getRecipeKey());
    }

    @Test
    public void test_onPlayerJoin_unapplicableEvent() {
        TorchArrow mockArrow = spy(new TorchArrow());
        PlayerJoinEvent mockEvent = mock(PlayerJoinEvent.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getPlayer().getAdvancementProgress(any(Advancement.class))
            .isDone()).thenReturn(false);
        Advancement mockEnchanter = mock(Advancement.class);
        doReturn(mockEnchanter).when(mockServer).getAdvancement(any(NamespacedKey.class));

        mockArrow.onPlayerJoin(mockEvent);

        verify(mockEvent.getPlayer(), never()).discoverRecipe(any(NamespacedKey.class));
    }

    @Test
    public void test_onPlayerJoin_applicableEvent() {
        TorchArrow mockArrow = spy(new TorchArrow());
        PlayerJoinEvent mockEvent = mock(PlayerJoinEvent.class, RETURNS_DEEP_STUBS);
        when(mockEvent.getPlayer().getAdvancementProgress(any(Advancement.class))
            .isDone()).thenReturn(true);
        Advancement mockEnchanter = mock(Advancement.class);
        doReturn(mockEnchanter).when(mockServer).getAdvancement(any(NamespacedKey.class));

        mockArrow.onPlayerJoin(mockEvent);

        verify(mockEvent.getPlayer()).discoverRecipe(any(NamespacedKey.class));
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
    public void test_isCorrespondingArrowOf_nonArrowEntity() {
        TorchArrow powerArrow = new TorchArrow();
        Entity nonArrowEntity = mock(Entity.class);

        assertFalse(powerArrow.isCorrespondingArrowOf(nonArrowEntity));
    }

    @Test
    public void test_isCorrespondingArrowOf_otherArrowEntity() {
        TorchArrow powerArrow = new TorchArrow();
        Arrow otherArrowEntity = mock(Arrow.class);

        assertFalse(powerArrow.isCorrespondingArrowOf(otherArrowEntity));
    }

    @Test
    public void test_isCorrespondingArrowOf_powerArrowEntity() {
        TorchArrow powerArrow = new TorchArrow();
        Arrow powerArrowEntity = mock(Arrow.class);
        List<MetadataValue> powerList = new ArrayList<>();
        powerList.add(powerArrow.getEntityMetadata());
        when(powerArrowEntity.getMetadata("effect")).thenReturn(powerList);

        assertTrue(powerArrow.isCorrespondingArrowOf(powerArrowEntity));
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