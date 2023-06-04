/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.powerarrow;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.scheduler.BukkitRunnable;

import dev.jhale.powerprojectiles.PowerProjectilePlugin;
import dev.jhale.powerprojectiles.util.EventTools;

public class HordeArrow extends PowerArrow {

    /**
     * The number of baby zombies to spawn.
     */
    private static final int HORDE_SIZE = 7;

    /**
     * How many ticks to keep the horde.
     */
    private static final int TIMEOUT_DELAY = HORDE_SIZE * 20;

    public HordeArrow() {
        super();
    }

    public HordeArrow(int count) {
        super(count);
    }

    @Override
    public String getName() {
        return "horde_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[] { "Summons baby zombies", "around its victim." };
    }

    @Override
    public Recipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
                this.getRecipeKey(),
                new HordeArrow());
        recipe.shape("FZF", "FSF", "FFF");
        recipe.setIngredient('Z', Material.ZOMBIE_HEAD);
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('F', Material.ROTTEN_FLESH);
        return recipe;
    }

    @Override
    public Particle getTrailParticle() {
        return Particle.SQUID_INK;
    }

    @Override
    protected void onThisProjectileHit(ProjectileHitEvent event) {
        Location impactLocation = EventTools.getImpactLocation(event);
        if (impactLocation != null) {
            for (int i = 0; i < HORDE_SIZE; i++) {
                Zombie zombie = (Zombie) event.getEntity().getWorld().spawnEntity(impactLocation, EntityType.ZOMBIE);
                zombie.setBaby();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        zombie.remove();
                        zombie.getWorld().spawnParticle(Particle.FLAME, zombie.getLocation(), 5);
                    }
                }.runTaskLater(PowerProjectilePlugin.instance, TIMEOUT_DELAY);
            }
        }
    }

}
