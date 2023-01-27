import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MainInventory implements Listener {

    private final Inventory inv;

    public MainInventory() {
        inv = Bukkit.createInventory(null, 36, "Store");

        ItemStack itemStack = new ItemStack(Material.OAK_SIGN);
        ItemMeta itemMeta = itemStack.getItemMeta();
        try {
            itemMeta.setDisplayName("Price : 0");
            itemStack.setItemMeta(itemMeta);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        inv.setItem(13, itemStack);
    }

    @EventHandler
    public void openInventory(OpenInventory e) {
        e.p.openInventory(inv);
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent e) {
        if (e.getInventory().equals(inv)) {
            e.setCancelled(true);

            int num = e.getRawSlot();
            switch (num) {
                case 13:
                    Player p = (Player) e.getWhoClicked();
                    if (!Command.openingSign.containsKey(p)) {
                        Command.openingSign.put(p, true);
                    } else {
                        Command.openingSign.replace(p, true);
                    }
            }
        }
    }

    @EventHandler
    public void onDragInventory(InventoryDragEvent e) {
        if (e.getInventory().equals(inv)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!Command.openingSign.containsKey(p)) {
            Command.openingSign.put(p, false);
        } else {
            Command.openingSign.replace(p, false);
        }
    }
}

class OpenInventory extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    public Player p;
    public OpenInventory(Player p) {
        this.p = p;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}