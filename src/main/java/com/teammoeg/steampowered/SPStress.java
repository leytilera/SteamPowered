package com.teammoeg.steampowered;

import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.BlockStressValues.IStressValueProvider;
import com.simibubi.create.foundation.utility.Couple;
import com.teammoeg.steampowered.content.alternator.DynamoBlock;
import com.teammoeg.steampowered.create.flywheel.engine.EngineBlock;
import com.teammoeg.steampowered.create.flywheel.legacy.FlywheelBlock;

import net.minecraft.world.level.block.Block;

public class SPStress implements IStressValueProvider {

	@Override
	public double getCapacity(Block arg0) {
		if(!(arg0 instanceof EngineBlock))return BlockStressDefaults.DEFAULT_CAPACITIES.getOrDefault(arg0.getRegistryName(),0D);
		String mat=arg0.getRegistryName().getPath().split("_")[0];
		switch(mat) {
		case "bronze":return SPConfig.COMMON.bronzeFlywheelCapacity.get();
		case "cast":return SPConfig.COMMON.castIronFlywheelCapacity.get();
		case "steel":return SPConfig.COMMON.steelFlywheelCapacity.get();
		}
		return BlockStressDefaults.DEFAULT_CAPACITIES.getOrDefault(arg0.getRegistryName(),0D);
	}

	@Override
	public double getImpact(Block arg0) {
		if(arg0 instanceof FlywheelBlock)return BlockStressDefaults.DEFAULT_IMPACTS.getOrDefault(arg0.getRegistryName(),0D);
		if(arg0 instanceof DynamoBlock) {
			return SPConfig.COMMON.dynamoImpact.get();
		}
		String[] mat=arg0.getRegistryName().getPath().split("_");
		if(mat[mat.length-1].equals("cogwheel")) {
			switch(mat[0]) {
			case "bronze":return SPConfig.COMMON.bronzeCogwheelImpact.get();
			case "cast":return SPConfig.COMMON.castIronCogwheelImpact.get();
			case "steel":return SPConfig.COMMON.steelCogwheelImpact.get();
			}
		}
		return BlockStressDefaults.DEFAULT_IMPACTS.getOrDefault(arg0.getRegistryName(),0D);
	}

	@Override
	public boolean hasCapacity(Block arg0) {
		if((arg0 instanceof EngineBlock))return true;
		return false;
	}

	@Override
	public boolean hasImpact(Block arg0) {
		if(arg0 instanceof FlywheelBlock)return false;
		return true;
	}

	@Override
	public Couple<Integer> getGeneratedRPM(Block block) {
		return null; //TODO: WTF
	}

}
