package lol.ssmp.ssmp5.commands;

import lol.ssmp.ssmp5.managers.BalanceManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lol.ssmp.ssmp5.Main.db;
import static lol.ssmp.ssmp5.managers.BalanceManager.*;

public class Pay implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("ssmp.pay")) {
                Player p = (Player) sender;

                if (args.length >= 1) {
                    try {
                        int amount = Integer.parseInt(args[1]);
                        subtractBalance(p, amount);
                        addBalance((args[0]);
                        sender.sendMessage("Paid " + amount + " from your balance. New balance: " + getBalance(p));
                    } catch (NumberFormatException e) {
                        sender.sendMessage("Invalid amount. Please provide a valid number.");
                    }
                } else {
                    sender.sendMessage("Usage: /pay <amount>");
                }
            } else {
                sender.sendMessage("No perms");
            }
        } else {
            sender.sendMessage("Sender not an instance of player!");
        }
        return true;
    }
}
