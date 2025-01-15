package me.elpomoika.pudgeHook.item;

import me.elpomoika.pudgeHook.PudgeHook;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class TridentItem {
    private final PudgeHook plugin;
    private final NamespacedKey key;

    public TridentItem(PudgeHook plugin) {
        this.plugin = plugin;
        this.key = new NamespacedKey(plugin, "hook");
    }

    public ItemStack getModifiedTrident() {
        ItemStack trident = new ItemStack(Material.TRIDENT);

        ItemMeta tridentMeta = trident.getItemMeta();
        tridentMeta.addEnchant(Enchantment.LOYALTY, 2, true);
        tridentMeta.setDisplayName("Hook");

        PersistentDataContainer tridentPdc = tridentMeta.getPersistentDataContainer();
        tridentPdc.set(key, PersistentDataType.STRING, "1");

        trident.setItemMeta(tridentMeta);
        return trident;
    }
}
