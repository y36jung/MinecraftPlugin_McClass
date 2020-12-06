package me.midmad1.mcClass.classes.tamer.customMobs;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class TamedMobEvent implements Listener {
	
	@EventHandler
	public void hit(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player entity = (Player) event.getEntity();
			if (entity.)
		} else {
			Entity entity = event.getEntity();
		})
	}
}
