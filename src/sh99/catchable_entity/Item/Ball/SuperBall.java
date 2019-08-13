package sh99.catchable_entity.Item.Ball;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import sh99.catchable_entity.Item.Catchable;

public class SuperBall implements Catchable
{
    @Override
    public int rate() {
        return 20;
    }

    @Override
    public Particle particle() {
        return Particle.BUBBLE_POP;
    }

    @Override
    public String name() {
        return ChatColor.BLUE + "Super " + ChatColor.WHITE + " Ball";
    }

    @Override
    public String identifier() {
        return "SUPERBALL";
    }

    @Override
    public int customModelData() {
        return 0;
    }

    @Override
    public String namespace() {
        return "ball_super";
    }

    @Override
    public ShapedRecipe recipe(String s, ItemStack itemStack, Plugin plugin) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, namespace());
        ShapedRecipe recipe = new ShapedRecipe(namespacedKey, create());
        recipe.shape("LRL", "WBW", "LSL");
        recipe.setIngredient('L', Material.LAPIS_LAZULI);
        recipe.setIngredient('R', Material.REDSTONE);
        recipe.setIngredient('L', Material.LAPIS_LAZULI);
        recipe.setIngredient('W', Material.PUMPKIN_SEEDS);
        recipe.setIngredient('B', Material.STONE_BUTTON);
        recipe.setIngredient('W', Material.PUMPKIN_SEEDS);
        recipe.setIngredient('L', Material.LAPIS_LAZULI);
        recipe.setIngredient('L', Material.LAPIS_LAZULI);
        recipe.setIngredient('L', Material.LAPIS_LAZULI);
        return recipe;
    }

}
