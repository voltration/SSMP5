package lol.ssmp.ssmp5.managers;

import org.bukkit.entity.Player;

import static lol.ssmp.ssmp5.managers.DatabaseManager.getField;
import static lol.ssmp.ssmp5.managers.DatabaseManager.setField;

public class BalanceManager {

    public static Integer getBalance(Player p) {
        return (Integer) getField(p, Integer.class, "balance");
    }

    public static void subtractBalance(Player p, int amount) {

        int oldBalance = getBalance(p);
        int newBalance =  oldBalance - amount;

        if (newBalance > 0) {
            setField(p, Integer.class, "balance", newBalance);
        }
    }

    public static void addBalance(Player p, int amount) {

        int oldBalance = getBalance(p);
        int newBalance =  oldBalance + amount;

        setField(p, Integer.class, "balance", newBalance);


    }
}
