package lol.ssmp.ssmp5.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class Pay implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {

            if (sender.hasPermission("ssmp.pay")) {

            }
            sender.sendMessage("Sender not an instance of player!");
        }
        return true;
    }
}
