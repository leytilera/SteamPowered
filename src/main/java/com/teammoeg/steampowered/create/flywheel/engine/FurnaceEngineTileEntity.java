package com.teammoeg.steampowered.create.flywheel.engine;

import com.simibubi.create.content.kinetics.BlockStressValues;
import com.teammoeg.steampowered.create.flywheel.engine.FurnaceEngineInteractions.HeatSource;
import com.teammoeg.steampowered.create.flywheel.engine.FurnaceEngineInteractions.InteractionHandler;
import com.teammoeg.steampowered.registrate.SPBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class FurnaceEngineTileEntity extends EngineTileEntity {

	public FurnaceEngineTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	public void lazyTick() {
		updateFurnace();
		super.lazyTick();
	}

	public void updateFurnace() {
		BlockState state = level.getBlockState(EngineBlock.getBaseBlockPos(getBlockState(), worldPosition));
		InteractionHandler handler = FurnaceEngineInteractions.getHandler(state);
		HeatSource heatSource = handler.getHeatSource(state);
		if (heatSource.isEmpty())
			return;

		float modifier = handler.getSpeedModifier(state);
		boolean active = heatSource.isActive();
		float speed = active ? 16 * modifier : 0;
		float capacity =
			(float) (active ? BlockStressValues.getCapacity(SPBlocks.FURNACE_ENGINE.get())
				: 0);

		appliedCapacity = capacity;
		appliedSpeed = speed;
		refreshWheelSpeed();
	}

}
