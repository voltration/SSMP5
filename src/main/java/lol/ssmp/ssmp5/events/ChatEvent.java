package lol.ssmp.ssmp5.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static lol.ssmp.ssmp5.Main.db;
import static lol.ssmp.ssmp5.util.Format.f;

public class ChatEvent implements Listener {

    private static final String DEFAULT_RANK = "default";
    private static final String SUPPORTER_RANK = "supporter";
    private static final String ADMIN_RANK = "admin";

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {

        String msg = e.getMessage();
        String displayName = e.getPlayer().getDisplayName();
        String rank = null;

        try {

            String query = "SELECT rank FROM users WHERE uuid = ?;";
            PreparedStatement preparedStatement = db.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(e.getPlayer().getUniqueId()));
            ResultSet resultSet = preparedStatement.executeQuery();
            rank = resultSet.getString("rank");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        if (Objects.equals(rank, DEFAULT_RANK)) { rank = "&9"; }
        if (Objects.equals(rank, SUPPORTER_RANK)) { rank = "&6"; }
        if (Objects.equals(rank, ADMIN_RANK)) { rank = "&c"; }


        e.setFormat(f(rank + displayName + "&8: &f" + msg));
    }
}
