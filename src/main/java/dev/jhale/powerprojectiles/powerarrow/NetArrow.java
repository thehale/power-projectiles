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

public class NetArrow extends PowerArrow {

    public NetArrow() {
        super();
    }

    public NetArrow(int count) {
        super(count);
    }

    @Override
    public String getName() {
        return "net_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[]{"Sprays cobwebs", "where it lands."};
    }

    @Override
    public Recipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
            this.getRecipeKey(),
            new NetArrow()
        );
        recipe.shape("CCC", "CAC", "CCC");
        recipe.setIngredient('C', Material.COBWEB);
        recipe.setIngredient('A', Material.ARROW);
        return recipe;
    }

    @Override 
    public Particle getTrailParticle() {
        return Particle.SPIT;
    }

    @Override
    protected void onThisProjectileHit(ProjectileHitEvent event) {
        Location impactLocation = EventTools.getImpactLocation(event);
        if (impactLocation != null) {
            BlockTools.replaceAirWith(Material.COBWEB, impactLocation, 0.5);
        }
    }
    
}
