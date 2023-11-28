package lol.ssmp.ssmp5;

import lol.ssmp.ssmp5.commands.*;
import lol.ssmp.ssmp5.events.*;
import lol.ssmp.ssmp5.managers.EnderchestManager;
import lol.ssmp.ssmp5.util.GroupPrefix;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static lol.ssmp.ssmp5.util.Format.f;

public final class Main extends JavaPlugin {

    // TODO: Abstract command else spam!

    public static Connection db = null;

    String usersQuery =
            "CREATE TABLE IF NOT EXISTS users(" +
                    "uuid TEXT PRIMARY KEY," +
                    "advancements INTEGER DEFAULT 0," +
                    "rank TEXT DEFAULT 'default'," +
                    "progressRank TEXT DEFAULT ''," +
                    "balance INTEGER DEFAULT 0)";

    String enderchestQuery =
            "CREATE TABLE IF NOT EXISTS enderchests(" +
                    "uuid TEXT PRIMARY KEY," +
                    "ecContents TEXT)";

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        getLogger().info(f("SSMP5 by sanderson"));

        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        getServer().getPluginManager().registerEvents(new QuitEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);

        getServer().getPluginManager().registerEvents(new AdvancementEvent(), this);
        getServer().getPluginManager().registerEvents(new EnderchestManager(this), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(this), this);
        getServer().getPluginManager().registerEvents(new Deposit(this), this);

        getCommand("pay").setExecutor(new Pay(this));
        getCommand("balance").setExecutor(new Balance(this));
        getCommand("help").setExecutor(new Help(this));
        getCommand("pm").setExecutor(new PM(this));
        getCommand("deposit").setExecutor(new Deposit(this));

        GroupPrefix.initialize(this);

        try {
            db = DriverManager.getConnection("jdbc:sqlite:plugins/SSMP5/ssmp5.db");
            db.createStatement().execute(usersQuery);
            db.createStatement().execute(enderchestQuery);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onDisable() {
        try {
            if (db != null && !db.isClosed()) {
                db.close();
                System.out.println("Disconnected from SQLite!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
