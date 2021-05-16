package io.github.jhale1805.torcharrow;

import org.bukkit.Material;
import org.bukkit.block.Block;
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
            if (hitBlock != null) {
                hitBlock.setType(Material.TORCH);
            }
        }
    }

}
