/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.powerarrow;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

public class WaterArrow extends PowerArrow {

    public WaterArrow() {
        super();
    }

    public WaterArrow(int count) {
        super(count);
    }

    @Override
    public String getName() {
        return "water_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[]{"Places a water block", "where it lands", "when shot with Flame"};
    }
    
    @Override
    public Recipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
            this.getRecipeKey(),
            new WaterArrow()
        );
        recipe.shape("I", "S", "F");
        recipe.setIngredient('I', Material.ICE);
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('F', Material.FEATHER);
        return recipe;
    }

    @Override
    public void onThisProjectileHit(ProjectileHitEvent event) {
        // Check preconditions for torch placement
        if (!(event.getEntity().getFireTicks() > 0
                && event.getHitBlock() != null
                && event.getHitBlockFace() != null)) 
            return;
            placeWater(event);
    }

    

    private void placeWater(ProjectileHitEvent event) {
        BlockData water = Material.WATER.createBlockData();
        event.getHitBlock()
                .getRelative(event.getHitBlockFace())
                .setBlockData(water);
    }

}
