package lol.ssmp.ssmp5.managers;

import org.bukkit.entity.Player;

import static lol.ssmp.ssmp5.managers.DatabaseManager.getField;
import static lol.ssmp.ssmp5.managers.DatabaseManager.setField;

public class GroupManager {

    public static String getGroup(Player p) {
        return (String) getField(p, String.class, "rank");
    }

    public static void setGroup(Player p, String group) {
        setField(p, "rank", String.class, group);
    }

}
