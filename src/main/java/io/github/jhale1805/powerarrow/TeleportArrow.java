package io.github.jhale1805.powerarrow;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ShapedRecipe;

public class TeleportArrow extends PowerArrow {

    public TeleportArrow() {
        super();
    }

    public TeleportArrow(int count) {
        super(count);
    }

    @Override
    public String getName() {
        return "teleport_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[] {"Teleports you to", "where it lands"};
    }

    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
            this.getRecipeKey(),
            new TeleportArrow()
        );
        recipe.shape("E ", " A");
        recipe.setIngredient('E', Material.ENDER_PEARL);
        recipe.setIngredient('A', Material.ARROW);
        return recipe;
    }

    @Override
    public Particle getTrailParticle() {
        return Particle.DRAGON_BREATH;
    }

    @Override
    protected void onThisProjectileHit(ProjectileHitEvent event) {
        Player shooter = (Player) event.getEntity().getShooter();
        shooter.teleport(event.getEntity().getLocation());
    }
    
}
