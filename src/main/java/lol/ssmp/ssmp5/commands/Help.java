package lol.ssmp.ssmp5.commands;

import lol.ssmp.ssmp5.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

import static lol.ssmp.ssmp5.util.Format.f;

public class Help implements CommandExecutor {

    private final Main plugin;

    public Help(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        sender.sendMessage(f(Objects.requireNonNull(plugin.getConfig().getString("helpMessage"))));

        return false;
    }
}
