package me.controls.disarm.hub.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        }
        if(cmd.getName().equalsIgnoreCase("fly")) {
            Player player = (Player) sender;
            if(!sender.hasPermission("hub.command.fly") && !sender.isOp()) {
                sender.sendMessage("§4Invalid permissions");
            } else {
                player.setAllowFlight(true);
            }
        }
        return true;
    }
}
