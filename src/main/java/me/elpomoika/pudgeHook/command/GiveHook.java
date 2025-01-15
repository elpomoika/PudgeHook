package me.elpomoika.pudgeHook.command;

import me.elpomoika.pudgeHook.item.utils.TridentUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiveHook implements CommandExecutor {
    private final TridentUtils tridentUtils;

    public GiveHook(TridentUtils tridentUtils) {
        this.tridentUtils = tridentUtils;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            tridentUtils.giveItemToPlayer(player);
        }
        return true;
    }
}
