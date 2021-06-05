package io.github.jhale1805.powerarrow;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.event.entity.ProjectileHitEvent;
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
    public ShapedRecipe getRecipe() {
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
            startForestFire(impactLocation);
        }
    }

    private void startForestFire(Location location) {
        for (double x = -1; x <= 1; x++) {
            for (double y = 0; y <= 2; y++) {
                for (double z = -1; z <= 1; z++) {
                    Location toCheck = new Location(
                        location.getWorld(),
                        location.getX() + x,
                        location.getY() + y,
                        location.getZ() + z
                    );
                    Block toIgnite = location.getWorld().getBlockAt(toCheck);
                    if (toIgnite.getType() == Material.AIR) {
                        toIgnite.setType(Material.FIRE);
                    }
                }
            }
        }
    }
    
}
