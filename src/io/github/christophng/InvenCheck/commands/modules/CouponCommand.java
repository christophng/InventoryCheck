package io.github.christophng.InvenCheck.commands.modules;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.christophng.InvenCheck.Main;
import io.github.christophng.InvenCheck.commands.CommandInterface;
import net.md_5.bungee.api.ChatColor;

public class CouponCommand implements CommandInterface {
	
	private Main plugin;
	
	public CouponCommand(Main plugin) {
		this.plugin = plugin;
	}

	/**
	 * If player's inventory is empty, issue coupon command
	 * Else, if full, issue treasure command and give player a message
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		String keyType;
		String keyQuant;
		String keyCmd;
		String targetName;
		
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		
		// ex: /invcheck coupon TESTKEY QUANTITY PLAYERNAME

		if (args.length == 4) {
			keyType = args[1];
			keyQuant = args[2];
			targetName = args[3];
			
			Player p = Bukkit.getPlayer(targetName);
			
			if (p == null) {
				// Handle NPE
			}
			
			//TODO: Make plugin more modular using string formatting and config for more commands if plugins change
			//TODO: Clean up code, organize
			else {
				if (p.hasPermission("invencheck.use")) {
					if (isFull(p)) {
						p.sendMessage(this.plugin.getMessage("coupon-fullinventory").replace("[key]", keyType).replace("[quantity]", keyQuant));
						keyCmd = "treasures keys " + targetName + " " + keyType.toLowerCase() + " add " + keyQuant;
						Bukkit.dispatchCommand(console, keyCmd);
					}
					else {
						//Else give physical key
						keyCmd = "coupon give " + targetName + " " + keyType + " " + keyQuant;
						Bukkit.dispatchCommand(console, keyCmd);
					}
				} else {
					p.sendMessage(this.plugin.getMessage("no-access"));
				}
				// If inventory is full, add a key to player's virtual key balance
			}
		} 
			// If not 4 arguments, give prompt:
		else {
			sender.sendMessage(this.plugin.getMessage("coupon-use"));
		}
		
		return false;
	}
	
	/**
	 * @return True if inventory is full, else false
	 */
	public boolean isFull(Player player) {
		if (player.getInventory().firstEmpty() == -1) {
			return true;
		}
		return false;
	}

}
