package io.github.jhale1805.torcharrow;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class TorchArrowPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Copyright 2021 by Joseph Hale (jhale1805). All Rights Reserved.");
        getLogger().info("Report issues to https://github.com/jhale1805/torch-arrow");
        getLogger().info("Registering Torch Arrow event listeners.");
        getServer().getPluginManager().registerEvents(new TorchArrowListener(this), this);
        getLogger().info("Adding crafting recipie for Torch Arrows.");
        Bukkit.addRecipe(new TorchArrowRecipie(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("Thanks for using Torch Arrow!");
    }
}
