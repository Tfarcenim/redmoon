package tfar.redmoon.clientpacket;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tfar.redmoon.RedMoon;

public class SetFogPacket implements IMessage {

  private double fog;

  public SetFogPacket() {
  }

  public SetFogPacket(double fog) {
    this.fog = fog;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    fog = buf.readDouble();
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeDouble(fog);
  }

  public static class Handler implements IMessageHandler<SetFogPacket, IMessage> {
    @Override
    public IMessage onMessage(SetFogPacket message, MessageContext ctx) {
      // Always use a construct like this to actually handle your message. This ensures that
      // youre 'handle' code is run on the main Minecraft thread. 'onMessage' itself
      // is called on the networking thread so it is not safe to do a lot of things
      // here.
      FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
      return null;
    }

    private void handle(SetFogPacket message, MessageContext ctx) {
      // This code is run on the server side. So you can do server-side calculations here
      Minecraft.getMinecraft().addScheduledTask(() -> {
        RedMoon.Client.fog = message.fog;
      });
    }
  }
}