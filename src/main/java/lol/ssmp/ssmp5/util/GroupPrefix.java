package lol.ssmp.ssmp5.util;

import lol.ssmp.ssmp5.Main;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lol.ssmp.ssmp5.managers.GroupManager.getGroup;
import static lol.ssmp.ssmp5.managers.GroupManager.getProgressGroup;

public class GroupPrefix {

    static YamlGetter yamlGetter;

    public static void initialize(Main plugin) {
        yamlGetter = new YamlGetter(plugin);
    }

    public static String getPlayerGroupPrefix(Player p) {

        String result = null;

        String playerGroup = getGroup(p);
        String playerProgressGroup = getProgressGroup(p);
        String replaceQuery = yamlGetter.getString(playerProgressGroup);

        switch (playerGroup) {
            case "default":
                result = yamlGetter.getString("defaultBrackets").replace("{group}", replaceQuery);
                break;
            case "supporter":
                result = yamlGetter.getString("supporterBrackets").replace("{group}", replaceQuery);
                break;
            case "admin":
                result = yamlGetter.getString("adminBrackets").replace("{group}", replaceQuery);
                break;
        }
        return result;
    }

    public static String getPlayerGroupColor(Player p) {

        String playerProgressGroup = getProgressGroup(p);
        String playerGroup = yamlGetter.getString(playerProgressGroup);

        if (playerGroup != null && !playerGroup.isEmpty()) {
            Pattern pattern = Pattern.compile("^&(.).*");
            Matcher matcher = pattern.matcher(playerGroup);

            if (matcher.find()) {
                return "&" + matcher.group(1).toLowerCase();
            }
        }

        return yamlGetter.getString("default");
    }
}