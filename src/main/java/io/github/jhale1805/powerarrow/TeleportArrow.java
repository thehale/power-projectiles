/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.github.jhale1805.powerarrow;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;

public class TeleportArrow extends PowerArrow {

    public static final int TELEPORT_DAMAGE = 5;  // Standard Ender Pearl damage

    public TeleportArrow() {
        super();
    }

    public TeleportArrow(int count) {
        super(count);
    }

    @Override
    public String getName() {
        return "teleport_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[] {"Teleports you to", "where it lands."};
    }

    @Override
    public Recipe getRecipe() {
        ShapelessRecipe recipe = new ShapelessRecipe(
            this.getRecipeKey(),
            new TeleportArrow()
        );
        recipe.addIngredient(Material.ENDER_PEARL)
              .addIngredient(Material.ARROW);
        return recipe;
    }

    @Override
    public Particle getTrailParticle() {
        return Particle.PORTAL;
    }

    @Override
    protected void onThisProjectileHit(ProjectileHitEvent event) {
        Player shooter = (Player) event.getEntity().getShooter();
        shooter.teleport(event.getEntity().getLocation());
        event.getEntity().getWorld().spawnParticle(
            this.getTrailParticle(), 
            event.getEntity().getLocation(), 
            100
        );

        shooter.damage(TELEPORT_DAMAGE);
    }
    
}
