package me.midmad1.mcClass.gameSystems;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.midmad1.mcClass.Main;

public class LuckSystem implements Listener {
	
	@EventHandler
	public void luckDodge(EntityDamageEvent event) {
		if (event.getCause() == DamageCause.ENTITY_ATTACK) {
			if (event.getEntity() instanceof Player) {
				Player player = (Player) event.getEntity();
				int luck = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".luck");
				Random r = new Random();
				double chance = -1;
				if (luck > 0) {
					chance = Math.log10(luck);
				}
				if (r.nextDouble() <= chance / 20) {
					event.setDamage(0.0);
					player.sendMessage(ChatColor.DARK_GREEN + "Dodged!");
				}
			}
		}
	}
	
	@EventHandler
	public void luckRandomCrit(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			int luck = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".luck");
			Random r = new Random();
			double chance = -1;
			if (luck > 0) {
				chance = Math.log10(luck);
			}
			if (r.nextDouble() <= chance / 20) {
				event.setDamage(event.getDamage() * 3);
				player.sendMessage(ChatColor.DARK_GREEN + "Random Crit!");
			}
		}
	}
}
