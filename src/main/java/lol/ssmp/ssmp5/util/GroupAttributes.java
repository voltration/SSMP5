package lol.ssmp.ssmp5.util;

import java.util.Objects;

public class GroupAttributes {

    public static String getGroupPrefix(String group) {

        String prefix = null;

        if (Objects.equals(group, "default")) { prefix = "&9"; }
        if (Objects.equals(group, "supporter")) { prefix = "&e"; }
        if (Objects.equals(group, "admin")) { prefix = "&c"; }

        return prefix;
    }
}
