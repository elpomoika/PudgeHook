package me.elpomoika.pudgeHook.listeners;

import me.elpomoika.pudgeHook.PudgeHook;
import me.elpomoika.pudgeHook.item.utils.TridentUtils;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class TridentHitEvent implements Listener {
    private final TridentUtils tridentUtils;
    private final PudgeHook plugin;

    public TridentHitEvent(TridentUtils tridentUtils, PudgeHook plugin) {
        this.tridentUtils = tridentUtils;
        this.plugin = plugin;
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Trident trident) {
            if (event.getHitEntity() instanceof Pig victim) {
                if (tridentUtils.isCustomTrident(trident)) {
                    if (trident.getShooter() instanceof Player shooter) {
                        magnetEntityToTrident(victim, trident, shooter);
                    }
                }
            }
        }
    }

    public void magnetEntityToTrident(Entity victim, Trident trident, Player shooter) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (victim.isDead() || trident.isDead()) {
                    this.cancel();
                    return;
                }

                Location victimLocation = victim.getLocation();
                Location tridentLocation = trident.getLocation();

                Vector directionVictimToTrident = tridentLocation.toVector().subtract(victimLocation.toVector()).normalize();

                victim.setVelocity(directionVictimToTrident.multiply(1.0));
                System.out.println(victimLocation.distance(shooter.getLocation()));

                if (victimLocation.distanceSquared(shooter.getLocation()) < 1) {
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }
}