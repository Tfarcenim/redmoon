package tfar.redmoon.clientpacket;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tfar.redmoon.RedMoon;

public class SetMoonPacket implements IMessage {

  private boolean active;

  public SetMoonPacket() {
  }

  public SetMoonPacket(boolean active) {
    this.active = active;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    active = buf.readBoolean();
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(active);
  }

  public static class Handler implements IMessageHandler<SetMoonPacket, IMessage> {
    @Override
    public IMessage onMessage(SetMoonPacket message, MessageContext ctx) {
      // Always use a construct like this to actually handle your message. This ensures that
      // youre 'handle' code is run on the main Minecraft thread. 'onMessage' itself
      // is called on the networking thread so it is not safe to do a lot of things
      // here.
      FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
      return null;
    }

    private void handle(SetMoonPacket message, MessageContext ctx) {
      // This code is run on the server side. So you can do server-side calculations here
      Minecraft.getMinecraft().addScheduledTask(() -> {
        RedMoon.Client.active = message.active;
      });
    }
  }
}