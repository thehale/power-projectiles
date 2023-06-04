/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.powerarrow;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import dev.jhale.powerprojectiles.util.EventTools;

public class LightningArrow extends PowerArrow {

    public LightningArrow() {
        super();
    }

    public LightningArrow(int count) {
        super(count);
    }

    @Override
    public String getName() {
        return "lightning_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[]{"Summons lightning", "where it lands."};
    }

    @Override
    public Recipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
            this.getRecipeKey(),
            new LightningArrow()
        );
        recipe.shape("R", "L", "F");
        recipe.setIngredient('R', Material.REDSTONE);
        recipe.setIngredient('L', Material.LIGHTNING_ROD);
        recipe.setIngredient('F', Material.FEATHER);
        return recipe;
    }

    @Override 
    public Particle getTrailParticle() {
        return Particle.ELECTRIC_SPARK;
    }

    @Override
    protected void onThisProjectileHit(ProjectileHitEvent event) {
        Location impactLocation = EventTools.getImpactLocation(event);
        if (impactLocation != null) {
            event.getEntity().getWorld().strikeLightning(impactLocation);
        }
    }
    
}
