package me.midmad1.mcClass.classes.tamer;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.midmad1.mcClass.Main;

public class TamerChoose {
	
	public static void execute(Player player) {
		Main.getPlugin(Main.class).data.getConfig().set("players." + player.getUniqueId().toString() + ".classInd", 4);
		Main.getPlugin(Main.class).data.saveConfig();
		player.sendMessage(ChatColor.DARK_PURPLE + "Congratulations! You are now a Tamer!");
		player.getInventory().addItem(TamerAbility.egg);
	}
}
