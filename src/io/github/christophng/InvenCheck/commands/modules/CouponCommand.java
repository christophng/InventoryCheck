package io.github.christophng.InvenCheck.commands.modules;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.christophng.InvenCheck.Main;
import io.github.christophng.InvenCheck.commands.CommandInterface;

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
		
		String couponType;
		String couponQuant;
		String couponCmd;
		String targetName;
		
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		
		// ex: /invcheck coupon TESTKEY QUANTITY PLAYERNAME
		// args length = 3
			if (args.length > 1 && args.length <= 4) {
				couponType = args[1];
				couponQuant = args[2];
				targetName = args[3];
				
				Player p = Bukkit.getPlayer(targetName);
				
				if (p == null) {
					// Handle NPE
				}
				
				else {
					if (isFull(p)) {
						p.sendMessage(this.plugin.getMessage("coupon-fullinventory"));
						couponCmd = "treasures keys " + targetName + " " + couponType + " add " + couponQuant;
						Bukkit.dispatchCommand(console, couponCmd);
					}
				}
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
