/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

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

    /**
     * Creates a new location based at the block coordinates of the 
     * given location.
     * @param loc The base location.
     * @return a new location based at the block coordinates of the 
     * given location.
     */
    public static Location blockCoordsOf(Location loc) {
        Location out = new Location(
            loc.getWorld(), 
            loc.getBlockX(), 
            loc.getBlockY(),
            loc.getBlockZ()
        );
        return out;
    }

    /**
     * Creates a new location at the base coordinates plus the given
     * coordinate deltas.
     * 
     * @param base The base lcoation.
     * @param dx The change in x to apply to the new location.
     * @param dy The change in y to apply to the new location.
     * @param dz The change in z to apply to the new location.
     * @return A new location translated from the given base by the given deltas
     */
    public static Location addImmutable(Location base, double dx, double dy, double dz) {
        Location out = new Location(
            base.getWorld(), 
            base.getX() + dx, 
            base.getY() + dy,
            base.getZ() + dz
        );
        return out;
    }

    /**
     * Returns a string representation of the given location's coordinates
     * 
     * @param loc The location to stringify
     * @return "[worldName](xCoord, yCoord, zCoord)"
     */
    public static String stringify(Location loc) {
        return "[" + loc.getWorld().getName() + "]"
            + "(" + loc.getX() + ", " 
            + loc.getY() + ", " 
            + loc.getZ() + ")";
    }

}
