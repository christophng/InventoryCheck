package io.github.christophng.InvenCheck;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.christophng.InvenCheck.commands.CommandHandler;
import io.github.christophng.InvenCheck.commands.modules.*;

import org.bukkit.ChatColor;

public class Main extends JavaPlugin {

	public void registerCommands() {
		CommandHandler handler = new CommandHandler();
		
		handler.register("coupon", new CouponCommand(this));
		getCommand("invencheck").setExecutor(handler);
	}
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "InvenCheck starting.....");
		this.registerCommands();
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "InvenCheck shutting down.....");
		this.reload();
	}
	
	public void reload() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		
	}
	
    public String getMessage(final String path) {
        if (!this.getConfig().isString(path)) {
            return path;
        }
        return ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(path));
    }
	
}
