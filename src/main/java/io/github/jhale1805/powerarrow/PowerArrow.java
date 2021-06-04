package io.github.jhale1805.powerarrow;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import io.github.jhale1805.PowerProjectilePlugin;

public abstract class PowerArrow extends ItemStack implements Listener {

    public PowerArrow() {
        super(Material.ARROW);
        this.setItemMeta(this.getItemMetadata());
    }

    public PowerArrow(int count) {
        this();
        this.setAmount(count);
    }

    /**
     * @return the name of this PowerArrow in snake_case.
     */
    public abstract String getName();

    /**
     * Each PowerArrow's usage instructions are shown in its lore.
     * 
     * Instructions should be no more than 3 lines of 20 characters.
     * @return the instructions for using this PowerArrow.
     */
    public abstract String[] getUsageInstructions();

    public abstract ShapedRecipe getRecipe();

    public NamespacedKey getRecipeKey() {
        return new NamespacedKey(PowerProjectilePlugin.instance, "recipe/" + this.getName());
    }

    public NamespacedKey getItemKey() {
        return new NamespacedKey(PowerProjectilePlugin.instance, this.getName());
    }

    public ItemMeta getItemMetadata() {
        ItemMeta itemMetadata = new ItemStack(Material.ARROW).getItemMeta();
        itemMetadata.setDisplayName(this.getDisplayName());
        itemMetadata.setLore(Arrays.asList(this.getUsageInstructions()));
        return itemMetadata;
    }

    protected String getDisplayName() {
        String displayName = ChatColor.RED.toString();
        String[] nameParts = this.getName().split("_");
        for (String part : nameParts) {
            displayName += Character.toUpperCase(part.charAt(0)) 
                    + part.substring(1)
                    + " ";
        }
        return displayName;
    }

    public MetadataValue getEntityMetadata() {
        return new FixedMetadataValue(PowerProjectilePlugin.instance, this.getName());
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        if (event.getConsumable().getItemMeta().equals(this.getItemMetadata())) {
            event.getProjectile().setMetadata("effect", this.getEntityMetadata());
            event.setConsumeItem(true);  // Ignore the effects of Infinity
        }
    }

    @EventHandler
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent event) {
        // Show the Torch Arrow in the recipe book upon completing the Enchanter advancement.
        Advancement enchanter = Bukkit.getAdvancement(NamespacedKey.minecraft("story/enchant_item"));
        if (event.getAdvancement().equals(enchanter)) {
            event.getPlayer().discoverRecipe(this.getRecipeKey());
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Show the Torch Arrow in the recipe book to those that have completed the Enchanter advancement.
        Advancement enchanter = Bukkit.getAdvancement(NamespacedKey.minecraft("story/enchant_item"));
        if (event.getPlayer().getAdvancementProgress(enchanter).isDone()) {
            event.getPlayer().discoverRecipe(this.getRecipeKey());
        }
    }

    @Override
    public boolean isSimilar(ItemStack stack) {
        return stack.getItemMeta().equals(this.getItemMeta());
    }

    /**
     * Determines if the given entity is a PowerArrow of this type.
     * @param entity The entity to assess.
     * @return true if the entity is a PowerArrow of this type.
     */
    public boolean isSimilar(Entity entity) {
        return entity instanceof Arrow 
            && entity.getMetadata("effect")
                    .stream()
                    .map(e -> e.asString())
                    .collect(Collectors.toList())
                    .contains(this.getEntityMetadata().asString());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ItemStack)) {
            return false;
        } 
        ItemStack other = (ItemStack) obj;
        return isSimilar(other) && other.getAmount() == this.getAmount();
    }
    
}
