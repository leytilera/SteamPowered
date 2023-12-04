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

package com.teammoeg.steampowered.content.engine;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.teammoeg.steampowered.FluidRegistry;
import com.teammoeg.steampowered.SPTags;
import com.teammoeg.steampowered.create.flywheel.engine.EngineBlock;
import com.teammoeg.steampowered.create.flywheel.engine.EngineTileEntity;
import com.teammoeg.steampowered.create.flywheel.legacy.FlywheelBlock;
import com.teammoeg.steampowered.create.flywheel.legacy.FlywheelBlockEntity;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class SteamEngineTileEntity extends EngineTileEntity implements IHaveGoggleInformation {

	private FluidTank tank;
	private LazyOptional<IFluidHandler> holder = LazyOptional.of(() -> tank);
	private int heatup = 0;

	public SteamEngineTileEntity(BlockEntityType<? extends SteamEngineTileEntity> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
		this.refreshCapability();
		this.tank = new FluidTank(this.getSteamStorage(), fluidStack -> {
			if (SPTags.STEAM != null)
				return fluidStack.getFluid().is(SPTags.STEAM);
			else
				return fluidStack.getFluid() == FluidRegistry.steam.get();
		});
	}

	@Override
	public void tick() {
		super.tick();
		if (level != null && !level.isClientSide) {
			BlockState state = this.level.getBlockState(this.worldPosition);
			if (this.poweredWheel == null || this.poweredWheel.isRemoved()) {
				
				this.appliedCapacity = 0;
				this.appliedSpeed = 0;
				this.refreshWheelSpeed();
				heatup = 0;
				tank.drain(this.getSteamConsumptionPerTick(), IFluidHandler.FluidAction.EXECUTE);
				this.level.setBlockAndUpdate(this.worldPosition, state.setValue(SteamEngineBlock.LIT, false));
			} else {
				if(heatup==0&&tank.getFluidAmount()<=this.getSteamConsumptionPerTick()*20) {
					this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
					this.setChanged();
					return;
				}
				if (!tank.isEmpty() && tank.drain(this.getSteamConsumptionPerTick(), IFluidHandler.FluidAction.EXECUTE)
						.getAmount() >= this.getSteamConsumptionPerTick()) {
					if (heatup >= 60) {
						this.appliedCapacity = this.getGeneratingCapacity();
						this.appliedSpeed = this.getGeneratingSpeed();
						this.refreshWheelSpeed();
					} else
						heatup++;
					this.level.setBlockAndUpdate(this.worldPosition, state.setValue(SteamEngineBlock.LIT, true));
				} else {
					if (heatup > 0)
						heatup--;
					
					this.appliedCapacity = 0;
					this.appliedSpeed = 0;
					this.refreshWheelSpeed();
					this.level.setBlockAndUpdate(this.worldPosition, state.setValue(SteamEngineBlock.LIT, false));
				}
				
			}
			this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
			this.setChanged();
		}
	}

	public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
		if (!this.getBlockState().getValue(SteamEngineBlock.LIT)) {
			tooltip.add(componentSpacing.plainCopy()
					.append(new TranslatableComponent("tooltip.steampowered.steam_engine.not_enough_steam")
							.withStyle(ChatFormatting.RED)));
		} else if (heatup < 60) {
			tooltip.add(componentSpacing.plainCopy()
					.append(new TranslatableComponent("tooltip.steampowered.steam_engine.heating")
							.withStyle(ChatFormatting.YELLOW)));
		} else {
			tooltip.add(componentSpacing.plainCopy()
					.append(new TranslatableComponent("tooltip.steampowered.steam_engine.running")
							.withStyle(ChatFormatting.GREEN)));
		}
		return this.containedFluidTooltip(tooltip, isPlayerSneaking,
				getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY));
	}

	@Override
	protected void read(CompoundTag compound, boolean clientPacket) {
		super.read(compound, clientPacket);
		tank.readFromNBT(compound.getCompound("TankContent"));
		heatup = compound.getInt("heatup");
	}

	@Override
	public void write(CompoundTag compound, boolean clientPacket) {
		super.write(compound, clientPacket);
		compound.put("TankContent", tank.writeToNBT(new CompoundTag()));
		compound.putInt("heatup", heatup);
		
	}

	@Override
	@Nonnull
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
		if (!this.holder.isPresent()) {
			this.refreshCapability();
		}
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? holder.cast()
				: super.getCapability(capability, facing);
	}

	private void refreshCapability() {
		LazyOptional<IFluidHandler> oldCap = this.holder;
		this.holder = LazyOptional.of(() -> {
			return this.tank;
		});
		oldCap.invalidate();
	}

	public void attachWheel() {
		Direction engineFacing = (Direction) this.getBlockState().getValue(EngineBlock.FACING);
		BlockPos wheelPos = this.worldPosition.relative(engineFacing, 2);
		BlockState wheelState = this.level.getBlockState(wheelPos);
		if (this.getFlywheel() == wheelState.getBlock()) {
			Direction wheelFacing = (Direction) wheelState.getValue(FlywheelBlock.HORIZONTAL_FACING);
			if (wheelFacing.getAxis() == engineFacing.getClockWise().getAxis()) {
				if (!FlywheelBlock.isConnected(wheelState)
						|| FlywheelBlock.getConnection(wheelState) == engineFacing.getOpposite()) {
					BlockEntity te = this.level.getBlockEntity(wheelPos);
					if (!te.isRemoved()) {
						if (te instanceof FlywheelBlockEntity) {
							if (!FlywheelBlock.isConnected(wheelState)) {
								FlywheelBlock.setConnection(this.level, te.getBlockPos(), te.getBlockState(),
										engineFacing.getOpposite());
							}

							this.poweredWheel = (FlywheelBlockEntity) te;
							this.refreshWheelSpeed();
						}

					}
				}
			}
		}
	}

	public abstract Block getFlywheel();

	public abstract float getGeneratingCapacity();

	public abstract float getGeneratingSpeed();

	public abstract int getSteamConsumptionPerTick();

	public abstract int getSteamStorage();
}
