package dev.jhale.powerprojectiles.powerrecipe;


import org.bukkit.Material;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;



public class ZombieSpawnEgg extends PowerRecipe{
    public Recipe getRecipe() {

        ItemStack ZombieSpawnEgg = new ItemStack(Material.ZOMBIE_SPAWN_EGG);
        ShapedRecipe recipe = new ShapedRecipe(this.getRecipeKey(), ZombieSpawnEgg);
        recipe.shape("WWW", "WTW", "WWW");
        recipe.setIngredient('T', Material.EGG);
        recipe.setIngredient('W', Material.ROTTEN_FLESH);
  
        return recipe;
    }

    
    public String getName() {
        return "ZombieSpawnEgg";
    }

}
