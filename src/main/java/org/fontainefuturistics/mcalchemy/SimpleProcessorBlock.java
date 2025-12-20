package org.fontainefuturistics.mcalchemy;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos; //need block position and state to create the BlockEntity
import net.minecraft.world.level.block.state.BlockState;

/**
 * this block will recieve Raw Item and output Processed Item
 */
public class SimpleProcessorBlock extends BaseEntityBlock{

    public static final MapCodec<SimpleProcessorBlock> CODEC = simpleCodec(SimpleProcessorBlock::new); //the co

    //constructor
    public SimpleProcessorBlock(Properties properties) {
        super(properties);
    }

    //have to implement this, it is abstract in BaseEntityBlock. will be useful later i'm getting to it.
    @Override
    public MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    //BaseEntityBlocks pierce the fabric of perception by default for some reason. tell it to render itself normally.
    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    //instantiate the associated BlockEntity 
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SimpleProcessorBlockEntity(pos, state);
    }
}
