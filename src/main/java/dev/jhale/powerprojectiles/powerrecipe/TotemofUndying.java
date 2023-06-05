package dev.jhale.powerprojectiles.powerrecipe;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

public class TotemofUndying extends PowerRecipe{
    public Recipe getRecipe() {

        ItemStack totemofUndyingItemStack = new ItemStack(Material.TOTEM_OF_UNDYING);
        ShapedRecipe recipe = new ShapedRecipe(this.getRecipeKey(), totemofUndyingItemStack);
        recipe.shape("EGE", "GHG", " G ");
        recipe.setIngredient('E', Material.ENDER_EYE);
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('H', Material.GHAST_TEAR);
  
        return recipe;
    }
        

    public String getName() {
        return "TotemofUndying";
    }
   
}
