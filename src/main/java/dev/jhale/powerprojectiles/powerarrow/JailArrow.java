/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.powerarrow;

import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import dev.jhale.powerprojectiles.PowerProjectilePlugin;
import dev.jhale.powerprojectiles.util.BlockTools;
import dev.jhale.powerprojectiles.util.LocationTools;

public class JailArrow extends PowerArrow {

    public JailArrow() {
        super();
    }

    public JailArrow(int count) {
        super(count);
    }

    @Override
    public String getName() {
        return "jail_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[] {"Surrounds hit target", "with iron bars"};
    }

    @Override
    public Recipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
            this.getRecipeKey(),
            new JailArrow()
        );
        recipe.shape("III", "IAI", "III");
        recipe.setIngredient('I', Material.IRON_BARS);
        recipe.setIngredient('A', Material.ARROW);
        return recipe;
    }

    @Override
    protected void onThisProjectileHit(ProjectileHitEvent event) {
        Entity hitEntity = event.getHitEntity();
        if (hitEntity != null) {
            Location barsBox1 = LocationTools.blockCoordsOf(hitEntity.getLocation());
            Location barsBox2 = LocationTools.addImmutable(hitEntity.getLocation(), 1, 1, 1);
            Location teleportTo = LocationTools.addImmutable(hitEntity.getLocation(), 1, 0, 1);
            PowerProjectilePlugin.log.log(Level.FINEST, "[" + getName() + "]"
                + " Placing bars from " + LocationTools.stringify(barsBox1) 
                + " to " + LocationTools.stringify(barsBox1)
            );
            BlockTools.replaceAll(null, Material.IRON_BARS, barsBox1, barsBox2);
            hitEntity.teleport(teleportTo);
        }
    }
    
}
