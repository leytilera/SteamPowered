package com.teammoeg.steampowered.content.alternator;

import com.jozufozu.flywheel.api.MaterialManager;
import com.simibubi.create.content.kinetics.base.HalfShaftInstance;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import net.minecraft.core.Direction;

public class DynamoShaftInstance extends HalfShaftInstance<KineticBlockEntity> {

	public DynamoShaftInstance(MaterialManager modelManager, KineticBlockEntity tile) {
		super(modelManager, tile);
	}
	@Override
    protected Direction getShaftDirection() {
        return blockState.getValue(DynamoBlock.FACING).getOpposite();
    }

}
