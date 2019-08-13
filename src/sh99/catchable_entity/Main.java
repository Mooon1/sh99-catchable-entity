package sh99.catchable_entity;

import org.bukkit.plugin.java.JavaPlugin;
import sh99.catchable_entity.Command.BallCommand;
import sh99.catchable_entity.Listener.CatchListener;

public class Main extends JavaPlugin
{
    @Override
    public void onEnable() {
        this.saveConfig();

        this.getCommand("ball").setExecutor(new BallCommand());

        this.getServer().getPluginManager().registerEvents(new CatchListener(), this);
    }

    @Override
    public void onDisable() {

    }
}
