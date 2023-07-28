/*
 * Copyright (c) 2021 TeamMoeg
 *
 * This file is part of Steam Powered.
 *
 * Steam Powered is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * Steam Powered is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Steam Powered. If not, see <https://www.gnu.org/licenses/>.
 */

package com.teammoeg.steampowered.registrate;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOnly;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;

import javax.annotation.Nonnull;

import com.simibubi.create.AllTags;
import com.simibubi.create.Create;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.flywheel.FlywheelGenerator;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.simibubi.create.content.kinetics.simpleRelays.CogwheelBlockItem;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.teammoeg.steampowered.SteamPowered;
import com.teammoeg.steampowered.content.alternator.DynamoBlock;
import com.teammoeg.steampowered.content.boiler.BronzeBoilerBlock;
import com.teammoeg.steampowered.content.boiler.CastIronBoilerBlock;
import com.teammoeg.steampowered.content.boiler.SteelBoilerBlock;
import com.teammoeg.steampowered.content.burner.BronzeBurnerBlock;
import com.teammoeg.steampowered.content.burner.CastIronBurnerBlock;
import com.teammoeg.steampowered.content.burner.SteelBurnerBlock;
import com.teammoeg.steampowered.content.cogwheel.MetalCogwheelBlock;
import com.teammoeg.steampowered.content.engine.BronzeSteamEngineBlock;
import com.teammoeg.steampowered.content.engine.CastIronSteamEngineBlock;
import com.teammoeg.steampowered.content.engine.SteelSteamEngineBlock;
import com.teammoeg.steampowered.content.flywheel.BronzeSteamFlywheelBlock;
import com.teammoeg.steampowered.content.flywheel.CastIronSteamFlywheelBlock;
import com.teammoeg.steampowered.content.flywheel.SteelSteamFlywheelBlock;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;

public class SPBlocks {
    private static final CreateRegistrate REGISTRATE = SteamPowered.registrate
            .creativeModeTab(() -> SteamPowered.itemGroup);

    public static final BlockEntry<BronzeBurnerBlock> BRONZE_BURNER = REGISTRATE.block("bronze_burner", BronzeBurnerBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .transform(pickaxeOnly())
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<CastIronBurnerBlock> CAST_IRON_BURNER = REGISTRATE.block("cast_iron_burner", CastIronBurnerBlock::new)
            .initialProperties(SPBlocks::hardMetal)
            .transform(pickaxeOnly())
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<SteelBurnerBlock> STEEL_BURNER = REGISTRATE.block("steel_burner", SteelBurnerBlock::new)
            .initialProperties(SPBlocks::hardMetal)
            .transform(pickaxeOnly())
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<BronzeBoilerBlock> BRONZE_BOILER = REGISTRATE.block("bronze_boiler", BronzeBoilerBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .transform(pickaxeOnly())
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<CastIronBoilerBlock> CAST_IRON_BOILER = REGISTRATE.block("cast_iron_boiler", CastIronBoilerBlock::new)
            .initialProperties(SPBlocks::hardMetal)
            .transform(pickaxeOnly())
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<SteelBoilerBlock> STEEL_BOILER = REGISTRATE.block("steel_boiler", SteelBoilerBlock::new)
            .initialProperties(SPBlocks::hardMetal)
            .transform(pickaxeOnly())
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<BronzeSteamEngineBlock> BRONZE_STEAM_ENGINE = REGISTRATE.block("bronze_steam_engine", BronzeSteamEngineBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .transform(pickaxeOnly())
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<CastIronSteamEngineBlock> CAST_IRON_STEAM_ENGINE = REGISTRATE.block("cast_iron_steam_engine", CastIronSteamEngineBlock::new)
            .initialProperties(SPBlocks::hardMetal)
            .transform(pickaxeOnly())
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<SteelSteamEngineBlock> STEEL_STEAM_ENGINE = REGISTRATE.block("steel_steam_engine", SteelSteamEngineBlock::new)
            .initialProperties(SPBlocks::hardMetal)
            .transform(pickaxeOnly())
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<MetalCogwheelBlock> STEEL_COGWHEEL = REGISTRATE.block("steel_cogwheel", MetalCogwheelBlock::small)
            .initialProperties(SPBlocks::hardMetal)
            .transform(pickaxeOnly())
            .transform(BlockStressDefaults.setNoImpact())
            .properties(p -> p.sound(SoundType.METAL))
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .item(CogwheelBlockItem::new)
            .build()
            .register();

    public static final BlockEntry<MetalCogwheelBlock> STEEL_LARGE_COGWHEEL = REGISTRATE.block("steel_large_cogwheel", MetalCogwheelBlock::large)
            .initialProperties(SPBlocks::hardMetal)
            .transform(pickaxeOnly())
            .transform(BlockStressDefaults.setNoImpact())
            .properties(p -> p.sound(SoundType.METAL))
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .item(CogwheelBlockItem::new)
            .build()
            .register();

    public static final BlockEntry<MetalCogwheelBlock> CAST_IRON_COGWHEEL = REGISTRATE.block("cast_iron_cogwheel", MetalCogwheelBlock::small)
            .initialProperties(SPBlocks::hardMetal)
            .transform(pickaxeOnly())
            .transform(BlockStressDefaults.setNoImpact())
            .properties(p -> p.sound(SoundType.METAL))
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .item(CogwheelBlockItem::new)
            .build()
            .register();

    public static final BlockEntry<MetalCogwheelBlock> CAST_IRON_LARGE_COGWHEEL = REGISTRATE.block("cast_iron_large_cogwheel", MetalCogwheelBlock::large)
            .initialProperties(SPBlocks::hardMetal)
            .transform(pickaxeOnly())
            .transform(BlockStressDefaults.setNoImpact())
            .properties(p -> p.sound(SoundType.METAL))
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .item(CogwheelBlockItem::new)
            .build()
            .register();

    public static final BlockEntry<MetalCogwheelBlock> BRONZE_COGWHEEL = REGISTRATE.block("bronze_cogwheel", MetalCogwheelBlock::small)
            .initialProperties(SharedProperties::softMetal)
            .transform(pickaxeOnly())
            .transform(BlockStressDefaults.setNoImpact())
            .properties(p -> p.sound(SoundType.METAL))
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .item(CogwheelBlockItem::new)
            .build()
            .register();

    public static final BlockEntry<MetalCogwheelBlock> BRONZE_LARGE_COGWHEEL = REGISTRATE.block("bronze_large_cogwheel", MetalCogwheelBlock::large)
            .initialProperties(SharedProperties::softMetal)
            .transform(pickaxeOnly())
            .transform(BlockStressDefaults.setNoImpact())
            .properties(p -> p.sound(SoundType.METAL))
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .item(CogwheelBlockItem::new)
            .build()
            .register();

    public static final BlockEntry<DynamoBlock> DYNAMO = REGISTRATE.block("alternator", DynamoBlock::new)
            .initialProperties(SPBlocks::hardMetal)
            .transform(pickaxeOnly())
            .transform(BlockStressDefaults.setImpact(4.0))
            .tag(AllTags.AllBlockTags.SAFE_NBT.tag) //Dono what this tag means (contraption safe?).
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<BronzeSteamFlywheelBlock> BRONZE_FLYWHEEL = REGISTRATE.block("bronze_flywheel", BronzeSteamFlywheelBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(BlockBehaviour.Properties::noOcclusion)
            .transform(axeOrPickaxe())
            .transform(BlockStressDefaults.setNoImpact())
            .blockstate(new FlywheelGenerator()::generate)
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<CastIronSteamFlywheelBlock> CAST_IRON_FLYWHEEL = REGISTRATE.block("cast_iron_flywheel", CastIronSteamFlywheelBlock::new)
            .initialProperties(SPBlocks::hardMetal)
            .properties(BlockBehaviour.Properties::noOcclusion)
            .transform(axeOrPickaxe())
            .transform(BlockStressDefaults.setNoImpact())
            .blockstate(new FlywheelGenerator()::generate)
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<SteelSteamFlywheelBlock> STEEL_FLYWHEEL = REGISTRATE.block("steel_flywheel", SteelSteamFlywheelBlock::new)
            .initialProperties(SPBlocks::hardMetal)
            .properties(BlockBehaviour.Properties::noOcclusion)
            .transform(axeOrPickaxe())
            .transform(BlockStressDefaults.setNoImpact())
            .blockstate(new FlywheelGenerator()::generate)
            .item()
            .transform(customItemModel())
            .register();

    public static void register() {
        /*Create.REGISTRATE.addToSection(BRONZE_STEAM_ENGINE, AllSections.KINETICS);
        Create.REGISTRATE.addToSection(CAST_IRON_STEAM_ENGINE, AllSections.KINETICS);
        Create.REGISTRATE.addToSection(STEEL_STEAM_ENGINE, AllSections.KINETICS);
        Create.REGISTRATE.addToSection(STEEL_COGWHEEL, AllSections.KINETICS);
        Create.REGISTRATE.addToSection(STEEL_LARGE_COGWHEEL, AllSections.KINETICS);
        Create.REGISTRATE.addToSection(CAST_IRON_COGWHEEL, AllSections.KINETICS);
        Create.REGISTRATE.addToSection(CAST_IRON_LARGE_COGWHEEL, AllSections.KINETICS);
        Create.REGISTRATE.addToSection(BRONZE_COGWHEEL, AllSections.KINETICS);
        Create.REGISTRATE.addToSection(BRONZE_LARGE_COGWHEEL, AllSections.KINETICS);
        Create.REGISTRATE.addToSection(BRONZE_FLYWHEEL, AllSections.KINETICS);
        Create.REGISTRATE.addToSection(CAST_IRON_FLYWHEEL, AllSections.KINETICS);
        Create.REGISTRATE.addToSection(STEEL_FLYWHEEL, AllSections.KINETICS);*/
    }

    @Nonnull
    public static Block hardMetal() {
        return Blocks.IRON_BLOCK;
    }
}
