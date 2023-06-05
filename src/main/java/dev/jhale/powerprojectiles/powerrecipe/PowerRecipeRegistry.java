/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.powerrecipe;

import java.util.Arrays;

public class PowerRecipeRegistry {
    
    private static PowerRecipe[] powerRecipes = {
        new Cobweb(),
        new NotchApple(),
        new RavagerSpawnEgg(),
        new TotemofUndying(),
        new Trident(),
        new WitherSkeletonSpawnEgg(),
        new XPBottle(),
        new ZombieSpawnEgg(),
    };

    public static PowerRecipe[] getPowerRecipes() {
        return Arrays.copyOf(powerRecipes, powerRecipes.length);
    }

}
