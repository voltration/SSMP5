package lol.ssmp.ssmp5.events;

import lol.ssmp.ssmp5.Main;
import lol.ssmp.ssmp5.util.YamlGetter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static lol.ssmp.ssmp5.util.Format.fp;

public class DeathEvent implements Listener {

    private final Main plugin;
    private final YamlGetter yamlGetter;

    public DeathEvent(Main plugin) {
        this.plugin = plugin;
        this.yamlGetter = new YamlGetter(plugin);
    }

    @EventHandler
    public void onDeathEvent(PlayerDeathEvent e) {

        Player p = (Player) e.getEntity();
        String deathMessage = yamlGetter.getString("deathMessage");
        String deathMessageTimer = yamlGetter.getString("deathMessageTimer");

        String x = String.valueOf(Math.floor(p.getLocation().getX()));
        String y = String.valueOf(Math.floor(p.getLocation().getY()));
        String z = String.valueOf(Math.floor(p.getLocation().getZ()));

        p.sendMessage(fp(deathMessage)
                .replace("{x}", x)
                .replace("{y}", y)
                .replace("{z}", z));

        p.sendMessage(fp(deathMessageTimer));
    }
}
