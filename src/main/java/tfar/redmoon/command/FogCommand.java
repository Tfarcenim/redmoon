package tfar.redmoon.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import tfar.redmoon.PacketHandler;
import tfar.redmoon.clientpacket.SetFogPacket;

public class FogCommand extends CommandBase {

    @Override
    public String getName() {
        return "fog";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.redmoon.fog.usages";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        double fog = Double.parseDouble(args[0]);
        PacketHandler.INSTANCE.sendToAll(new SetFogPacket(fog));
    }
}
