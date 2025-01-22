package me.elpomoika.pudgeHook.listeners;

import me.elpomoika.pudgeHook.PudgeHook;
import me.elpomoika.pudgeHook.item.utils.TridentUtils;
import org.bukkit.Material;
import org.bukkit.entity.*;
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
                        chainBehindTrident(trident, shooter);
                        magnetVictimToShooter(victim, shooter);
                    }
                }
            }
        }
    }

    public void magnetVictimToShooter(Entity victim, Player shooter) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (victim.isDead() || shooter.isDead()) {
                    this.cancel();
                    return;
                }

                Vector victimLocation = victim.getLocation().toVector();
                Vector shooterLocation = shooter.getLocation().toVector();

                Vector direction = shooterLocation.subtract(victimLocation);

                Vector velocity = direction.normalize().multiply(1.0);
                victim.setVelocity(velocity);

                if (victim.getLocation().distance(shooter.getLocation()) < 2) {
                    this.cancel();
                    victim.setVelocity(direction.multiply(0));
                }

            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    private void chainBehindTrident(Trident trident, Player shooter) {
        new BukkitRunnable() {
            @Override
            public void run() {

                BlockDisplay display = trident.getLocation().getWorld().spawn(trident.getLocation(), BlockDisplay.class, entity -> {
                    entity.setBlock(Material.CHAIN.createBlockData());
                    entity.setBillboard(Display.Billboard.FIXED);
                    entity.setRotation(shooter.getBodyYaw(), 180f);
                    entity.setGravity(false);
                    entity.setPersistent(true);
                });
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }
}