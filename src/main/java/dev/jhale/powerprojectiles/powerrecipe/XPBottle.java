package dev.jhale.powerprojectiles.powerrecipe;


import org.bukkit.Material;


import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;



public class XPBottle extends PowerRecipe{
    public Recipe getRecipe() {

        ItemStack XPBottle = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ShapedRecipe recipe = new ShapedRecipe(this.getRecipeKey(), XPBottle);
        recipe.shape("NPN");
        recipe.setIngredient('N', Material.LAPIS_LAZULI);
        recipe.setIngredient('I', Material.GLASS_BOTTLE);
        return recipe;
    }
   
    public String getName() {
        return "XPBottle";
    }

}
