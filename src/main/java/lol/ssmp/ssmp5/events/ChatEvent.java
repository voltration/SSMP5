package lol.ssmp.ssmp5.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

import static lol.ssmp.ssmp5.managers.GroupManager.getGroup;
import static lol.ssmp.ssmp5.util.Format.f;

public class ChatEvent implements Listener {

    private static final String DEFAULT_RANK = "default";
    private static final String SUPPORTER_RANK = "supporter";
    private static final String ADMIN_RANK = "admin";

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {

        String msg = e.getMessage();
        String displayName = e.getPlayer().getDisplayName();
        String group = getGroup(e.getPlayer());

        if (Objects.equals(group, DEFAULT_RANK)) { group = "&9"; }
        if (Objects.equals(group, SUPPORTER_RANK)) { group = "&6"; }
        if (Objects.equals(group, ADMIN_RANK)) { group = "&c"; }


        e.setFormat(f(group + displayName + "&8: &f" + msg));
    }
}
