package com.teammoeg.steampowered.content.boiler;

import com.teammoeg.steampowered.SPConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BronzeBoilerBlockEntity extends BoilerTileEntity {

    public BronzeBoilerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    protected int getHUPerTick() {
        return SPConfig.COMMON.bronzeBoilerHU.get();
    }

}
