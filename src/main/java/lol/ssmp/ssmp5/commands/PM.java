package lol.ssmp.ssmp5.commands;

import lol.ssmp.ssmp5.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import static lol.ssmp.ssmp5.util.Format.fp;

public class PM implements CommandExecutor {

    private final Main plugin;

    public PM(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("ssmp.pm")) {
                if (args.length >= 1) {

                    Player player = (Player) sender;
                    Player recipient = Bukkit.getPlayer(args[0]);

                    if (recipient != null) {
                        if (sender != recipient) {
                            StringBuilder argBuilder = new StringBuilder();

                            for (String arg : args) {
                                argBuilder.append(arg).append(" ");
                            }

                            String msg = argBuilder.toString().trim();

                            recipient.sendMessage(fp(Objects.requireNonNull(plugin.getConfig().getString("payRecipientMessage")
                                    .replace("{recipient}", recipient.getDisplayName())
                                    .replace("{sender}", player.getDisplayName())
                                    .replace("{msg}", msg))));
                        }
                        else {
                            // Recipient cannot be sender
                            sender.sendMessage(fp(Objects.requireNonNull(plugin.getConfig().getString("wrongRecipientMessage"))));
                        }
                    }
                    else {
                        // Cannot find player
                        sender.sendMessage(fp(Objects.requireNonNull(plugin.getConfig().getString("noPlayerMessage"))));
                    }
                }
                else {
                    // Wrong usage
                    sender.sendMessage(fp(Objects.requireNonNull(plugin.getConfig().getString("payUsageMessage"))));
                }
            }
            else {
                // No permission
                sender.sendMessage(fp(Objects.requireNonNull(plugin.getConfig().getString("noPermissionMessage"))));
            }
        }
        else {
            // Not a player
            sender.sendMessage(fp(Objects.requireNonNull(plugin.getConfig().getString("playerSenderMessage"))));
        }
        return false;
    }
}
