package lol.ssmp.ssmp5.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static lol.ssmp.ssmp5.Main.db;
import static lol.ssmp.ssmp5.util.Format.f;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) {

        String displayName = e.getPlayer().getDisplayName();
        String uuid = e.getPlayer().getUniqueId().toString();

        // Use a prepared statement to safely insert the UUID
        String query = "INSERT OR IGNORE INTO users(uuid) VALUES (?);";

        try (PreparedStatement preparedStatement = db.prepareStatement(query)) {
            preparedStatement.setString(1, uuid);

            // execute the prepared statement
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        e.setJoinMessage(f("&a+ " + displayName));
    }
}
