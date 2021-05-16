package io.github.jhale1805.torcharrow;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TorchArrow extends ItemStack {

    private final static String DISPLAY_NAME = ChatColor.RED + "Torch Arrow";

    public TorchArrow() {
        super(Material.ARROW);
        setMetadata();
    }

    public TorchArrow(int count) {
        this();
        this.setAmount(count);
    }

    private void setMetadata() {
        ItemMeta metadata = this.getItemMeta();
        metadata.setDisplayName(DISPLAY_NAME);

        var lore = new ArrayList<String>();
        lore.add("Shoot with Flame to");
        lore.add("place a torch");
        metadata.setLore(lore);

        this.setItemMeta(metadata);
    }

    @Override
    public boolean isSimilar(ItemStack stack) {
        return stack.getItemMeta().equals(this.getItemMeta());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TorchArrow)) {
            return false;
        } 

        if (((TorchArrow) obj).getAmount() == this.getAmount()) {
            return true;
        } else {
            return false;
        }
    }

}
