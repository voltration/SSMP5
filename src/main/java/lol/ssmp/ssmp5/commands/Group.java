package lol.ssmp.ssmp5.commands;

import lol.ssmp.ssmp5.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import static lol.ssmp.ssmp5.managers.GroupManager.setGroup;
import static lol.ssmp.ssmp5.managers.GroupManager.setProgressGroup;
import static lol.ssmp.ssmp5.util.Format.f;

public class Group implements CommandExecutor {

    private final Main plugin;

    public Group(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("ssmp.admin")) {
            if (args.length >= 1) {

                Player p = Bukkit.getPlayer(args[0]);

                String pName = p.getDisplayName();
                String group = args[1];

                String msg = Objects.requireNonNull(plugin.getConfig().getString("groupSuccessMessage"))
                        .replace("{player}", pName)
                        .replace("{group}", group);

                sender.sendMessage(f(msg));
                setProgressGroup(p, group);
            }
            else {
                plugin.getConfig().getString("groupUsageMessage");
            }
        }

        return false;
    }
}
