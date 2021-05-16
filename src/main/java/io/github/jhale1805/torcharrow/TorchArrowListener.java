package io.github.jhale1805.torcharrow;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public class TorchArrowListener implements Listener {

    private JavaPlugin plugin;
    private MetadataValue torchArrowMetadata;

    public TorchArrowListener(JavaPlugin plugin) {
        this.plugin = plugin;
        this.torchArrowMetadata = new FixedMetadataValue(plugin, "torch-arrow");
    }
    
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        // Check preconditions for torch conversion
        if (!(event.getEntity() instanceof Arrow
                && event.getEntity().getFireTicks() > 0
                && event.getEntity().getMetadata("effect").contains(torchArrowMetadata)
                && event.getHitBlock() != null
                && event.getHitBlockFace() != null
                && !event.getHitBlockFace().equals(BlockFace.DOWN))) 
            return;

        // Get location of block in which to place the torch.
        Block hitBlock = event.getHitBlock();
        BlockFace hitBlockFace = event.getHitBlockFace();
        Block adj = hitBlock.getWorld().getBlockAt(
            hitBlock.getX() + hitBlockFace.getModX(),
            hitBlock.getY() + hitBlockFace.getModY(),
            hitBlock.getZ() + hitBlockFace.getModZ()
        );

        // Compute torch orientation
        BlockData torch = Material.TORCH.createBlockData();
        if (!hitBlockFace.equals(BlockFace.UP)) {
            torch = Material.WALL_TORCH.createBlockData();
            ((Directional) torch).setFacing(hitBlockFace);
        }

        // Convert arrow to torch
        adj.setBlockData(torch);
        event.getEntity().remove();
        
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        if (event.getConsumable().getItemMeta().equals(new TorchArrow().getItemMeta())) {
            event.getProjectile().setMetadata("effect", torchArrowMetadata);
        }
    }

    @EventHandler
    public void onPlayerRecipieDiscover(PlayerRecipeDiscoverEvent event) {
        // Show the Torch Arrow in the recipie book upon discovering torches.
        if(event.getRecipe().getNamespace().equals("minecraft:torch")) {
            event.getPlayer().discoverRecipe(new TorchArrowRecipie(plugin).getKey());
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Show the Torch Arrow in the recipie book to those that have discovered torches.
        if (event.getPlayer().hasDiscoveredRecipe(NamespacedKey.fromString("minecraft:torch"))) {
            event.getPlayer().discoverRecipe(new TorchArrowRecipie(plugin).getKey());
        }
    }

}
