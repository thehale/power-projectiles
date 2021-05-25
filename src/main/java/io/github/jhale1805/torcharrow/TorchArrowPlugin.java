package io.github.jhale1805.torcharrow;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

public class TorchArrowPlugin extends JavaPlugin {

    /**
     * Default constructor.
     * 
     * <p>Used solely by MockBukkit during unit tests.
     */
    public TorchArrowPlugin() {
        super();
    }

    /**
     * Parameterized constructor.
     * 
     * <p>Used solely by MockBukkit during unit tests.
     */
    protected TorchArrowPlugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    @Override
    public void onEnable() {
        getLogger().info("Copyright 2021 by Joseph Hale (jhale1805). All Rights Reserved.");
        getLogger().info("Report issues to https://github.com/jhale1805/torch-arrow");
        getLogger().info("Registering Torch Arrow event listeners.");
        getServer().getPluginManager().registerEvents(new TorchArrowListener(this), this);
        getLogger().info("Adding crafting recipe for Torch Arrows.");
        Bukkit.addRecipe(new TorchArrowRecipe(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("Thanks for using Torch Arrow!");
    }
}
