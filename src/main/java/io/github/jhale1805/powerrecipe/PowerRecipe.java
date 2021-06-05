package io.github.jhale1805.powerrecipe;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ShapedRecipe;

import io.github.jhale1805.PowerProjectilePlugin;

public abstract class PowerRecipe implements Listener {
    
    public abstract String getName();

    public abstract ShapedRecipe getRecipe();

    public NamespacedKey getRecipeKey() {
        return new NamespacedKey(
            PowerProjectilePlugin.instance,
            "recipe/" + this.getName()
        );
    }

    @EventHandler
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent event) {
        // Show the Power Recipe in the recipe book upon completing the Enchanter advancement.
        Advancement enchanter = Bukkit.getAdvancement(NamespacedKey.minecraft("story/enchant_item"));
        if (event.getAdvancement().equals(enchanter)) {
            event.getPlayer().discoverRecipe(this.getRecipeKey());
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Show the Power Recipe in the recipe book to those that have completed the Enchanter advancement.
        Advancement enchanter = Bukkit.getAdvancement(NamespacedKey.minecraft("story/enchant_item"));
        if (event.getPlayer().getAdvancementProgress(enchanter).isDone()) {
            event.getPlayer().discoverRecipe(this.getRecipeKey());
        }
    }
}
