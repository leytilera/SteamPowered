package com.teammoeg.steampowered.content.engine;

import com.simibubi.create.AllShapes;
import com.simibubi.create.foundation.block.IBE;
import com.teammoeg.steampowered.block.SPBlockPartials;
import com.teammoeg.steampowered.oldcreatestuff.FurnaceEngineInteractions;
import com.teammoeg.steampowered.oldcreatestuff.OldEngineBlock;
import com.teammoeg.steampowered.registrate.SPBlockEntities;
import com.teammoeg.steampowered.registrate.SPBlocks;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.levelWrappers.WrappedLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FurnaceEngineBlock extends OldEngineBlock implements IBE<FurnaceEngineTileEntity> {

    public FurnaceEngineBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean isValidBaseBlock(BlockState baseBlock, BlockGetter world, BlockPos pos) {
        return FurnaceEngineInteractions.getHandler(baseBlock).getHeatSource(baseBlock).isValid();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return AllShapes.FURNACE_ENGINE.get(state.getValue(FACING));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public PartialModel getFrameModel() {
        return SPBlockPartials.FURNACE_GENERATOR_FRAME;
    }

    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
                                boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
        if (worldIn instanceof WrappedLevel)
            return;
        if (worldIn.isClientSide)
            return;

        if (fromPos.equals(getBaseBlockPos(state, pos)))
            if (canSurvive(state, worldIn, pos))
                withBlockEntityDo(worldIn, pos, FurnaceEngineTileEntity::updateFurnace);
    }

    @SubscribeEvent
    public static void usingFurnaceEngineOnFurnacePreventsGUI(PlayerInteractEvent.RightClickBlock event) {
        ItemStack item = event.getItemStack();
        if (!(item.getItem() instanceof BlockItem blockItem))
            return;
        if (blockItem.getBlock() != SPBlocks.FURNACE_ENGINE.get())
            return;
        BlockState state = event.getLevel().getBlockState(event.getPos());
        if (event.getFace().getAxis().isVertical())
            return;
        if (state.getBlock() instanceof AbstractFurnaceBlock)
            event.setUseBlock(Event.Result.DENY);
    }

    @Override
    public Class<FurnaceEngineTileEntity> getBlockEntityClass() {
        return FurnaceEngineTileEntity.class;
    }

    @Override
    public BlockEntityType<? extends FurnaceEngineTileEntity> getBlockEntityType() {
        return SPBlockEntities.FURNACE_ENGINE.get();
    }


}
