package io.github.jhale1805.torcharrow;

import org.junit.*;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;

import static org.junit.Assert.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import static org.mockito.Mockito.*;

public class TorchArrowListenerTest {
    
    private ServerMock server;
    private World world;
    private TorchArrowPlugin plugin;
    private TorchArrowListener taListener;

    @BeforeClass
    public static void setUpClass() {
        
    }

    @Before
    public void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(TorchArrowPlugin.class);
        taListener = new TorchArrowListener(plugin);
        world = new WorldMock(Material.DIRT, 3);
    }

    @After
    public void tearDown() {
        MockBukkit.unmock();
    }

    @AfterClass
    public static void tearDownClass() {
        
    }

    // @Test
    // public void test_onProjectileHit() {
    //     // Arrow arrow = 
        
    // }

    // // These tests are not supported by the current version of MockBukkit
    // @Test
    // public void test_onEntityShootBow() {
    //     Player player = server.addPlayer();
    //     TorchArrowStack tArrows = new TorchArrowStack(4);
    //     Arrow arrow = mock(Arrow.class);
    //     EntityShootBowEvent event = new EntityShootBowEvent(
    //         player,
    //         new ItemStack(Material.BOW), 
    //         tArrows,
    //         arrow, 
    //         EquipmentSlot.HAND,
    //         10.0f,
    //         false  // Simulate the effect of an Infinity bow
    //     );
    //     taListener.onEntityShootBow(event);
    //     verify(arrow).getMetadata("effect").contains(taListener.torchArrowMetadata);
    //     assertTrue(event.shouldConsumeItem());
    // }

    // @Test
    // public void test_onPlayerJoin_newPlayer() {
    //     Player player = server.addPlayer();
    //     PlayerJoinEvent event = new PlayerJoinEvent(player, "");
    //     taListener.onPlayerJoin(event);
    //     assertFalse(player.getDiscoveredRecipes().contains(new TorchArrowRecipe(plugin).getKey()));
    // }

    // @Test
    // public void test_onPlayerJoin_experiencedPlayer() {
    //     Player player = server.addPlayer();
    //     Advancement enchanter = Bukkit.getAdvancement(NamespacedKey.minecraft("story/enchant_item"));
    //     for (String criteria : enchanter.getCriteria())
    //         player.getAdvancementProgress(enchanter).awardCriteria(criteria);
    //     PlayerJoinEvent event = new PlayerJoinEvent(player, "");
    //     taListener.onPlayerJoin(event);
    //     assertTrue(player.getDiscoveredRecipes().contains(new TorchArrowRecipe(plugin).getKey()));
    // }

}