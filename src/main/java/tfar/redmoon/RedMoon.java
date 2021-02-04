package tfar.redmoon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import tfar.redmoon.command.ModCommand;

@Mod(modid = RedMoon.MODID, name = RedMoon.NAME, version = RedMoon.VERSION)
public class RedMoon {
    public static final String MODID = "redmoon";
    public static final String NAME = "Red Moon";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        PacketHandler.registerMessages(MODID);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent evt) {
        evt.registerServerCommand(new ModCommand());
    }

    @Mod.EventBusSubscriber(Side.CLIENT)
    public static class Client {

        private static final Minecraft mc  = Minecraft.getMinecraft();
        private static final ResourceLocation ID = new ResourceLocation(MODID, "shaders/post/red.json");

        public static boolean active;
        public static  int delay = 300;
        public static double fog = 0;
        public static double prevFog = 0;
        public int timer = 0;

        @SubscribeEvent
        public static void redMoon(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.START && mc.player != null) {
                if (!mc.entityRenderer.isShaderActive() && active) {
              //      mc.entityRenderer.loadShader(ID);
                } else if (!active && mc.entityRenderer.isShaderActive() && mc.entityRenderer.getShaderGroup().getShaderGroupName().equals(ID.toString())) {
                 //   mc.entityRenderer.loadEntityShader(mc.player);
                }
            }
        }

        public static void modifyLight(int[] lightmapColors, int i) {
            int red = 0;
            int green = 0;
            int blue = 0;
            lightmapColors[i] = 0;//0xff000000 | (red << 16) | (green << 8) | blue;
        }

        @SubscribeEvent
        public static void fog(EntityViewRenderEvent.FogDensity event) {
            if (fog > 0) {
                GlStateManager.setFog(GlStateManager.FogMode.EXP);
                event.setDensity((float) fog);
                event.setCanceled(true);
            }
        }
    }
}
