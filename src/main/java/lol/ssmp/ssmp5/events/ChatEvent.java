package lol.ssmp.ssmp5.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static lol.ssmp.ssmp5.util.Format.f;
import static lol.ssmp.ssmp5.util.GroupPrefix.getPlayerGroupColor;
import static lol.ssmp.ssmp5.util.GroupPrefix.getPlayerGroupPrefix;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {

        String msg = e.getMessage();
        Player p = e.getPlayer();

        String displayName = e.getPlayer().getDisplayName();
        String groupProgressColor = getPlayerGroupColor(p);
        String progressGroup = getPlayerGroupPrefix(p);

        e.setFormat(f(progressGroup + groupProgressColor + " " + displayName + "&8: &f" + msg));
    }
}
