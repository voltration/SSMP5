package lol.ssmp.ssmp5.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

import static lol.ssmp.ssmp5.managers.GroupManager.getGroup;
import static lol.ssmp.ssmp5.util.Format.f;
import static lol.ssmp.ssmp5.util.GroupAttributes.getGroupPrefix;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {

        String msg = e.getMessage();
        String displayName = e.getPlayer().getDisplayName();
        String prefix = getGroupPrefix(getGroup(e.getPlayer()));

        e.setFormat(f(prefix + displayName + "&8: &f" + msg));
    }
}
