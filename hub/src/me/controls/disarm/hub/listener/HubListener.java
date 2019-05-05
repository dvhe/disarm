package me.controls.disarm.hub.listener;

import me.controls.disarm.hub.Hub;
import me.controls.disarm.hub.util.ParticleCat;

import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.*;

public class HubListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("dummy", "");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§3§lDisarm §7§lNetwork");

        Score score = objective.getScore("§7§m---------------------");
        score.setScore(3);

        Score score1 = objective.getScore(" ");
        score1.setScore(4);

        Score score2 = objective.getScore("§7§m--------------------- ");
        score2.setScore(5);

        player.setScoreboard(scoreboard);


        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3));

        event.setJoinMessage("§7§m-------------------------------------------" +
                "\n" +
                "\n" +
                "§3 Disarm §7Network" +
                " \n " +
                " \n " +
                " \n " +
                "§7Hey " + "§3§n" +player.getDisplayName()+ "§7, welcome to the server!" +
                " \n " +
                " \n " +
                " \n " +
                " \n " +
                "§7§m-------------------------------------------");

        //player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 3));
    }

    @EventHandler
    public void onJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if(player.getGameMode() == GameMode.CREATIVE) return;
        event.setCancelled(true);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setVelocity(player.getLocation().getDirection().multiply(1.2F).setY(1));
        player.getWorld().playSound(player.getLocation(), Sound.FIREWORK_BLAST, 1.0F, -5.0F);
        ParticleCat.sendParticle(EnumParticle.FIREWORKS_SPARK, player.getLocation(), 0.5f, 0.5f, 0.5f, 0.07f, 80);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if ((player.getGameMode() != GameMode.CREATIVE) && (player.getLocation().subtract(0.0, 1.0D, 0.0D).getBlock().getType() != Material.AIR) && (!player.isFlying()));
        player.setAllowFlight(true);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamage (EntityDamageEvent event) {
        if(event.getCause() != EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange (FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            player.updateInventory();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);
        player.updateInventory();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
    }

}