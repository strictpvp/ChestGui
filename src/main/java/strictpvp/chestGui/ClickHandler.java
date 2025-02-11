package strictpvp.chestGui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.HashMap;
import java.util.Map;

public class ClickHandler implements Listener {
    public static final Map<Player, ChestGui> openInventories = new HashMap<>();

    @EventHandler
    public void clickHandler(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (openInventories.containsKey(player)) {
            event.setCancelled(true);

            ChestGui gui = openInventories.get(player);

            if (gui.leftClickHandler.get(event.getSlot()) == null)
                return;

            switch (event.getAction()) {
                case PICKUP_ALL: {
                    gui.leftClickHandler.get(event.getSlot()).accept(event);
                    break;
                }
                case PICKUP_HALF: {
                    gui.rightClickHandler.get(event.getSlot()).accept(event);
                    break;
                }
                case DROP_ALL_SLOT:
                case DROP_ONE_SLOT: {
                    gui.dropHandler.get(event.getSlot()).accept(event);
                    break;
                }
                case CLONE_STACK: {
                    gui.cloneHandler.get(event.getSlot()).accept(event);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void closeHandler(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        openInventories.remove(player);
    }
}
