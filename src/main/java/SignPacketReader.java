import io.netty.channel.*;
import net.minecraft.network.protocol.game.PacketPlayInUpdateSign;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import version.V1_19;

public class SignPacketReader implements Listener {

    String[] str;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        injectPlayer(e.getPlayer());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        removePlayer(e.getPlayer());
    }

    private void removePlayer(Player p) {
        Channel channel = ((CraftPlayer) p).getHandle().b.b.m;
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(p.getName());
            return null;
        });
    }

    private void injectPlayer(Player p) {
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler(){

            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
                /**Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "Packet Read "
                 + ChatColor.RED + packet.toString());**/
                if (Main.bukkitVersion.equals("1.19")) {
                    if (packet instanceof PacketPlayInUpdateSign) {
                        SetVersion setversion = new SetVersion(Main.bukkitVersion, p);

                        try {
                            new version.DelayedTask(() -> {
                                Bukkit.getServer().getPluginManager().callEvent(new UpdateSignEvent(setversion.signPacketUpdate(packet), p));
                            }, 1);
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                    }
                }

                super.channelRead(channelHandlerContext, packet);
            }

            @Override
            public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {
                /**Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Packet Write "
                 + ChatColor.GREEN + packet.toString());**/
                super.write(channelHandlerContext, packet, channelPromise);
            }
        };

        ChannelPipeline pipeline = ((CraftPlayer) p).getHandle().b.b.m.pipeline();
        pipeline.addBefore("packet_handler", p.getName(), channelDuplexHandler);
    }

    public String[] getString() {
        return str;
    }
}
