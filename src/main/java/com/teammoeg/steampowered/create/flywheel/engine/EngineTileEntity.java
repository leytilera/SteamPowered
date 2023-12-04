package com.teammoeg.steampowered.create.flywheel.engine;

import java.util.List;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.teammoeg.steampowered.create.flywheel.legacy.FlywheelBlock;
import com.teammoeg.steampowered.create.flywheel.legacy.FlywheelBlockEntity;
import com.teammoeg.steampowered.registrate.SPBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class EngineTileEntity extends SmartBlockEntity {

	public float appliedCapacity;
	public float appliedSpeed;
	protected FlywheelBlockEntity poweredWheel;

	public EngineTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
	}

	@Override
	protected AABB createRenderBoundingBox() {
		return super.createRenderBoundingBox().inflate(1.5f);
	}

	@Override
	public void lazyTick() {
		super.lazyTick();
		if (level.isClientSide)
			return;
		if (poweredWheel != null && poweredWheel.isRemoved())
			poweredWheel = null;
		if (poweredWheel == null)
			attachWheel();
	}

	public void attachWheel() {
		Direction engineFacing = getBlockState().getValue(EngineBlock.FACING);
		BlockPos wheelPos = worldPosition.relative(engineFacing, 2);
		BlockState wheelState = level.getBlockState(wheelPos);
		if (!SPBlocks.FLYWHEEL.has(wheelState))
			return;
		Direction wheelFacing = wheelState.getValue(FlywheelBlock.HORIZONTAL_FACING);
		if (wheelFacing.getAxis() != engineFacing.getClockWise().getAxis())
			return;
		if (FlywheelBlock.isConnected(wheelState)
				&& FlywheelBlock.getConnection(wheelState) != engineFacing.getOpposite())
			return;
		BlockEntity te = level.getBlockEntity(wheelPos);
		if (te.isRemoved())
			return;
		if (te instanceof FlywheelBlockEntity) {
			if (!FlywheelBlock.isConnected(wheelState))
				FlywheelBlock.setConnection(level, te.getBlockPos(), te.getBlockState(), engineFacing.getOpposite());
			poweredWheel = (FlywheelBlockEntity) te;
			refreshWheelSpeed();
		}
	}

	public void detachWheel() {
		if (poweredWheel == null || poweredWheel.isRemoved())
			return;
		poweredWheel.setRotation(0, 0);
		FlywheelBlock.setConnection(level, poweredWheel.getBlockPos(), poweredWheel.getBlockState(), null);
		poweredWheel = null;
	}

	@Override
	public void remove() {
		detachWheel();
		super.remove();
	}

	protected void refreshWheelSpeed() {
		if (poweredWheel == null)
			return;
		poweredWheel.setRotation(appliedSpeed, appliedCapacity);
	}


}
