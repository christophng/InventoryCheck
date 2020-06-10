package io.github.christophng.InvenCheck.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.christophng.InvenCheck.commands.modules.CouponCommand;

import java.util.HashMap;
 
public class CommandHandler implements CommandExecutor {
 
    private static HashMap<String, CommandInterface> commands = new HashMap<String, CommandInterface>();
 
    public boolean exists(String name) {
 
        return commands.containsKey(name);
    }
 
    /**
     * 
     * @param name Name of the command
     * @return The command executor object linked to the command name in the hashmap
     */
    public CommandInterface getExecutor(String name) {
 
        return commands.get(name);
    }
 
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
 
        if(sender instanceof Player) {
 
            if(args.length == 0) {
                return true;
            }
 
            if(args.length > 0) {
 
                if(exists(args[0])){
 
                    getExecutor(args[0]).onCommand(sender, cmd, commandLabel, args);
                    return true;
                } else {
 
                    sender.sendMessage("This command doesn't exist!");
                    return true;
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
            return true;
        }
        return false;
    }

    public void register(String name, CommandInterface cmd) {
    	 
        commands.put(name, cmd);
        
    }
}
