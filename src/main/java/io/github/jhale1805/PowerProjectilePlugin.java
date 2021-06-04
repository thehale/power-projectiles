package io.github.jhale1805;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import io.github.jhale1805.powerarrow.*;

public class PowerProjectilePlugin extends JavaPlugin {

    public static PowerProjectilePlugin instance;
    public final static String NAME = "PowerProjectile";

    /**
     * Default constructor.
     * 
     * <p>Used solely by MockBukkit during unit tests.
     */
    public PowerProjectilePlugin() {
        super();
    }

    /**
     * Parameterized constructor.
     * 
     * <p>Used solely by MockBukkit during unit tests.
     */
    protected PowerProjectilePlugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Copyright 2021 by Joseph Hale (jhale1805). All Rights Reserved.");
        getLogger().info("Report issues to https://github.com/jhale1805/power-projectiles");
        getLogger().info("Loading all Power Projectiles.");
        for (PowerArrow powerArrow : PowerArrowRegistry.getPowerArrows()) {
            getServer().getPluginManager().registerEvents(powerArrow, this);
            Bukkit.addRecipe(powerArrow.getRecipe());
        }
        getLogger().info("Ready!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Thanks for using Power Projectiles!");
    }
}