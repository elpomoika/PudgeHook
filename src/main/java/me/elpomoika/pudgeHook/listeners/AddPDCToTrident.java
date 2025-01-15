package me.elpomoika.pudgeHook.listeners;

import me.elpomoika.pudgeHook.PudgeHook;
import me.elpomoika.pudgeHook.item.utils.TridentUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class AddPDCToTrident implements Listener {
    private final TridentUtils tridentUtils;
    private final PudgeHook plugin;

    public AddPDCToTrident(TridentUtils tridentUtils, PudgeHook plugin) {
        this.tridentUtils = tridentUtils;
        this.plugin = plugin;
    }

    @EventHandler
    public void onTridentLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();

        if (projectile instanceof Trident trident && trident.getShooter() instanceof Player player) {
            ItemStack heldItem = player.getInventory().getItemInMainHand();

            if (heldItem.getType() == Material.TRIDENT &&
                    heldItem.hasItemMeta() &&
                    "1".equals(heldItem.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "hook"), PersistentDataType.STRING))) {

                trident.getPersistentDataContainer().set(new NamespacedKey(plugin, "hook"), PersistentDataType.STRING, "1");
            }
        }
    }
}

