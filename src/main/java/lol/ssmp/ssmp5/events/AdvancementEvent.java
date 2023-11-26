package lol.ssmp.ssmp5.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import static lol.ssmp.ssmp5.managers.AdvancementManager.addAdvancements;
import static lol.ssmp.ssmp5.managers.AdvancementManager.getAdvancements;
import static lol.ssmp.ssmp5.managers.GroupManager.setProgressGroup;

public class AdvancementEvent implements Listener {

    /*
    ranks:
    |>> default / supporter / admin
    |>> coal / iron / gold / diamond / netherite :: awarded for every 22 achievements out of 110
     */

    @EventHandler
    public void onAdvancementEvent(PlayerAdvancementDoneEvent e) {

        Player p = e.getPlayer();

        addAdvancements(p, 1);

        int getAdvancements = getAdvancements(p);

        System.out.println(getAdvancements);

        switch (getAdvancements) {
            case 22:
                setProgressGroup(p, "coal");
                break;
            case 44:
                setProgressGroup(p, "iron");
                break;
            case 66:
                setProgressGroup(p, "gold");
                break;
            case 88:
                setProgressGroup(p, "diamond");
                break;
            case 110:
                setProgressGroup(p, "netherite");
                break;
        }

    }
}
