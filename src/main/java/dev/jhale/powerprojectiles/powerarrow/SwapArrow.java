/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.powerarrow;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;

import dev.jhale.powerprojectiles.util.LocationTools;

public class SwapArrow extends PowerArrow {

    public static final int TELEPORT_DAMAGE = 5;  // Standard Ender Pearl damage

    public SwapArrow() {
        super();
    }

    public SwapArrow(int count) {
        super(count);
    }

    @Override
    public String getName() {
        return "swap_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[] {"Swaps your location", "with the hit target"};
    }

    @Override
    public Recipe getRecipe() {
        ShapelessRecipe recipe = new ShapelessRecipe(
            this.getRecipeKey(),
            new SwapArrow()
        );
        recipe.addIngredient(2, Material.ENDER_PEARL)
              .addIngredient(Material.ARROW);
        return recipe;
    }

    @Override
    public Particle getTrailParticle() {
        return Particle.PORTAL;
    }

    @Override
    protected void onThisProjectileHit(ProjectileHitEvent event) {
        Entity hitEntity = event.getHitEntity();
        Player shooter = (Player) event.getEntity().getShooter();

        if (hitEntity != null && hitEntity instanceof LivingEntity
                && shooter != null) {
            LocationTools.swapEntities(shooter, hitEntity);
            shooter.damage(TELEPORT_DAMAGE);
            ((LivingEntity) hitEntity).damage(TELEPORT_DAMAGE);
        }

    }
    
}
