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

package com.teammoeg.steampowered.content.burner;

import com.simibubi.create.foundation.block.IBE;
import com.teammoeg.steampowered.SPConfig;
import com.teammoeg.steampowered.registrate.SPTiles;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CastIronBurnerBlock extends BurnerBlock implements IBE<CastIronBurnerTileEntity> {
    public CastIronBurnerBlock(Properties properties) {
        super(properties);
    }

	@Override
	public int getHuProduce() {
		return SPConfig.COMMON.castIronBurnerHU.get();
	}

	@Override
	public double getEfficiency() {
		return SPConfig.COMMON.castIronBurnerEfficiency.get();
	}

	@Override
	public Class<CastIronBurnerTileEntity> getBlockEntityClass() {
		return CastIronBurnerTileEntity.class;
	}

	@Override
	public BlockEntityType<? extends CastIronBurnerTileEntity> getBlockEntityType() {
		return SPTiles.CAST_IRON_BURNER.get();
	}
}
