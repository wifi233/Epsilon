package cn.enaium.epsilon.mixin;

import cn.enaium.epsilon.Epsilon;
import cn.enaium.epsilon.imixin.IMinecraftClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Project: Epsilon
 * -----------------------------------------------------------
 * Copyright © 2020 | Enaium | All rights reserved.
 */
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin implements IMinecraftClient {

    @Shadow
    private Window window;

    @Override
    public Window window() {
        return this.window;
    }

    @Inject(at = @At("HEAD"), method = "run()V")
    private void run(CallbackInfo ci) {
        Epsilon.INSTANCE.run();
    }

    @Inject(at = @At("HEAD"), method = "stop()V")
    private void stop(CallbackInfo ci) {
        Epsilon.INSTANCE.stop();
    }

    @Shadow
    public Screen currentScreen;

//    @Inject(at = @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;currentScreen:Lnet/minecraft/client/gui/screen/Screen;", shift = At.Shift.AFTER), method = "openScreen")
//    private void openScreen(Screen screen, CallbackInfo info) {
//        if (this.currentScreen instanceof net.minecraft.client.gui.screen.TitleScreen) {
//        }
//    }

    @Inject(at = @At("RETURN"), method = "updateWindowTitle()V")
    private void updateWindowTitle(CallbackInfo ci) {
        this.window.setTitle(Epsilon.INSTANCE.getNAME() + " | " + Epsilon.INSTANCE.getVERSION() + " | " + Epsilon.INSTANCE.getGAME() + " | " + Epsilon.INSTANCE.getAUTHOR());
    }


}
