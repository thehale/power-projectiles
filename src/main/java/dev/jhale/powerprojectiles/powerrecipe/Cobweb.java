/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.powerrecipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;


public class Cobweb extends PowerRecipe {

    
    @Override
    public String getName() {
        return "cobweb";
    }

    public ShapedRecipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
            this.getRecipeKey(),
            new ItemStack(Material.COBWEB, 1)
        );
        recipe.shape("SSS", "SBS", "SSS");
        recipe.setIngredient('S', Material.STRING);
        recipe.setIngredient('B', Material.SLIME_BALL);
        return recipe;
    }

    @EventHandler
    public void onPlayerDiscoverRecipe(PlayerRecipeDiscoverEvent event) {
        // Show Cobweb in the recipe book upon discovering the slimeball.
        if (event.getRecipe().equals(NamespacedKey.minecraft("slime_block"))) {
            event.getPlayer().discoverRecipe(this.getRecipeKey());
        }
    }

    @Override
    @EventHandler
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent event) {
        // Recipe discovery handled through a recipe discover event 
        // instead of an advancement completion.
    }

    @Override
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Show Cobweb in the recipe book to those that have discovered the slimeball.
        if (event.getPlayer().hasDiscoveredRecipe(NamespacedKey.minecraft("slime_block"))) {
            event.getPlayer().discoverRecipe(this.getRecipeKey());
        }
    }

}
