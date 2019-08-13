package sh99.catchable_entity.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import sh99.catchable_entity.Item.Catchable;
import sh99.catchable_entity.Item.Catchables;

public class BallCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        if(!player.isOp() || !player.getName().equals("Xsrf")){
            return false;
        }

        if(args.length <= 0){
            return false;
        }

        for(Catchables catchables:Catchables.values()){
            if(!args[0].equals(catchables.getCatchable().identifier())){
                continue;
            }

            Catchable catchable = catchables.getCatchable();

            ItemStack ball = catchable.create();
            ball.setAmount(16);

            player.getInventory().addItem(ball);
            player.updateInventory();
        }
        return true;
    }
}
