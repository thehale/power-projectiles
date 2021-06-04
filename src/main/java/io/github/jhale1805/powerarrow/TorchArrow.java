package io.github.jhale1805.powerarrow;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;
import org.bukkit.inventory.ShapedRecipe;

public class TorchArrow extends PowerArrow {

    public TorchArrow() {
        super();
    }

    public TorchArrow(int count) {
        super(count);
    }

    @Override
    public String getName() {
        return "torch_arrow";
    }

    @Override
    public String[] getUsageInstructions() {
        return new String[]{"Shoot with Flame to", "place a torch."};
    }
    
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
            this.getRecipeKey(),
            new TorchArrow(4)
        );
        recipe.shape(" A ", "ACA", " A ");
        recipe.setIngredient('C', new MaterialChoice(Tag.ITEMS_COALS));
        recipe.setIngredient('A', Material.ARROW);
        return recipe;
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        // Check preconditions for torch placement
        if (!(this.isSimilar(event.getEntity())
                && event.getEntity().getFireTicks() > 0
                && event.getHitBlock() != null
                && event.getHitBlockFace() != null)) 
            return;
        // Drop the arrow if it can't place a torch at the impact site.
        if (event.getHitBlockFace().equals(BlockFace.DOWN)) {
            dropTorchArrow(event);
        } else {
            placeTorch(event);
        }
        event.getEntity().remove(); 
    }

    private void dropTorchArrow(ProjectileHitEvent event) {
        event.getEntity().getWorld().dropItemNaturally(
            event.getEntity().getLocation(),
            new TorchArrow()
        );
    }

    private void placeTorch(ProjectileHitEvent event) {
        // Compute torch orientation
        BlockData torch = Material.TORCH.createBlockData();
        if (!event.getHitBlockFace().equals(BlockFace.UP)) {
            torch = Material.WALL_TORCH.createBlockData();
            ((Directional) torch).setFacing(event.getHitBlockFace());
        }
        // Place the torch where the arrow landed
        event.getHitBlock()
                .getRelative(event.getHitBlockFace())
                .setBlockData(torch);
    }

}
