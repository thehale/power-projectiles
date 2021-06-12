package io.github.jhale1805.powerarrow;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import io.github.jhale1805.util.Utilities;

public class NetArrow extends PowerArrow {

    public NetArrow() {
        super();
    }

    public NetArrow(int count) {
        super(count);
    }

    @Override
    public String getName() {
        return "net_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[]{"Sprays cobwebs", "where it lands."};
    }

    @Override
    public Recipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
            this.getRecipeKey(),
            new NetArrow()
        );
        recipe.shape("CCC", "CAC", "CCC");
        recipe.setIngredient('C', Material.COBWEB);
        recipe.setIngredient('A', Material.ARROW);
        return recipe;
    }

    @Override 
    public Particle getTrailParticle() {
        return Particle.SPIT;
    }

    @Override
    protected void onThisProjectileHit(ProjectileHitEvent event) {
        Location impactLocation = Utilities.getImpactLocation(event);
        if (impactLocation != null) {
            Utilities.replaceAirWith(Material.COBWEB, impactLocation, 0.5);
        }
    }
    
}
