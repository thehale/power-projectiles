package io.github.jhale1805.powerarrow;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ShapedRecipe;

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

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        // Check preconditions for explosion
        if ( !this.isSimilar(event.getEntity()) ) 
            return;
        // Get the impact site
        Location impactSite = null;
        if (event.getHitBlock() != null) {
            impactSite = event.getHitBlock().getLocation();
        } else if (event.getHitEntity() != null) {
            impactSite = event.getHitEntity().getLocation();
            event.getHitEntity().setFireTicks(20); // ignite a hit entity
        }
        // Start the forest fire around the impact site
        if (impactSite != null) {
            for (double x = -1; x <= 1; x++) {
                for (double y = 0; y <= 2; y++) {
                    for (double z = -1; z <= 1; z++) {
                        Location toCheck = new Location(
                            event.getEntity().getWorld(),
                            impactSite.getX() + x,
                            impactSite.getY() + y,
                            impactSite.getZ() + z
                        );
                        Block toIgnite = event.getEntity().getWorld()
                                .getBlockAt(toCheck);
                        if (toIgnite.getType() == Material.AIR) {
                            toIgnite.setType(Material.FIRE);
                        }
                    }
                }
            }
        }
        event.getEntity().remove(); 
    }
    
}
