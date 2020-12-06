package me.midmad1.mcClass.classes.gambler;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.midmad1.mcClass.Main;

public class GamblerChoose {
	
	public static void execute(Player player) {
		Main.getPlugin(Main.class).data.getConfig().set("players." + player.getUniqueId().toString() + ".classInd", 1);
		Main.getPlugin(Main.class).data.saveConfig();
		player.sendMessage(ChatColor.DARK_PURPLE + "Congratulations! You are now a Gambler!");
		player.getInventory().addItem(GamblerAbility.luckyCoin);
	}
}
