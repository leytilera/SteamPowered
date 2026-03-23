package com.teammoeg.steampowered.content.flywheel;

import com.teammoeg.steampowered.registrate.SPBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class SteelSteamFlywheelBlock extends SteamFlywheelBlock {
    public SteelSteamFlywheelBlock(Properties properties) {
        super(properties);
    }

    //@Override
    public BlockEntityType<? extends SteamFlywheelTileEntity> getBlockEntityType() {
        return SPBlockEntities.STEEL_STEAM_FLYWHEEL.get();
    }
}
