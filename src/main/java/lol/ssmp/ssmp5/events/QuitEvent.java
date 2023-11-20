package lol.ssmp.ssmp5.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static lol.ssmp.ssmp5.util.Format.f;

public class QuitEvent implements Listener {

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent e) {

        String displayName = e.getPlayer().getDisplayName();

        e.setQuitMessage(f("&c- ") + displayName);
    }
}
