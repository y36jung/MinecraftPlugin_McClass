package me.midmad1.mcClass.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.midmad1.mcClass.Main;
import me.midmad1.mcClass.classes.fisherman.FishermanAbility;

public class FishermanCommand extends ClassAbstractCommand {
	
	public static String className = "fisherman";
	
	public FishermanCommand(CommandSender sender) {
		super(sender, className);
	}

	public void execute(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		Main.getPlugin(Main.class).data.getConfig().set("players." + player.getUniqueId().toString() + ".classInd", 3);
		Main.getPlugin(Main.class).data.saveConfig();
		player.sendMessage(ChatColor.DARK_PURPLE + "Congratulations! You are now a Fisherman!");
		player.getInventory().addItem(FishermanAbility.hook);
	}

}
