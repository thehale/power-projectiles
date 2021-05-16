package io.github.jhale1805.torcharrow;

import org.bukkit.plugin.java.JavaPlugin;

public class TorchArrowPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("You've discovered torch arrows!");
        getServer().getPluginManager().registerEvents(new TorchArrowListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Thank's for using torch arrows!");
    }
}
