package io.github.jhale1805.powerrecipe;

import java.util.Arrays;

public class PowerRecipeRegistry {
    
    private static PowerRecipe[] powerRecipes = {
        new Cobweb(),
    };

    public static PowerRecipe[] getPowerRecipes() {
        return Arrays.copyOf(powerRecipes, powerRecipes.length);
    }

}
