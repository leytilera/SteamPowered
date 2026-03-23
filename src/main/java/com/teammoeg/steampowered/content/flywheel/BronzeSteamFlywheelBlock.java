package com.teammoeg.steampowered.content.flywheel;

import com.teammoeg.steampowered.registrate.SPBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BronzeSteamFlywheelBlock extends SteamFlywheelBlock {
    public BronzeSteamFlywheelBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends SteamFlywheelTileEntity> getBlockEntityType() {
        return SPBlockEntities.BRONZE_STEAM_FLYWHEEL.get();
    }
}
