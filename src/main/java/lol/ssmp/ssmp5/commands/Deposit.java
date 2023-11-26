package lol.ssmp.ssmp5.commands;

import lol.ssmp.ssmp5.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static lol.ssmp.ssmp5.managers.BalanceManager.addBalance;
import static lol.ssmp.ssmp5.util.Format.f;
import static lol.ssmp.ssmp5.util.Format.fp;
import static org.bukkit.Instrument.DIDGERIDOO;

public class Deposit implements CommandExecutor, Listener {

    private final Main plugin;

    public Deposit(Main plugin) {
        this.plugin = plugin;
        guiTitle = f(plugin.getConfig().getString("depositTitle"));
    }


    Material fillerMaterial = Material.GRAY_STAINED_GLASS_PANE;
    String guiTitle;
    Inventory gui;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;

            openDepositGUI(p);

        }
        return false;
    }

    public void openDepositGUI(Player p) {
        gui = Bukkit.createInventory(null, 9, guiTitle);

        for (int i = 0; i < gui.getSize(); i++) {
            if (i != 4) {

                ItemStack fillerItem = new ItemStack(fillerMaterial);
                ItemMeta fillerMeta = fillerItem.getItemMeta();

                fillerMeta.setDisplayName(" ");
                fillerItem.setItemMeta(fillerMeta);

                gui.setItem(i, fillerItem);
            }
        }
        p.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        // TODO: vulnerability - players can rename a chest to mimic the GUI
        if (e.getView().getTitle().equals(guiTitle) && clickedItem != null) {
            if (clickedItem.getType() != Material.DIAMOND) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1f, 0.1f);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {

        Player p = (Player) e.getPlayer();
        int diamondCount = 0;

        if (e.getView().getTitle().equals(guiTitle)) {
            Inventory inventory = e.getInventory();

            for (ItemStack item : inventory.getContents()) {
                if (item != null && item.getType() == Material.DIAMOND) {
                    diamondCount += item.getAmount();
                }
            }

            if (diamondCount > 0) {

                int diamondConversion = diamondCount * 100;

                addBalance(p, diamondConversion);
                p.sendMessage(fp(Objects.requireNonNull(plugin.getConfig().getString("depositMessage"))
                    .replace("{amount}", String.valueOf(diamondConversion))));
            }
        }
    }
}
