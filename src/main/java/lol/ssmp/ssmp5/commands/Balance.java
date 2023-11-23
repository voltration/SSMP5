package lol.ssmp.ssmp5.commands;

import lol.ssmp.ssmp5.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import static lol.ssmp.ssmp5.managers.BalanceManager.getBalance;
import static lol.ssmp.ssmp5.util.Format.fp;

public class Balance implements CommandExecutor {

    private final Main plugin;

    public Balance(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;
            String balance = String.valueOf(getBalance(p));

            sender.sendMessage(fp(Objects.requireNonNull(plugin.getConfig().getString("balanceMessage")
                    .replace("{balance}", balance))));
        }
        else {
            // Not a player
            sender.sendMessage(fp(Objects.requireNonNull(plugin.getConfig().getString("playerSenderMessage"))));
        }
        return true;
    }
}
