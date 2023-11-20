package lol.ssmp.ssmp5.managers;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lol.ssmp.ssmp5.Main.db;

public class BalanceManager {

    public static int getBalance(String p) {

        int balance = 0;

        try {

            String query = "SELECT balance FROM users WHERE uuid = ?;";
            PreparedStatement preparedStatement = db.prepareStatement(query);
            preparedStatement.setString(1, p);
            ResultSet resultSet = preparedStatement.executeQuery();
            balance = resultSet.getInt("balance");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return balance;
    }

    public static void subtractBalance(String p, int amount) {

        int oldBalance = getBalance(p);
        int newBalance =  oldBalance - amount;

        try {

            String query = "UPDATE users SET balance = ? WHERE uuid = ?";
            PreparedStatement updateStatement = db.prepareStatement(query);
            updateStatement.setInt(1, newBalance);
            updateStatement.setString(2, p);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void addBalance(Player p, int amount) {

        String uuid = String.valueOf(p.getUniqueId());
        int oldBalance = getBalance(p);
        int newBalance =  oldBalance + amount;

        try {

            String query = "UPDATE users SET balance = ? WHERE uuid = ?";
            PreparedStatement updateStatement = db.prepareStatement(query);
            updateStatement.setInt(1, newBalance);
            updateStatement.setString(2, uuid);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
