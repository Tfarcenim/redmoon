package tfar.redmoon.command;

import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;
import tfar.redmoon.RedMoon;

public class ModCommand extends CommandTreeBase {

    public ModCommand() {
        addSubcommand(new RedMoonCommand());
        addSubcommand(new FogCommand());
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getName() {
        return RedMoon.MODID;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.redmoon.usage";
    }
}
