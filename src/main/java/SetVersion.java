import net.minecraft.network.protocol.game.PacketPlayInUpdateSign;
import org.bukkit.entity.Player;
import version.V1_19;

public class SetVersion {

    private String version;
    private Player p;

    public SetVersion(String version, Player p) {
        this.version = version;
        this.p = p;
    }

    public void openSign(Player p) {
        V1_19 v1_19 = new V1_19();
        v1_19.openSign(p);

    }

    public String[] signPacketUpdate(Object packet) throws IllegalArgumentException {

        if (version.equals("1.19")) {
            V1_19 v1_19 = new V1_19();
            return v1_19.updateSign(packet);
        } else {
            throw new IllegalArgumentException("This is not a bug");
        }

    }

    public Player getPlayer() {
        return p;
    }

    public void setPlayer(Player p) {
        this.p = p;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
