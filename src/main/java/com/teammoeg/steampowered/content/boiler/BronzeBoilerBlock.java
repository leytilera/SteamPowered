package com.teammoeg.steampowered.content.boiler;

import com.simibubi.create.foundation.block.IBE;
import com.teammoeg.steampowered.SPConfig;
import com.teammoeg.steampowered.registrate.SPBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BronzeBoilerBlock extends BoilerBlock implements IBE<BronzeBoilerBlockEntity> {
    public BronzeBoilerBlock(Properties properties) {
        super(properties);
    }

	@Override
	public int getHuConsume() {
		return SPConfig.COMMON.bronzeBoilerHU.get();
	}

    @Override
    public Class<BronzeBoilerBlockEntity> getBlockEntityClass() {
        return BronzeBoilerBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends BronzeBoilerBlockEntity> getBlockEntityType() {
        return SPBlockEntities.BRONZE_BOILER.get();
    }
}
