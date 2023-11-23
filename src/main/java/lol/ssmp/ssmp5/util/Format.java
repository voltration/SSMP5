package lol.ssmp.ssmp5.util;

import lol.ssmp.ssmp5.Main;
import org.bukkit.ChatColor;

public class Format {

    /***
     * @return formatted msg
     */
    public static String f(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    /***
     * @message Same as f function but returns a prefix
     * @return formatted msg
     */
    public static String fp(String msg) {
        return ChatColor.translateAlternateColorCodes('&', "&7[&e&lSSMP5&7] " + msg);
    }
}
