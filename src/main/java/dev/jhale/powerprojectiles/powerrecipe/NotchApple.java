package dev.jhale.powerprojectiles.powerrecipe;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

public class NotchApple extends PowerRecipe {
    public Recipe getRecipe() {
        ItemStack notchAppleItemStack = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
        ShapedRecipe recipe = new ShapedRecipe(this.getRecipeKey(), notchAppleItemStack);
        recipe.shape("GGG", "GAG", "GGG");
        recipe.setIngredient('G', Material.GOLD_BLOCK);
        recipe.setIngredient('A', Material.APPLE);
        return recipe;
    }
    public String getName() {
        return "NotchApple";
    }
}
