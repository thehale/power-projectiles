package io.github.jhale1805.powerarrow;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ShapedRecipe;

import io.github.jhale1805.util.Utilities;

public class ExplosiveArrow extends PowerArrow {

    public ExplosiveArrow() {
        super();
    }

    public ExplosiveArrow(int count) {
        super(count);
    }

    @Override
    public String getName() {
        return "explosive_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[]{"Shoot with Flame to", "cause a small blast."};
    }

    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
            this.getRecipeKey(),
            new ExplosiveArrow(4)
        );
        recipe.shape(" A ", "ATA", " A ");
        recipe.setIngredient('T', Material.TNT);
        recipe.setIngredient('A', Material.ARROW);
        return recipe;
    }

    @Override
    protected void onThisProjectileHit(ProjectileHitEvent event) {
        // Check preconditions for explosion
        if (!(event.getEntity().getFireTicks() > 0)) 
            return;

        Location impactLocation = Utilities.getImpactLocation(event);
        if (impactLocation != null) {
            event.getEntity().getWorld().createExplosion(
                impactLocation,  // Cause the explosion where the arrow hit.
                1F,          // Cause an explosion of 1/4th the power of TNT
                false,       // Don't start fires
                true,        // Do break blocks.
                event.getEntity()
            );
        }
    }
    
}
