package com.teammoeg.steampowered.content.flywheel;

import com.teammoeg.steampowered.registrate.SPBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CastIronSteamFlywheelBlock extends SteamFlywheelBlock {
    public CastIronSteamFlywheelBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends SteamFlywheelTileEntity> getBlockEntityType() {
        return SPBlockEntities.CAST_IRON_STEAM_FLYWHEEL.get();
    }
}
