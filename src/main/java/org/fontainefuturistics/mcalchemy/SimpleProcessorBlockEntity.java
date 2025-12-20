package org.fontainefuturistics.mcalchemy;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * this block entity is instanced by SimpleProcessorBlock at each BlockPos it is placed, it will be used to implement furnace functionality
 */
public class SimpleProcessorBlockEntity extends BlockEntity{

    //constructor
    public SimpleProcessorBlockEntity(BlockPos pos, BlockState state) {
        super(AllBlockEntityTypes.SIMPLE_PROCESSOR_BLOCK_ENTITY.get(), pos, state); //needs to get its associated BlockEntityType to construct
    }
}
