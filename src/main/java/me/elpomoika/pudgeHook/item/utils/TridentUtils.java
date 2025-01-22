package me.elpomoika.pudgeHook.item.utils;

import me.elpomoika.pudgeHook.PudgeHook;
import me.elpomoika.pudgeHook.item.TridentItem;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
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

    public boolean isCustomTrident(Entity entity) {
        if (!(entity instanceof Trident trident)) return false;

        // Проверяем ключ в PersistentDataContainer трезубца
        return "1".equals(trident.getPersistentDataContainer().get(new NamespacedKey(plugin, "hook"), PersistentDataType.STRING));
    }
}
