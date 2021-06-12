package io.github.jhale1805.powerarrow;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

public class SniperArrow extends PowerArrow {

    public SniperArrow() {
        super();
    }

    public SniperArrow(int count) {
        super(count);
    }

    @Override
    public String getName() {
        return "sniper_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[] {"Fire with Power to", "hit crosshair target."};
    }

    @Override
    public Recipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
            this.getRecipeKey(),
            new SniperArrow()
        );
        recipe.shape("F", "B", "R");
        recipe.setIngredient('F', Material.FLINT);
        recipe.setIngredient('B', Material.BAMBOO);
        recipe.setIngredient('R', Material.FEATHER);
        return recipe;
    }

    @Override
    protected void onThisProjectileShot(EntityShootBowEvent event) {
        if (event.getBow().containsEnchantment(Enchantment.ARROW_DAMAGE)) {
            event.getProjectile().setGravity(false);
            event.getProjectile().getVelocity().multiply(3);
        }
    }
    
}
