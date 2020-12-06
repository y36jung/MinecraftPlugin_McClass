package me.midmad1.mcClass.commands;

import me.midmad1.mcClass.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand extends ClassAbstractCommand {
	
	public static String command = "stats";

	public StatsCommand(CommandSender sender) {
		super(sender, command);
	}
	
	public String getClassName(int classInd) {
		String ret = "Not assigned";
		switch (classInd) {
		case 1:
			ret = "Gambler";
			break;
		case 2:
			ret = "Gourmet";
			break;
		case 3:
			ret = "Fisherman";
			break;
		case 4:
			ret = "Tamer";
			break;
		default:
			break;
		}
		return ret;
	}

	@Override
	public void execute(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		int classInd = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".classInd");
		int level = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".level");
		int strength = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".strength");
		int stamina = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".stamina");
		int speed = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".speed");
		int dexterity = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".dexterity");
		int luck = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".luck");
		int statPoints = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".statPoints");
		
		player.sendMessage(player.getDisplayName().toString() + "'s Stats");
		player.sendMessage("-----------------------");
		player.sendMessage("Class: " + getClassName(classInd));
		player.sendMessage("Level: " + level);
		player.sendMessage("Strength: " + strength);
		player.sendMessage("Stamina: " + stamina);
		player.sendMessage("Speed: " + speed);
		player.sendMessage("Dexterity: " + dexterity);
		player.sendMessage("Luck: " + luck);
		player.sendMessage("Stat Points Available: " + statPoints);
		player.sendMessage("-----------------------");
	}
	
}
