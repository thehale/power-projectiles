/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.util;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class ParticleTools {
    
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
    
}
