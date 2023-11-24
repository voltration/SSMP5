package lol.ssmp.ssmp5.functions;

import lol.ssmp.ssmp5.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Objects;

import static lol.ssmp.ssmp5.util.Format.f;

public class ConfirmationGUI implements Listener {

    private final Main plugin;

    public ConfirmationGUI(Main plugin) {
        this.plugin = plugin;
    }

    Material confirmBlock = Material.GREEN_TERRACOTTA;
    Material denyBlock = Material.RED_TERRACOTTA;


    public void confimationGUI(Player p) {

        Inventory gui = Bukkit.createInventory(null, 9, Objects.requireNonNull(plugin.getConfig().getString(f("confirmationTitle"))));

        ItemStack confirmItem = new ItemStack(confirmBlock);
        ItemStack denyItem = new ItemStack(denyBlock);
        ItemMeta confirmMeta = confirmItem.getItemMeta();
        ItemMeta denyMeta = denyItem.getItemMeta();

        confirmMeta.setDisplayName(Objects.requireNonNull(plugin.getConfig().getString(f("confirmTitleMessage"))));
        confirmMeta.setLore(Arrays.asList(Objects.requireNonNull(plugin.getConfig().getString(f("confirmTitleMessage")))));
        denyMeta.setDisplayName(Objects.requireNonNull(plugin.getConfig().getString(f("denyTitleMessage"))));
        denyMeta.setLore(Arrays.asList(Objects.requireNonNull(plugin.getConfig().getString(f("denyTitleMessage")))));

        confirmItem.setItemMeta(confirmMeta);
        denyItem.setItemMeta(denyMeta);

        gui.setItem(3, confirmItem);
        gui.setItem(5, denyItem);

        p.openInventory(gui);
    }

    @EventHandler
    public boolean clickHandler(InventoryClickEvent e) {

        if (e.getInventory().getHolder() == null && e.getView().getTitle().equals(Objects.requireNonNull(plugin.getConfig().getString(f("confirmationTitle"))))) {
            e.setCancelled(true);
        }

        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem != null && clickedItem.getType() == confirmBlock) {
            return true;
        }
        else {
            p.closeInventory();
        }
        return false;
    }
}
