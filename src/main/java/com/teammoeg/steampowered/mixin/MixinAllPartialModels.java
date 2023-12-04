package com.teammoeg.steampowered.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.simibubi.create.AllPartialModels;
import com.teammoeg.steampowered.create.flywheel.PartialModels;

@Mixin(AllPartialModels.class)
public class MixinAllPartialModels {
    
    @Inject(method = "<clinit>", at = @At("HEAD"), remap = false)
    private static void onInit(CallbackInfo ci) {
        PartialModels.init();
    }

}
