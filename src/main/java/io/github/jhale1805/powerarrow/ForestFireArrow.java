package io.github.jhale1805.powerarrow;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import io.github.jhale1805.util.Utilities;

public class ForestFireArrow extends PowerArrow {

    public ForestFireArrow() {
        super();
    }

    public ForestFireArrow(int count) {
        super(count);
    }

    @Override
    public String getName() {
        return "forest_fire_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[]{"Starts a fire", "where it lands."};
    }

    @Override
    public Recipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
            this.getRecipeKey(),
            new ForestFireArrow()
        );
        recipe.shape("F", "B", "R");
        recipe.setIngredient('F', Material.FLINT);
        recipe.setIngredient('B', Material.BLAZE_ROD);
        recipe.setIngredient('R', Material.FEATHER);
        return recipe;
    }

    @Override 
    public Particle getTrailParticle() {
        return Particle.LAVA;
    }

    @Override
    protected void onThisProjectileHit(ProjectileHitEvent event) {
        Location impactLocation = Utilities.getImpactLocation(event);
        if (impactLocation != null) {
            Utilities.replaceAirWith(Material.FIRE, impactLocation, 1);
        }
    }
    
}
