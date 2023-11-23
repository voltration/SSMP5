package lol.ssmp.ssmp5.managers;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lol.ssmp.ssmp5.Main.db;

public class BalanceManager {


    public static int getBalance(Player p) {

        int balance = 0;

        String uuid = String.valueOf(p.getUniqueId());

        try {

            String query = "SELECT balance FROM users WHERE uuid = ?;";
            PreparedStatement preparedStatement = db.prepareStatement(query);
            preparedStatement.setString(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            balance = resultSet.getInt("balance");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return balance;
    }

    public static void subtractBalance(Player p, int amount) {

        int oldBalance = getBalance(p);
        int newBalance =  oldBalance - amount;

        String uuid = String.valueOf(p.getUniqueId());

        // TODO: send message
        if (newBalance < 0) {
            return;
        }

        try {

            String query = "UPDATE users SET balance = ? WHERE uuid = ?";
            PreparedStatement updateStatement = db.prepareStatement(query);
            updateStatement.setInt(1, newBalance);
            updateStatement.setString(2, uuid);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void addBalance(Player p, int amount) {

        int oldBalance = getBalance(p);
        int newBalance =  oldBalance + amount;

        String uuid = String.valueOf(p.getUniqueId());

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
