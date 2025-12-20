package org.fontainefuturistics.mcalchemy;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType; //so we can make BlockEntityType(s)
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier; //the docs say to use a supplier... i'll get back to you on why

/**
 * this be the place fer all yer BlockEntityType registration needs!
 */
public class AllBlockEntityTypes {

    //the DeferredRegister to hold the BlockEntityType(s);
    //a BlockEntityType is a singleton like Block and Item, whilst a BlockEntity is instanced at a coordinate (when the block is placed)
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = 
    DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MCAlchemy.MODID); 

    //register SimpleProcessorBlockEntity's type
    public static final Supplier<BlockEntityType<SimpleProcessorBlockEntity>> SIMPLE_PROCESSOR_BLOCK_ENTITY = 
    BLOCK_ENTITY_TYPES.register(
        "simple_processor_block_entity", 
        () -> BlockEntityType.Builder.of(
            SimpleProcessorBlockEntity::new, 
            AllBlocks.SIMPLE_PROCESSOR_BLOCK.get() //vararg of blocks with which this entity may associate. we only have one... for now.
        ).build(null) //Builder.of returns new Builder, Builder.build returns new BlockEntityType
    /*^the method described on the docs involving BlockEntityType's constructor throws an exception (i don't know why). 
    instead, we obtain the requisite BlockEntityType from its Builder class.*/
    );

    public static void register(IEventBus modEventBus) {
        BLOCK_ENTITY_TYPES.register(modEventBus);
    }
}
