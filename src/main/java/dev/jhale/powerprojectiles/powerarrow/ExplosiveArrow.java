/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.powerarrow;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;

import dev.jhale.powerprojectiles.util.EventTools;

public class ExplosiveArrow extends PowerArrow {

    public ExplosiveArrow() {
        super();
    }

    public ExplosiveArrow(int count) {
        super(count);
    }

    @Override
    public String getName() {
        return "explosive_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[]{"Shoot with Flame to", "cause a small blast."};
    }

    @Override
    public Recipe getRecipe() {
        ShapelessRecipe recipe = new ShapelessRecipe(
            this.getRecipeKey(),
            new ExplosiveArrow(2)
        );
        recipe.addIngredient(Material.TNT)
              .addIngredient(2, Material.ARROW);
        return recipe;
    }

    @Override
    protected void onThisProjectileHit(ProjectileHitEvent event) {
        // Check preconditions for explosion
        if (!(event.getEntity().getFireTicks() > 0)) 
            return;

        Location impactLocation = EventTools.getImpactLocation(event);
        if (impactLocation != null) {
            event.getEntity().getWorld().createExplosion(
                impactLocation,  // Cause the explosion where the arrow hit.
                2F,          // Cause an explosion of 1/2th the power of TNT
                false,       // Don't start fires
                true,        // Do break blocks.
                event.getEntity()
            );
        }
    }
    
}
