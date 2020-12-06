package me.midmad1.mcClass.gameSystems;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.midmad1.mcClass.Main;

public class StrengthSystem implements Listener {
	
	@EventHandler
	public void strWhenHits(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			int str = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".strength");
			double dmg = event.getDamage() + (str * 0.05);
			event.setDamage(dmg);
		}
	}

}
