package io.github.jhale1805.powerarrow;

import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.Recipe;

public class WitherArrow extends PowerArrow {

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String[] getUsageInstructions() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Recipe getRecipe() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onThisProjectileShot(EntityShootBowEvent event) {
        // TODO Get the entity that shot the arrow from the event.
        // TODO Launch a wither skull from that entity.
    }
    
}
