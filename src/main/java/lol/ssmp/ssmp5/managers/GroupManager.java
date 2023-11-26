package lol.ssmp.ssmp5.managers;

import org.bukkit.entity.Player;

import static lol.ssmp.ssmp5.managers.DatabaseManager.getField;
import static lol.ssmp.ssmp5.managers.DatabaseManager.setField;

public class GroupManager {

    /*
    ranks:
    |>> default / supporter / admin
    |>> coal / iron / gold / diamond / netherite :: awarded for every 22 achievements out of 110
     */

    public static String getGroup(Player p) {
        return (String) getField(p, String.class, "rank");
    }

    public static void setGroup(Player p, String group) {
        setField(p, String.class, "rank", group);
    }

    public static String getProgressGroup(Player p) {
        return (String) getField(p, String.class, "progressRank");
    }

    public static void setProgressGroup(Player p, String group) {
        setField(p, String.class, "progressRank", group);
    }

}
