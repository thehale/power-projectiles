package dev.jhale.powerprojectiles.powerrecipe;


import org.bukkit.Material;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;



public class WitherSkeletonSpawnEgg extends PowerRecipe{
    public Recipe getRecipe() {

        ItemStack WitherSkeletonSpawnEgg = new ItemStack(Material.WITHER_SKELETON_SPAWN_EGG);
        ShapedRecipe recipe = new ShapedRecipe(this.getRecipeKey(), WitherSkeletonSpawnEgg);
        recipe.shape("TW");
        recipe.setIngredient('T', Material.EGG);
        recipe.setIngredient('W', Material.WITHER_SKELETON_SKULL);
  
        return recipe;
    }

    public String getName() {
        return "WitherSkeletonSpawnEgg";
    }

}
