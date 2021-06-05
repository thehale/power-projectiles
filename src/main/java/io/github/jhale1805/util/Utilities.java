package io.github.jhale1805.util;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

public class Utilities {

    public static void drawParticles(Location from, Location to, Particle particle){
        if (!from.getWorld().equals(to.getWorld())) return;

        Vector direction = to.toVector().subtract(from.toVector()).normalize().multiply(0.4);
        Location particleLocation = from.clone();
        while(particleLocation.distanceSquared(to) > 1){
            particleLocation = particleLocation.add(direction);
            particleLocation.getWorld().spawnParticle(particle, particleLocation, 1);
        }
    }

    public static Location getImpactLocation(ProjectileHitEvent event) {
        Location loc = null;
        if (event.getHitBlock() != null) {
            loc = event.getHitBlock().getLocation();
        } else if (event.getHitEntity() != null) {
            loc = event.getHitEntity().getLocation();
        }
        return loc;
    }
    
}
