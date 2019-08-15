package sh99.catchable_entity.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import sh99.catchable_entity.Item.Catchable;
import sh99.catchable_entity.Item.Catchables;

import java.util.Random;

public class CatchListener implements Listener
{
    private static final String THROWABLE_NAME = "CATCH_THROWABLE";

    @EventHandler(priority = EventPriority.NORMAL)
    public void onCatch(PlayerEggThrowEvent event)
    {
        event.setHatching(false);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onThrowCatchable(PlayerInteractEvent event)
    {
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        if(!item.getType().equals(Material.SNOWBALL)){
            return;
        }

        Catchable catchable = Catchable.fromItem(item);

        if(null == catchable){
            return;
        }

        event.setCancelled(true);

        int amount = item.getAmount();
        --amount;

        if(amount <= 0){
            item = new ItemStack(Material.AIR);
            amount = 1;
        }

        item.setAmount(amount);
        event.getPlayer().getInventory().setItemInMainHand(item);

        Location location = event.getPlayer().getLocation().clone();
        location.setY(location.getY()+2);

        Snowball snowball = (Snowball) event.getPlayer().getWorld().spawnEntity(location, EntityType.SNOWBALL);
        snowball.setVelocity(event.getPlayer().getLocation().getDirection().multiply(5.0F));
        snowball.setCustomName(catchable.identifier());
        snowball.setShooter(event.getPlayer());
        snowball.setCustomNameVisible(false);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onCatchThrowableHit(ProjectileHitEvent event)
    {
        if(!(event.getEntity() instanceof Snowball)){
            return;
        }

        if(!(event.getEntity().getShooter() instanceof Player)){
            event.getEntity().getServer().broadcastMessage("LOL2");
            return;
        }

        Player shooter = (Player) event.getEntity().getShooter();

        Snowball snowball = (Snowball) event.getEntity();

        String identifier = snowball.getCustomName();

        try {
            Catchable catchable = Catchables.valueOf(identifier).getCatchable();

            Entity hitEntity = event.getHitEntity();

            if(null == hitEntity){
                return;
            }

            if(hitEntity instanceof Player){
                return;
            }

            if(!(hitEntity instanceof Creature)){
                return;
            }

            if((new Random().nextInt(100)) > catchable.rate()){
                shooter.sendMessage(ChatColor.RED + "Failed to catch a wild " + ChatColor.GOLD + hitEntity.getName() + ChatColor.RED + " with a " + catchable.name() + ChatColor.RED + ".");
                return;
            }

            String spawnEggName = hitEntity.getType().toString() +"_SPAWN_EGG";

            Material material;

            try {
                material = Material.valueOf(spawnEggName);
            }catch (IllegalArgumentException e){
                return;
            }

            ItemStack itemStack = new ItemStack(material, 1);

            hitEntity.getWorld().dropItem(hitEntity.getLocation(), itemStack);
            ((Creature) hitEntity).setLastDamage(0);
            hitEntity.getWorld().spawnParticle(catchable.particle(), hitEntity.getLocation(), 100);
            event.getHitEntity().remove();
            event.getHitEntity().setCustomNameVisible(true);
            event.getHitEntity().setCustomName(hitEntity.getName());

            shooter.sendMessage(ChatColor.DARK_PURPLE + "You catched a wild " + ChatColor.GOLD + hitEntity.getName() + ChatColor.DARK_PURPLE + " with a " + catchable.name() + ChatColor.DARK_PURPLE + ".");
        }catch (IllegalArgumentException e){
        }
    }
}
