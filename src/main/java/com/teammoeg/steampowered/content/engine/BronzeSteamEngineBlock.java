package com.teammoeg.steampowered.content.engine;

import java.util.List;

import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.teammoeg.steampowered.SPConfig;
import com.teammoeg.steampowered.client.ClientUtils;
import com.teammoeg.steampowered.registrate.SPBlockEntities;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

import net.minecraft.world.level.BlockGetter;

public class BronzeSteamEngineBlock extends SteamEngineBlock implements IBE<BronzeSteamEngineTileEntity> {
    public BronzeSteamEngineBlock(Properties builder) {
        super(builder);
    }

    @Override
    public BlockEntityType<? extends BronzeSteamEngineTileEntity> getBlockEntityType() {
        return SPBlockEntities.BRONZE_STEAM_ENGINE.get();
    }

    @Override
    public Class<BronzeSteamEngineTileEntity> getBlockEntityClass() {
        return BronzeSteamEngineTileEntity.class;
    }

    @Override
	public void appendHoverText(ItemStack i, BlockGetter w, List<Component> t,
			TooltipFlag f) {
    	if(Screen.hasShiftDown()) {
    		t.add(Component.translatable("tooltip.steampowered.engine.brief").withStyle(ChatFormatting.GOLD));
    		if(ClientUtils.hasGoggles()) 
    		t.add(Component.translatable("tooltip.steampowered.engine.steamconsume",SPConfig.COMMON.bronzeFlywheelSteamConsumptionPerTick.get()).withStyle(ChatFormatting.GOLD));
    	}else {
    		t.add(TooltipHelper.holdShift(FontHelper.Palette.GRAY,false));
    	}
		super.appendHoverText(i,w,t,f);
	}
}
