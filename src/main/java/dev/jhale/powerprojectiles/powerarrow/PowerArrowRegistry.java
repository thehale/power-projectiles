/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles.powerarrow;

import java.util.Arrays;

public class PowerArrowRegistry {
    
    private static PowerArrow[] powerArrows = {
        new ExplosiveArrow(),
        new ForestFireArrow(),
        new HordeArrow(),
        new JailArrow(),
        new LightningArrow(),
        new NetArrow(),
        new SniperArrow(),
        new SwapArrow(),
        new TeleportArrow(),
        new TorchArrow(),
    };

    public static PowerArrow[] getPowerArrows() {
        return Arrays.copyOf(powerArrows, powerArrows.length);
    }

}
