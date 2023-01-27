package version;

import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayInUpdateSign;
import net.minecraft.network.protocol.game.PacketPlayOutOpenSignEditor;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class V1_19 {

    public void openSign(Player p) {
        Location location = new Location(p.getWorld(), p.getLocation().getX(), 100, p.getLocation().getZ());
        Block block = location.getBlock();

        Material material = block.getType();
        block.setType(Material.OAK_SIGN);
        BlockPosition blockPosition = new BlockPosition(block.getX(), block.getY(), block.getZ());
        PacketPlayOutOpenSignEditor packetPlayOutOpenSignEditor = new PacketPlayOutOpenSignEditor(blockPosition);

        new DelayedTask(() -> {
            sendPacket(p, packetPlayOutOpenSignEditor);

            new DelayedTask(() -> {
                block.setType(material);
            }, 2);
        }, 3);
    }

    private void sendPacket(Player p, Packet packet) {
        PlayerConnection connection = ((CraftPlayer) p).getHandle().b;
        connection.a(packet);
    }

    public String[] updateSign(Object packet) throws IllegalArgumentException {
        return ((PacketPlayInUpdateSign) packet).c();
    }
}
