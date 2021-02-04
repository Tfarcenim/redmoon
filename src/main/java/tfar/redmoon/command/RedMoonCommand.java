package tfar.redmoon.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import tfar.redmoon.PacketHandler;
import tfar.redmoon.clientpacket.SetMoonPacket;

public class RedMoonCommand extends CommandBase {

    @Override
    public String getName() {
        return "active";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.redmoon.active.usages";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        boolean active = "true".equals(args[0]);
        PacketHandler.INSTANCE.sendToAll(new SetMoonPacket(active));
    }
}
