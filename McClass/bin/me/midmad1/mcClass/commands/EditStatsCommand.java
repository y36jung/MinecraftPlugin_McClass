package me.midmad1.mcClass.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.midmad1.mcClass.Main;
import me.midmad1.mcClass.gameSystems.DexteritySystem;
import me.midmad1.mcClass.gameSystems.SpeedSystem;
import me.midmad1.mcClass.gameSystems.StaminaSystem;
import net.md_5.bungee.api.ChatColor;

public class EditStatsCommand extends ClassAbstractCommand{
	
	public static String command = "editstats";

	public EditStatsCommand(CommandSender sender) {
		super(sender, command);
	}
	
	public boolean boolInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void execute(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (args != null) {
			for (int i = 1; i < args.length; ++i) {
				if (!boolInt(args[i])) {
					player.sendMessage(ChatColor.RED + "This is an incorrect input. Use /help for correct usage");
					return;
				} else {
					String id = "";
					switch (i) {
						case 1:
							id = ".level";
							break;
						case 2:
							id = ".strength";
							break;
						case 3:
							id = ".stamina";
							break;
						case 4:
							id = ".speed";
							break;
						case 5:
							id = ".dexterity";
							break;
						case 6:
							id = ".luck";
							break;
						case 7:
							id = ".statPoints";
							break;
						default:
							break;
					}
					Main.getPlugin(Main.class).data.getConfig().set("players." + player.getUniqueId().toString() + id, Integer.parseInt(args[i]));
				}
			}
			
			Main.getPlugin(Main.class).data.saveConfig();
			StaminaSystem.updateHealth(player);
			DexteritySystem.updateDex(player);
			SpeedSystem.updateMoveSpd(player);
			
		}
	}

}
