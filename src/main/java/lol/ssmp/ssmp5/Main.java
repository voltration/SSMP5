package lol.ssmp.ssmp5;

import lol.ssmp.ssmp5.events.ChatEvent;
import lol.ssmp.ssmp5.events.JoinEvent;
import lol.ssmp.ssmp5.events.QuitEvent;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static lol.ssmp.ssmp5.util.Format.f;

public final class Main extends JavaPlugin {

    public static Connection db = null;

    String query =
            "CREATE TABLE IF NOT EXISTS users(" +
            "uuid TEXT PRIMARY KEY," +
            "rank TEXT DEFAULT 'default'," +
            "money INTEGER DEFAULT 0)";

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        getLogger().info(f("SSMP5 by sanderson"));

        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        getServer().getPluginManager().registerEvents(new QuitEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);

        try {
            db = DriverManager.getConnection("jdbc:sqlite:plugins/SSMP5/ssmp5.db");

            System.out.println("Connected to SQLite!");
            db.createStatement().execute(query);

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
