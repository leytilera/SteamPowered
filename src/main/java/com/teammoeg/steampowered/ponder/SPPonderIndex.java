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

package com.teammoeg.steampowered.ponder;

import com.simibubi.create.Create;
import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.foundation.ponder.PonderTag;
import com.simibubi.create.infrastructure.ponder.AllPonderTags;
import com.simibubi.create.infrastructure.ponder.scenes.KineticsScenes;
import com.teammoeg.steampowered.SteamPowered;
import com.teammoeg.steampowered.registrate.SPBlocks;

import net.minecraft.resources.ResourceLocation;


public class SPPonderIndex {
    static final PonderRegistrationHelper CREATE_HELPER = new PonderRegistrationHelper(Create.ID);
    static final PonderRegistrationHelper STEAM_HELPER = new PonderRegistrationHelper(SteamPowered.MODID);

    public static final PonderTag STEAM = new PonderTag(new ResourceLocation(SteamPowered.MODID, "steam")).item(SPBlocks.BRONZE_STEAM_ENGINE.get(), true, false)
            .defaultLang("Steam", "Components related to steam production and usage");

    public static void register() {
        CREATE_HELPER.forComponents(SPBlocks.BRONZE_COGWHEEL, SPBlocks.CAST_IRON_COGWHEEL, SPBlocks.STEEL_COGWHEEL)
                .addStoryBoard(new ResourceLocation("create", "cog/small"), KineticsScenes::cogAsRelay, AllPonderTags.KINETIC_RELAYS)
                .addStoryBoard(new ResourceLocation("create", "cog/speedup"), KineticsScenes::cogsSpeedUp);

        CREATE_HELPER.forComponents(SPBlocks.BRONZE_LARGE_COGWHEEL, SPBlocks.CAST_IRON_LARGE_COGWHEEL, SPBlocks.STEEL_LARGE_COGWHEEL)
                .addStoryBoard(new ResourceLocation("create", "cog/speedup"), KineticsScenes::cogsSpeedUp)
                .addStoryBoard(new ResourceLocation("create", "cog/large"), KineticsScenes::largeCogAsRelay, AllPonderTags.KINETIC_RELAYS);

        STEAM_HELPER.forComponents(SPBlocks.BRONZE_STEAM_ENGINE, SPBlocks.CAST_IRON_STEAM_ENGINE, SPBlocks.STEEL_STEAM_ENGINE)
                .addStoryBoard("steam_engine", SPScenes::steamEngine, AllPonderTags.KINETIC_SOURCES, STEAM);

        STEAM_HELPER.forComponents(SPBlocks.BRONZE_STEAM_ENGINE, SPBlocks.CAST_IRON_STEAM_ENGINE, SPBlocks.STEEL_STEAM_ENGINE)
                .addStoryBoard("boiler", SPScenes::steamBoiler, AllPonderTags.KINETIC_SOURCES, STEAM);

        STEAM_HELPER.forComponents(SPBlocks.BRONZE_BOILER, SPBlocks.BRONZE_BURNER, SPBlocks.CAST_IRON_BURNER, SPBlocks.CAST_IRON_BOILER, SPBlocks.STEEL_BURNER, SPBlocks.STEEL_BOILER)
                .addStoryBoard("boiler", SPScenes::steamBoiler, AllPonderTags.KINETIC_SOURCES, STEAM);

        STEAM_HELPER.forComponents(SPBlocks.BRONZE_FLYWHEEL, SPBlocks.CAST_IRON_FLYWHEEL, SPBlocks.STEEL_FLYWHEEL)
                .addStoryBoard("steam_engine", SPScenes::steamFlywheel, AllPonderTags.KINETIC_SOURCES, STEAM);

        STEAM_HELPER.forComponents(SPBlocks.DYNAMO)
                .addStoryBoard("dynamo", SPScenes::dynamo, AllPonderTags.KINETIC_APPLIANCES, STEAM);

    }
}
