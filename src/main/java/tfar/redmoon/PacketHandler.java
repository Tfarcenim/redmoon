package tfar.redmoon;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import tfar.redmoon.clientpacket.SetFogPacket;
import tfar.redmoon.clientpacket.SetMoonPacket;

public class PacketHandler {

  public static SimpleNetworkWrapper INSTANCE = null;

  public PacketHandler() {
  }

  public static void registerMessages(String channelName) {
    INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
    registerMessages();
  }
  public static void registerMessages() {
    // Register messages which are sent from the client to the server here:
    INSTANCE.registerMessage(SetMoonPacket.Handler.class, SetMoonPacket.class, 0, Side.CLIENT);
    INSTANCE.registerMessage(SetFogPacket.Handler.class, SetFogPacket.class, 1, Side.CLIENT);
  }
}