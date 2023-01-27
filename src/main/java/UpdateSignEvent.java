import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UpdateSignEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private String[] str;
    private Player p;

    public UpdateSignEvent(String[] str, Player p) {
        this.str = str;
        this.p = p;
    }

    public String[] getString() {
        return str;
    }

    public Player getPlayer() {
        return p;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
