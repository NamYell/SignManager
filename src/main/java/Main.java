import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import version.DelayedTask;

public class Main extends JavaPlugin {

    public static String bukkitVersion = Bukkit.getBukkitVersion().substring(0, 4);

    @Override
    public void onEnable() {
        super.onEnable();
        getServer().getPluginManager().registerEvents(new SignPacketReader(), this);
        getServer().getPluginManager().registerEvents(new MainInventory(), this);
        getServer().getPluginManager().registerEvents(new test(), this);
        getCommand("sign").setExecutor(new Command());
        new DelayedTask(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
