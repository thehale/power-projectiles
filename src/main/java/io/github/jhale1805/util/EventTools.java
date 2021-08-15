package io.github.jhale1805.util;

import org.bukkit.Location;
import org.bukkit.event.entity.ProjectileHitEvent;

public class EventTools {
    
    /**
     * Returns the Location where the projectile hit.
     * 
     * Abstracts out the complexity of determining whether the projectile hit
     * a block or an Entity.
     * 
     * @param event The event from which you want the impact location.
     * @return The Location where the projectile hit.
     */
    public static Location getImpactLocation(ProjectileHitEvent event) {
        Location loc = null;
        if (event.getHitBlock() != null) {
            loc = event.getHitBlock().getRelative(event.getHitBlockFace()).getLocation();
        } else if (event.getHitEntity() != null) {
            loc = event.getHitEntity().getLocation();
        }
        return loc;
    }

}
