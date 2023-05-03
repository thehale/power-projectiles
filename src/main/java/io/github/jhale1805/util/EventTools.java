/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

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
