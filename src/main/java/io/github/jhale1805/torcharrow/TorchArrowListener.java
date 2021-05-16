package io.github.jhale1805.torcharrow;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class TorchArrowListener implements Listener {
    
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile instanceof Arrow) {
            Block hitBlock = event.getHitBlock();
            BlockFace hitBlockFace = event.getHitBlockFace();
            if (hitBlock != null) {
                System.out.println(hitBlockFace);
                Block adj = hitBlock.getWorld().getBlockAt(
                    hitBlock.getX() + hitBlockFace.getModX(),
                    hitBlock.getY() + hitBlockFace.getModY(),
                    hitBlock.getZ() + hitBlockFace.getModZ()
                );
                if (!hitBlockFace.equals(BlockFace.DOWN)) {
                    BlockData torch = Material.TORCH.createBlockData();
                    if (!hitBlockFace.equals(BlockFace.UP)) {
                        torch = Material.WALL_TORCH.createBlockData();
                        ((Directional) torch).setFacing(hitBlockFace);
                    }
                    adj.setBlockData(torch);
                    projectile.remove();
                }   
            }
        }
    }

}
