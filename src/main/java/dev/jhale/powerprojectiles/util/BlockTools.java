/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockTools {
    
    public static void replaceAll(
            Material originalMaterial,
            Material newMaterial,
            Location loc1,
            Location loc2) {
    
        // Validate parameters
        if (newMaterial == null) throw new IllegalArgumentException("You must specify a newMaterial!");
        if (loc1 == null || loc2 == null) throw new IllegalArgumentException("You must specify a complete bounding box!");
        if (!loc1.getWorld().equals(loc2.getWorld())) throw new IllegalArgumentException("loc1 and loc2 must be in the same world!");
        // Replace the blocks
        for (Integer x : new Incrementor(loc1.getBlockX(), loc2.getBlockX())) {
            for (Integer y : new Incrementor(loc1.getBlockY(), loc2.getBlockY())) {
                for (Integer z : new Incrementor(loc1.getBlockZ(), loc2.getBlockZ())) {
                    Location toCheck = new Location(loc1.getWorld(), x, y, z);
                    Block toReplace = loc1.getWorld().getBlockAt(toCheck);
                    if (originalMaterial == null || toReplace.getType() == originalMaterial) {
                        toReplace.setType(newMaterial);
                    }
                }
            }
        }
    }
    
    /**
     * Replaces all Air blocks within the specified (cubic) radius of the 
     * given centerPoint with the specified Material.
     * 
     * @param material The material with which to replace the air.
     * @param centerPoint The centerpoint of the scan for air to replace
     * @param radius The radius around the centerpoint in which to look for air
     *          blocks to replace. Recommended max is 3 (this isn't WorldEdit!)
     *          Expected to be positive, but absolute valued in case you try to
     *          be tricky :D
     */
    public static void replaceAirWith(Material material, Location centerPoint, double radius) {
        // Validate parameters
        if (material == null) {
            throw new IllegalArgumentException("You must specify a Material!");
        }
        if (centerPoint == null) {
            throw new IllegalArgumentException("You must specify a centerPoint!");
        }
        radius = Math.abs(radius);

        // Replace the blocks
        for (double dx = -radius; dx <= radius; dx++) {
            for (double dy = -radius; dy <= radius ; dy++) {
                for (double dz = -radius; dz <= radius; dz++) {
                    Location toCheck = new Location(
                        centerPoint.getWorld(),
                        centerPoint.getX() + dx,
                        centerPoint.getY() + dy,
                        centerPoint.getZ() + dz
                    );
                    Block toReplace = centerPoint.getWorld().getBlockAt(toCheck);
                    if (toReplace.getType() == Material.AIR) {
                        toReplace.setType(material);
                    }
                }
            }
        }
    }

}
