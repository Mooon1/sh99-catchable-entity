package sh99.catchable_entity.Item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sh99.item.Craftable;
import sh99.item.Item;

import java.util.ArrayList;
import java.util.List;

public interface Catchable extends Item, Craftable
{
    public int rate();

    public Particle particle();

    public default ItemStack create()
    {
        ItemStack itemStack = new ItemStack(Material.SNOWBALL, 1);

        ItemMeta itemMeta = itemStack.getItemMeta();

        if(null == itemMeta){
            return null;
        }

        if(null == this.name()){
            return null;
        }

        itemMeta.setDisplayName(this.name());
        itemMeta.setCustomModelData(this.customModelData());

        List<String> lore = new ArrayList<>();

        //0 - identifier for enums
        lore.add(ChatColor.MAGIC + this.identifier());

        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static Catchable fromItem(ItemStack itemStack)
    {
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(null == itemMeta){
            return null;
        }

        if(null == itemMeta.getLore()){
            return null;
        }

        List<String> lore = itemMeta.getLore();

        if(1 != lore.size()){
            return null;
        }

        String catchableClass = lore.get(0);

        catchableClass = catchableClass.replace(ChatColor.MAGIC+"", "");

        Catchable catchable = Catchables.valueOf(catchableClass).getCatchable();

        if(null == catchable){
            return null;
        }

        return catchable;
    }

}
