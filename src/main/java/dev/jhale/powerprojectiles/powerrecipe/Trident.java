package dev.jhale.powerprojectiles.powerrecipe;


import org.bukkit.Material;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;



public class Trident extends PowerRecipe{
    public Recipe getRecipe() {

        ItemStack trident = new ItemStack(Material.TRIDENT);
        ShapedRecipe recipe = new ShapedRecipe(this.getRecipeKey(), trident);
        recipe.shape(" NI", " PN", "P  ");
        recipe.setIngredient('N', Material.IRON_NUGGET);
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('P', Material.PRISMARINE_CRYSTALS);
        return recipe;
    }

    public String getName() {
        return "trident";
    }
   
}
