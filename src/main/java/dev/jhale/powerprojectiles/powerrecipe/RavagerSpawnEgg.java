package dev.jhale.powerprojectiles.powerrecipe;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;


public class RavagerSpawnEgg extends PowerRecipe{
    public Recipe getRecipe() {

        ItemStack RavagerSpawnEgg = new ItemStack(Material.RAVAGER_SPAWN_EGG);
        ShapedRecipe recipe = new ShapedRecipe(this.getRecipeKey(), RavagerSpawnEgg);
        recipe.shape("RED");
        recipe.setIngredient('R', Material.SADDLE);
        recipe.setIngredient('E', Material.EGG);
        recipe.setIngredient('D', Material.DIAMOND);
        return recipe;
    }
    public String getName() {
        return "RavagerSpawnEgg";
    }
}
