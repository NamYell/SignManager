import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_19_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Command implements CommandExecutor {

    public static Map<Player, Boolean> openingSign = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            Player p = (Player) commandSender;

            String version = Bukkit.getVersion().substring(0, 4);
            SetVersion setversion = new SetVersion(version, p);
            setversion.openSign(p);

        }

        return false;
    }
}
