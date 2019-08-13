package sh99.catchable_entity.Item.Ball;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import sh99.catchable_entity.Item.Catchable;

public class HyperBall implements Catchable
{
    @Override
    public int rate() {
        return 40;
    }

    @Override
    public Particle particle() {
        return Particle.PORTAL;
    }

    @Override
    public String name() {
        return ChatColor.BLACK + "Hyper " + ChatColor.WHITE + " Ball";
    }

    @Override
    public String identifier() {
        return "HYPERBALL";
    }

    @Override
    public int customModelData() {
        return 0;
    }

    @Override
    public String namespace() {
        return "ball_hyper";
    }

    @Override
    public ShapedRecipe recipe(String s, ItemStack itemStack, Plugin plugin) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, namespace());
        ShapedRecipe recipe = new ShapedRecipe(namespacedKey, create());
        recipe.shape("GRG", "ABA", "GSG");
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('R', Material.REDSTONE);
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('A', Material.GOLDEN_APPLE);
        recipe.setIngredient('B', Material.STONE_BUTTON);
        recipe.setIngredient('A', Material.GOLDEN_APPLE);
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('G', Material.GOLD_INGOT);
        return recipe;
    }

}
