package io.github.jhale1805.powerarrow;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;


public class WitherArrow extends PowerArrow {

    @Override
    public String getName() {
        return "wither_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[]{"Launches a wither skull", "instead of an arrow"};
    }

    @Override
    public Recipe getRecipe() {
        ShapelessRecipe recipe = new ShapelessRecipe(
                this.getRecipeKey(),
                new TeleportArrow()
        );
        recipe.addIngredient(Material.WITHER_SKELETON_SKULL)
                .addIngredient(Material.ARROW);
        return recipe;

    }

    @Override
    public void onThisProjectileShot(EntityShootBowEvent event) {
        Player shooter = (Player) event.getEntity().getShooter();
        // TODO Get the entity that shot the arrow from the event.
        // TODO Launch a wither skull from that entity.
    }
    
}
