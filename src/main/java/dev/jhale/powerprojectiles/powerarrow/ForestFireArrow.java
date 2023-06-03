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

import dev.jhale.powerprojectiles.util.BlockTools;
import dev.jhale.powerprojectiles.util.EventTools;

public class ForestFireArrow extends PowerArrow {

    public ForestFireArrow() {
        super();
    }

    public ForestFireArrow(int count) {
        super(count);
    }

    @Override
    public String getName() {
        return "forest_fire_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[]{"Starts a fire", "where it lands."};
    }

    @Override
    public Recipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
            this.getRecipeKey(),
            new ForestFireArrow()
        );
        recipe.shape("F", "B", "R");
        recipe.setIngredient('F', Material.FLINT);
        recipe.setIngredient('B', Material.BLAZE_ROD);
        recipe.setIngredient('R', Material.FEATHER);
        return recipe;
    }

    @Override 
    public Particle getTrailParticle() {
        return Particle.LAVA;
    }

    @Override
    protected void onThisProjectileHit(ProjectileHitEvent event) {
        Location impactLocation = EventTools.getImpactLocation(event);
        if (impactLocation != null) {
            BlockTools.replaceAirWith(Material.FIRE, impactLocation, 1);
        }
    }
    
}
