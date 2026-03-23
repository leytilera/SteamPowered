package com.teammoeg.steampowered.content.engine;

import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.teammoeg.steampowered.SPConfig;
import com.teammoeg.steampowered.client.ClientUtils;
import com.teammoeg.steampowered.registrate.SPBlockEntities;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.List;

public class SteelSteamEngineBlock extends SteamEngineBlock implements IBE<SteelSteamEngineTileEntity> {
    public SteelSteamEngineBlock(Properties builder) {
        super(builder);
    }

    @Override
    public BlockEntityType<? extends SteelSteamEngineTileEntity> getBlockEntityType() {
        return SPBlockEntities.STEEL_STEAM_ENGINE.get();
    }
    @Override
	public void appendHoverText(ItemStack i, Item.TooltipContext w, List<Component> t,
			TooltipFlag f) {
    	if(Screen.hasShiftDown()) {
    		t.add(Component.translatable("tooltip.steampowered.engine.brief").withStyle(ChatFormatting.GOLD));
    		if(ClientUtils.hasGoggles()) 
    		t.add(Component.translatable("tooltip.steampowered.engine.steamconsume",SPConfig.COMMON.steelFlywheelSteamConsumptionPerTick.get()).withStyle(ChatFormatting.GOLD));
    	}else {
    		t.add(TooltipHelper.holdShift(FontHelper.Palette.GRAY,false));
    	}
		super.appendHoverText(i,w,t,f);
	}
    @Override
    public Class<SteelSteamEngineTileEntity> getBlockEntityClass() {
        return SteelSteamEngineTileEntity.class;
    }
}
