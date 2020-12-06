package me.midmad1.mcClass.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class ClassAbstractCommand {
	private final CommandSender sender;
	private final String label;
	
	public ClassAbstractCommand(CommandSender sender, String label) {
		this.sender = sender;
		this.label = label;
	}
	
	public CommandSender getSender() {
		return sender;
	}
	
	public String getLabel() {
		return label;
	}
	
    public abstract void execute(CommandSender sender, Command command, String label, String[] args);

}
