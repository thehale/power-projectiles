package io.github.jhale1805.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

public class Utilities {

    /**
     * Draws particles of the given type along the line specified by 
     * `from` to `to`.
     * 
     * @param particle The particle to draw
     * @param from The start point of the line of particles
     * @param to The end point of the line of particles
     */
    public static void drawParticles(Particle particle, Location from, Location to){
        if (!from.getWorld().equals(to.getWorld())) return;

        Vector direction = to.toVector().subtract(from.toVector()).normalize().multiply(0.4);
        Location particleLocation = from.clone();
        while(particleLocation.distanceSquared(to) > 1){
            particleLocation = particleLocation.add(direction);
            particleLocation.getWorld().spawnParticle(particle, particleLocation, 1);
        }
    }

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

    /**
     * Swaps the location of the two given entities.
     * 
     * @param e1 The entity to teleport to the position of e2.
     * @param e2 The entity to teleport to the position of e1.
     */
    public static void swapEntityLocations(Entity e1, Entity e2) {
        if (e1 != null || e2 != null) {
            Location loc1 = e1.getLocation();
            Location loc2 = e2.getLocation();
            e1.teleport(loc2);
            e2.teleport(loc1);
        }
    }
    
}
