package io.github.christophng.InvenCheck.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * 
 * @author Chris Ng
 * Interface for commands
 *
 */
public interface CommandInterface {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args);
	
}
