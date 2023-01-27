import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class test implements Listener {

    @EventHandler
    public void onUpdateSign(UpdateSignEvent e) {
        e.getPlayer().sendMessage(e.getString());
    }
}
