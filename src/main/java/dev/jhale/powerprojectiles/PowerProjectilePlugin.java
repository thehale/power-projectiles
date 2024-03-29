/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package dev.jhale.powerprojectiles;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import dev.jhale.powerprojectiles.bstats.Metrics;
import dev.jhale.powerprojectiles.powerarrow.*;
import dev.jhale.powerprojectiles.powerrecipe.*;

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
        log.info("Copyright (c) 2021-2023 by Joseph Hale (thehale). All Rights Reserved.");
        log.info("Report issues to https://github.com/thehale/power-projectiles");
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
