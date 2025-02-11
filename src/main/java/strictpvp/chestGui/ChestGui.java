package strictpvp.chestGui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ChestGui implements Listener {
    private final String title;
    private final Rows row;
    public final Map<Integer, ItemStack> items = new HashMap<>();
    public final Map<Integer, Consumer<InventoryClickEvent>> leftClickHandler = new HashMap<>();
    public final Map<Integer, Consumer<InventoryClickEvent>> rightClickHandler = new HashMap<>();
    public final Map<Integer, Consumer<InventoryClickEvent>> dropHandler = new HashMap<>();
    public final Map<Integer, Consumer<InventoryClickEvent>> cloneHandler = new HashMap<>();

    public static void register(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(new ClickHandler(), plugin);
    }

    public ChestGui(String title, Rows row) {
        this.title = title;
        this.row = row;
    }

    /**
     * item add method
     *
     * @param handler first handler: left click / second: right click / third: drop / fourth: clone (on creative mode)
     */
    public void addItem(Integer slot, ItemStack itemStack, Consumer<InventoryClickEvent>... handler) {
        items.put(slot, itemStack);

        if (handler != null) {
            leftClickHandler.put(slot, handler[0]);

            switch (handler.length) {
                case 1:
                    rightClickHandler.put(slot, handler[0]);
                    break;
                case 2:
                    rightClickHandler.put(slot, handler[1]);
                    break;
                case 3:
                    rightClickHandler.put(slot, handler[1]);
                    dropHandler.put(slot, handler[2]);
                    break;
                case 4:
                    rightClickHandler.put(slot, handler[1]);
                    dropHandler.put(slot, handler[2]);
                    cloneHandler.put(slot, handler[3]);
            }
        }
    }

    public void execute(Player player) {
        Inventory gui = Bukkit.createInventory(null, row.getMaxSlot(), title);
        items.forEach(gui::setItem);

        player.openInventory(gui);
        ClickHandler.openInventories.put(player, this);
    }
}