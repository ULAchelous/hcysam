package io.ula.hcysam.mixin;

import io.ula.hcysam.Registry_init;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundEngine.class)
public class SoundEngineMixin {

    @Shadow
    @Final
    private static Logger LOGGER;

    @Inject(method = "loadLibrary", at = @At("TAIL"))
    @Environment(EnvType.CLIENT)
    private synchronized void loadLibrary(CallbackInfo ci){
//        Minecraft client = Minecraft.getInstance();
//        if(client.getSoundManager() != null) {
//            client.getSoundManager().play(
//                    SimpleSoundInstance.forUI(Registry_init.VOICE_TITLE, 1.0f)
//            );
//        }
    }
}