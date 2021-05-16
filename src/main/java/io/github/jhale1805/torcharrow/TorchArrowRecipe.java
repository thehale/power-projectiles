package io.github.jhale1805.torcharrow;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class TorchArrowRecipe extends ShapedRecipe {

    public static final String NAMESPACED_KEY = "torch_arrow";
    
    /**
     * The recipie for crafting torch arrows.
     * 
     * This recipie yields 4 torch arrows, enough to generate the same number
     * of torches that the single piece of coal could be used to craft.
     * 
     * -------------
     * |   | A |   |
     * -------------
     * | A | C | A |
     * -------------
     * |   | A |   |
     * -------------
     * 
     * @param plugin The Torch Arrow plugin. Required for associating this
     *          recipie with the plugin.
     */
    public TorchArrowRecipe(Plugin plugin) {
        super(new NamespacedKey(plugin, NAMESPACED_KEY), new TorchArrow());
        shape(" A ", "ACA", " A ");
        setIngredient('C', Material.COAL);
        setIngredient('A', Material.ARROW);
    }

    @Override
    public ItemStack getResult() {
        return new TorchArrow(4);
    }

}
