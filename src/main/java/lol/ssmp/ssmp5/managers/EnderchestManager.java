package lol.ssmp.ssmp5.managers;

import lol.ssmp.ssmp5.Main;
import lol.ssmp.ssmp5.util.ItemStackSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static lol.ssmp.ssmp5.managers.DatabaseManager.getField;
import static lol.ssmp.ssmp5.managers.DatabaseManager.updateOrReplaceField;
import static lol.ssmp.ssmp5.managers.GroupManager.getProgressGroup;
import static lol.ssmp.ssmp5.util.Format.f;

public class EnderchestManager implements Listener {

    private final Main plugin;

    String guiTitle;

    public EnderchestManager(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        if (e.getInventory().getType() == InventoryType.ENDER_CHEST) {
            e.setCancelled(true);

            Inventory gui;
            Player p = (Player) e.getPlayer();
            String progressGroup = getProgressGroup(p);
            guiTitle = f(plugin.getConfig().getString("enderchestName"));

            switch (progressGroup) {
                case "coal":
                    gui = Bukkit.createInventory(null, 18, guiTitle);
                    break;
                case "iron":
                    gui = Bukkit.createInventory(null, 27, guiTitle);
                    break;
                case "gold":
                    gui = Bukkit.createInventory(null, 36, guiTitle);
                    break;
                case "diamond":
                    gui = Bukkit.createInventory(null, 45, guiTitle);
                    break;
                case "netherite":
                    gui = Bukkit.createInventory(null, 54, guiTitle);
                    break;
                default:
                    gui = Bukkit.createInventory(null, 9, guiTitle);
                    break;
            }

            ItemStack[] contents = deserializeContents((String) getField(p, String.class, "enderchests", "ecContents"));
            gui.setContents(contents);

            p.openInventory(gui);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getView().getTitle().equals(guiTitle)) {
            Player p = (Player) e.getPlayer();
            saveEnderChestContents(p, e.getInventory());
        }
    }

    private void saveEnderChestContents(Player p, Inventory enderChest) {
        ItemStack[] contents = enderChest.getContents();
        updateOrReplaceField(p, String.class, "enderchests", "ecContents", serializeContents(contents));
    }

    private ItemStack[] serializeContents(ItemStack[] contents) {
        return ItemStackSerializer.serializeContents(contents);
    }

    private ItemStack[] deserializeContents(String serializedContents) {
        return ItemStackSerializer.deserializeContents(serializedContents);
    }
}