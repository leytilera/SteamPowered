package com.teammoeg.steampowered;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.teammoeg.steampowered.client.Particles;
import com.teammoeg.steampowered.client.SteamPoweredClient;
import com.teammoeg.steampowered.network.PacketHandler;
import com.teammoeg.steampowered.registrate.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("steampowered")
public class SteamPowered {

    public static final String MODID = "steampowered";

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public SteamPowered() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        REGISTRATE.registerEventListeners(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> () -> SteamPoweredClient.addClientListeners(MinecraftForge.EVENT_BUS, FMLJavaModLoadingContext.get().getModEventBus()));

        Particles.REGISTER.register(eventBus);
        SPTabs.register(eventBus);
        SPFluids.register();
        SPBlocks.register();
        SPBlockEntities.register();
        SPItems.register();
        SPTags.init();
        new SPStress().initStress();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPConfig.COMMON_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SPConfig.SERVER_CONFIG);
        PacketHandler.register();
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    }
}
