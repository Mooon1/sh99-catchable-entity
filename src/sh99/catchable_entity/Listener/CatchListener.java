package sh99.catchable_entity.Listener;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.inventory.ItemStack;

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
    public void onCatchThrowableHit(ProjectileHitEvent event)
    {
        if(!(event.getEntity() instanceof Snowball)){
            return;
        }

        Snowball snowball = (Snowball) event.getEntity();

        event.getEntity().getServer().broadcastMessage(snowball.getName());

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

        if((new Random().nextInt(100)) > 10){
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
        hitEntity.getWorld().spawnParticle(Particle.TOTEM, hitEntity.getLocation(), 100);
        event.getHitEntity().remove();
        event.getHitEntity().setCustomNameVisible(true);
        event.getHitEntity().setCustomName(hitEntity.getName());
    }
}
