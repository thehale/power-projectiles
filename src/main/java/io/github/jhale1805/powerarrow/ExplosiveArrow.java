package io.github.jhale1805.powerarrow;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ShapedRecipe;

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

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        // Check preconditions for explosion
        if (!(this.isSimilar(event.getEntity())
                && event.getEntity().getFireTicks() > 0)) 
            return;
        Location impactSite = null;
        if (event.getHitBlock() != null) {
            impactSite = event.getHitBlock().getLocation();
        } else if (event.getHitEntity() != null) {
            impactSite = event.getHitEntity().getLocation();
        }
        if (impactSite != null) {
            event.getEntity().getWorld().createExplosion(
                impactSite,  // Cause the explosion where the arrow hit.
                1F,          // Cause an explosion of 1/4th the power of TNT
                false,       // Don't start fires
                true,        // Do break blocks.
                event.getEntity()
            );
        }
        event.getEntity().remove(); 
    }
    
}
