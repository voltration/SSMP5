package lol.ssmp.ssmp5.util;

import lol.ssmp.ssmp5.Main;

public class YamlGetter {

    private final Main plugin;

    public YamlGetter(Main plugin) {
        this.plugin = plugin;
    }

    public String getString(String arg) {
        if (plugin.getConfig().getString(arg) != null) {
            return plugin.getConfig().getString(arg);
        }
        else {
            return "No string found";
        }
    }
}
