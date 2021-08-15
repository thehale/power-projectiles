package io.github.jhale1805.util;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class LocationTools {
    
    /**
     * Swaps the location of the two given entities.
     * 
     * @param e1 The entity to teleport to the position of e2.
     * @param e2 The entity to teleport to the position of e1.
     */
    public static void swapEntities(Entity e1, Entity e2) {
        if (e1 != null || e2 != null) {
            Location loc1 = e1.getLocation();
            Location loc2 = e2.getLocation();
            e1.teleport(loc2);
            e2.teleport(loc1);
        }
    }

}
