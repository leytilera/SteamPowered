package com.teammoeg.steampowered;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.teammoeg.steampowered.client.Particles;
import com.teammoeg.steampowered.client.SteamPoweredClient;
import com.teammoeg.steampowered.content.alternator.DynamoBlockEntity;
import com.teammoeg.steampowered.content.boiler.BoilerTileEntity;
import com.teammoeg.steampowered.content.burner.BurnerBlockEntity;
import com.teammoeg.steampowered.content.engine.SteamEngineTileEntity;
import com.teammoeg.steampowered.registrate.*;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.NeoForge;

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

    public SteamPowered(IEventBus eventBus, ModContainer modContainer) {
        REGISTRATE.registerEventListeners(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::doClientStuff);

        if (FMLLoader.getDist() == Dist.CLIENT) {
            SteamPoweredClient.addClientListeners(NeoForge.EVENT_BUS, eventBus);
        }

        eventBus.addListener(this::registerCapabilities);
        Particles.REGISTER.register(eventBus);
        SPTabs.register(eventBus);
        SPFluids.register();
        SPBlocks.register();
        SPBlockEntities.register();
        SPItems.register();
        SPTags.init();
        new SPStress().initStress();
        modContainer.registerConfig(ModConfig.Type.COMMON, SPConfig.COMMON_CONFIG);
        modContainer.registerConfig(ModConfig.Type.SERVER, SPConfig.SERVER_CONFIG);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SPBlockEntities.BRONZE_BURNER.get(), BurnerBlockEntity.ITEM_CAP);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SPBlockEntities.CAST_IRON_BURNER.get(), BurnerBlockEntity.ITEM_CAP);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SPBlockEntities.STEEL_BURNER.get(), BurnerBlockEntity.ITEM_CAP);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, SPBlockEntities.BRONZE_BOILER.get(), BoilerTileEntity.FLUID_CAP);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, SPBlockEntities.CAST_IRON_BOILER.get(), BoilerTileEntity.FLUID_CAP);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, SPBlockEntities.STEEL_BOILER.get(), BoilerTileEntity.FLUID_CAP);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, SPBlockEntities.BRONZE_STEAM_ENGINE.get(), SteamEngineTileEntity.FLUID_CAP);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, SPBlockEntities.CAST_IRON_STEAM_ENGINE.get(), SteamEngineTileEntity.FLUID_CAP);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, SPBlockEntities.STEEL_STEAM_ENGINE.get(), SteamEngineTileEntity.FLUID_CAP);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, SPBlockEntities.DYNAMO.get(), DynamoBlockEntity.ENERGY_CAP);
    }

}
