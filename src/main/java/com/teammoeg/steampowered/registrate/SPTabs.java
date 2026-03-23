package com.teammoeg.steampowered.registrate;

import static com.teammoeg.steampowered.SteamPowered.REGISTRATE;

import com.teammoeg.steampowered.SteamPowered;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SPTabs {
    private static final DeferredRegister<CreativeModeTab> REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SteamPowered.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SP_BASE_TAB = REGISTER.register("base",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.steampowered.base"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .icon(SPBlocks.STEEL_FLYWHEEL::asStack)
                    .build());

    public static void register(IEventBus modEventBus) {
        REGISTER.register(modEventBus);
        REGISTRATE.defaultCreativeTab(SP_BASE_TAB.getKey());
    }
}
