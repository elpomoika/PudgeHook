package me.elpomoika.pudgeHook;

import me.elpomoika.pudgeHook.command.GiveHook;
import me.elpomoika.pudgeHook.item.TridentItem;
import me.elpomoika.pudgeHook.item.utils.TridentUtils;
import me.elpomoika.pudgeHook.listeners.AddPDCToTrident;
import me.elpomoika.pudgeHook.listeners.TridentHitEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PudgeHook extends JavaPlugin {
    private TridentItem tridentItem;
    private TridentUtils tridentUtils;

    @Override
    public void onEnable() {
        tridentItem = new TridentItem(this);
        tridentUtils = new TridentUtils(tridentItem, this);

        getCommand("pudge").setExecutor(new GiveHook(tridentUtils));
        Bukkit.getPluginManager().registerEvents(new AddPDCToTrident(tridentUtils, this), this);
        Bukkit.getPluginManager().registerEvents(new TridentHitEvent(tridentUtils, this), this);

    }
}
