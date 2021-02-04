package tfar.redmoon.mixin;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import tfar.redmoon.RedMoon;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

    @Shadow @Final private int[] lightmapColors;

    @Inject(method = "updateLightmap",
            at = @At(value = "FIELD",opcode = Opcodes.IASTORE,target = "Lnet/minecraft/client/renderer/EntityRenderer;lightmapColors:[I",shift = At.Shift.BY,by = 5),locals = LocalCapture.CAPTURE_FAILHARD)
    private void tweakLight(float partialTicks, CallbackInfo ci, World world, float f, float f1, int i) {
        if (RedMoon.Client.active || true) {
            RedMoon.Client.modifyLight(this.lightmapColors,i);
        }
    }
}
