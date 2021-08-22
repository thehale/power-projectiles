package io.github.jhale1805;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import io.github.jhale1805.powerarrow.*;
import io.github.jhale1805.powerrecipe.*;

public class PowerProjectilePlugin extends JavaPlugin {

    public static PowerProjectilePlugin instance;
    public static Logger log;
    public final static String NAME = "PowerProjectile";
    public final static int BSTATS_PLUGIN_ID = 12003;

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
        log = getLogger();
        log.info("Copyright 2021 by Joseph Hale (jhale1805). All Rights Reserved.");
        log.info("Report issues to https://github.com/jhale1805/power-projectiles");
        log.info("Loading all Power Projectiles.");
        registerPowerArrows();
        registerPowerRecipes();
        new Metrics(this, BSTATS_PLUGIN_ID);  // Enable bStats metrics
        log.info("Ready!");
    }

    private void registerPowerArrows() {
        for (PowerArrow powerArrow : PowerArrowRegistry.getPowerArrows()) {
            getServer().getPluginManager().registerEvents(powerArrow, this);
            Bukkit.addRecipe(powerArrow.getRecipe());
        }
    }

    private void registerPowerRecipes() {
        for (PowerRecipe powerRecipe : PowerRecipeRegistry.getPowerRecipes()) {
            getServer().getPluginManager().registerEvents(powerRecipe, this);
            Bukkit.addRecipe(powerRecipe.getRecipe());
        }
    }

    @Override
    public void onDisable() {
        log.info("Thanks for using Power Projectiles!");
    }
}
