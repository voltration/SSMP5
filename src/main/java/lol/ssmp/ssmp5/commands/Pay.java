package lol.ssmp.ssmp5.commands;

import lol.ssmp.ssmp5.Main;
import lol.ssmp.ssmp5.functions.ConfirmationGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import static lol.ssmp.ssmp5.managers.BalanceManager.addBalance;
import static lol.ssmp.ssmp5.managers.BalanceManager.subtractBalance;
import static lol.ssmp.ssmp5.util.Format.fp;

public class Pay implements CommandExecutor {

    private final Main plugin;
    private final ConfirmationGUI confirmationGUI;

    public Pay(Main plugin) {
        this.plugin = plugin;
        this.confirmationGUI = new ConfirmationGUI(plugin);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("ssmp.pay")) {

                if (args.length >= 1) {

                    Player recipient = Bukkit.getPlayer(args[0]);
                    Player player = (Player) sender;

                    int amount = Integer.parseInt(args[1]);

                    if (recipient != null) {
                        if (recipient != player) {

                            // transaction
                            confirmationGUI.confimationGUI(player);

                            sender.sendMessage(fp(Objects.requireNonNull(plugin.getConfig().getString("paySenderMessage")
                                    .replace("{amount}", String.valueOf(amount))
                                    .replace("{player}", recipient.getDisplayName()))));

                                recipient.sendMessage(fp(Objects.requireNonNull(plugin.getConfig().getString("payRecipientMessage")
                                    .replace("{amount}", String.valueOf(amount))
                                    .replace("{player}", player.getDisplayName()))));
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
        return true;
    }
}
