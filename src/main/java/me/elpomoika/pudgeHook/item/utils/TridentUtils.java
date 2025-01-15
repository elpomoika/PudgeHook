package me.elpomoika.pudgeHook.item.utils;

import me.elpomoika.pudgeHook.PudgeHook;
import me.elpomoika.pudgeHook.item.TridentItem;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class TridentUtils {
    private final TridentItem tridentItem;
    private final PudgeHook plugin;

    public TridentUtils(TridentItem tridentItem, PudgeHook plugin) {
        this.tridentItem = tridentItem;
        this.plugin = plugin;
    }

    public void giveItemToPlayer(Player player) {
        player.getInventory().addItem(tridentItem.getModifiedTrident());
    }

    public boolean isCustomItem(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        String pdc = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "hook"), PersistentDataType.STRING);
        return pdc.equals("1");
    }

    public boolean isCustomTrident(Entity entity) {
        if (!(entity instanceof Trident trident)) return false;

        // Проверяем ключ в PersistentDataContainer трезубца
        System.out.println("1".equals(trident.getPersistentDataContainer().get(new NamespacedKey(plugin, "hook"), PersistentDataType.STRING)) + " from check entity");
        return "1".equals(trident.getPersistentDataContainer().get(new NamespacedKey(plugin, "hook"), PersistentDataType.STRING));
    }
}
