package sh99.catchable_entity.Item.Ball;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import sh99.catchable_entity.Item.Catchable;

public class MineBall implements Catchable
{
    @Override
    public int rate() {
        return 10;
    }

    @Override
    public Particle particle() {
        return Particle.TOTEM;
    }

    @Override
    public String name() {
        return ChatColor.GREEN + "Mine " + ChatColor.WHITE + " Ball";
    }

    @Override
    public String identifier() {
        return "MINEBALL";
    }

    @Override
    public int customModelData() {
        return 0;
    }

    @Override
    public String namespace() {
        return "ball_mine";
    }

    @Override
    public ShapedRecipe recipe(String s, ItemStack itemStack, Plugin plugin) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, namespace());
        ShapedRecipe recipe = new ShapedRecipe(namespacedKey, create());
        recipe.shape("IRI", "WBW", "ISI");
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('R', Material.REDSTONE);
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('W', Material.WHEAT_SEEDS);
        recipe.setIngredient('B', Material.STONE_BUTTON);
        recipe.setIngredient('W', Material.WHEAT_SEEDS);
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('I', Material.IRON_INGOT);
        return recipe;
    }

}
