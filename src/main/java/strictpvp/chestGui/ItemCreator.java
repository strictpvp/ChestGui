package strictpvp.chestGui;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemCreator {
    public static ItemStack create(
            Material item,
            int count,
            String name,
            String description,
            Map<Enchantment, Integer> enchant,
            ItemFlag... flags
    ) {
        ItemStack stack = new ItemStack(item, count);
        ItemMeta meta = stack.getItemMeta();

        if (meta != null) {
            // name
            meta.setDisplayName(name);

            // description
            List<String> lore = Arrays.asList(description.split("\n"));
            meta.setLore(lore);

            // enchant
            if (enchant != null) {
                for (Map.Entry<Enchantment, Integer> entry : enchant.entrySet()) {
                    meta.addEnchant(entry.getKey(), entry.getValue(), true);
                }
            }

            // flag
            if (flags != null) {
                meta.addItemFlags(flags);
            }

            stack.setItemMeta(meta);
        }

        return stack;
    }
}
