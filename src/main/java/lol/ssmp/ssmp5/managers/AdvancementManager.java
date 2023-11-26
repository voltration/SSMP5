package lol.ssmp.ssmp5.managers;

import org.bukkit.entity.Player;

import static lol.ssmp.ssmp5.managers.DatabaseManager.getField;
import static lol.ssmp.ssmp5.managers.DatabaseManager.setField;

public class AdvancementManager {

    public static void addAdvancements(Player p, int amount) {

        int oldAdvancementCount = (int) getField(p, Integer.class, "advancements");
        int newAdvancementCount = oldAdvancementCount + amount;

        setField(p, Integer.class, "advancements", newAdvancementCount);
    }

    public static int getAdvancements(Player p) {
        return (int) getField(p, Integer.class, "advancements");
    }
}
