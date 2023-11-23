package lol.ssmp.ssmp5.managers;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lol.ssmp.ssmp5.Main.db;

public class DatabaseManager {

    public static void setField(Player p, String field, Class<?> type, Object value) {

        String uuid = String.valueOf(p.getUniqueId());

        try {

            String query = "UPDATE users SET " + field + " = ? WHERE uuid = ?";
            PreparedStatement updateStatement = db.prepareStatement(query);

            if (type == Integer.class) {
                updateStatement.setInt(1, (Integer) value);
            } else if (type == String.class) {
                updateStatement.setString(1, (String) value);
            }

            updateStatement.setString(2, uuid);
            updateStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static Object getField(Player p, Class<?> type, String field) {

        Object result = null;
        String uuid = String.valueOf(p.getUniqueId());

        try {
            String query = "SELECT " + field + " FROM users WHERE uuid = ?";
            PreparedStatement preparedStatement = db.prepareStatement(query);
            preparedStatement.setString(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (type == Integer.class) {
                    result = resultSet.getInt(field);
                } else if (type == String.class) {
                    result = resultSet.getString(field);
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
