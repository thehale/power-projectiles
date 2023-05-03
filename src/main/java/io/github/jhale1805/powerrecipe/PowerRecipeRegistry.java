/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

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
