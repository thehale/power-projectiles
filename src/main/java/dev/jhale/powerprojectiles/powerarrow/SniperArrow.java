/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.powerarrow;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.scheduler.BukkitRunnable;

import dev.jhale.powerprojectiles.PowerProjectilePlugin;

public class SniperArrow extends PowerArrow {

    /**
     * How many times faster to fly than a normal arrow.
     */
    private static final double VELOCITY_MULTIPLIER = 3.0;

    /**
     * How many ticks to stay in the air.
     * 
     * Important since Sniper Arrows aren't affected by gravity and thus
     * might never hit the ground.
     */
    private static final int TIMEOUT_DELAY = 400;

    public SniperArrow() {
        super();
    }

    public SniperArrow(int count) {
        super(count);
    }

    @Override
    public String getName() {
        return "sniper_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[] {"Directly hits target", "in crosshairs when", "shot with Power."};
    }

    @Override
    public Recipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
            this.getRecipeKey(),
            new SniperArrow()
        );
        recipe.shape("F", "B", "R");
        recipe.setIngredient('F', Material.FLINT);
        recipe.setIngredient('B', Material.BAMBOO);
        recipe.setIngredient('R', Material.FEATHER);
        return recipe;
    }

    @Override
    protected void onThisProjectileShot(EntityShootBowEvent event) {
        if (event.getBow().containsEnchantment(Enchantment.ARROW_DAMAGE)) {
            Entity arrow = event.getProjectile();
            arrow.setGravity(false);
            arrow.getVelocity().multiply(VELOCITY_MULTIPLIER);
            new BukkitRunnable() {
                @Override
                public void run() {
                    arrow.remove();
                }
            }.runTaskLater(PowerProjectilePlugin.instance, TIMEOUT_DELAY);
        }
    }
    
}
