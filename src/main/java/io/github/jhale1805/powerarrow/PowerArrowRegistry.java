package io.github.jhale1805.powerarrow;

import java.util.Arrays;

public class PowerArrowRegistry {
    
    private static PowerArrow[] powerArrows = {
        new ExplosiveArrow(),
        new ForestFireArrow(),
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
