package org.fontainefuturistics.mcalchemy; // Define our package

import net.neoforged.neoforge.registries.DeferredRegister; // So we can create a DeferredRegister.Blocks and DeferredRegister.Items
import net.neoforged.bus.api.IEventBus; // The modEventBus passed to us by MCAlchemy.java
import net.neoforged.neoforge.registries.DeferredBlock; // The return type when a block is registered with the Deferred Register
import net.neoforged.neoforge.registries.DeferredItem; // The return type when a block item is registered with the Deferred Register
import net.minecraft.world.item.BlockItem; // So we can create block items
import net.minecraft.world.item.Item; // So we can set the properties of our block items
import net.minecraft.world.level.block.state.BlockBehaviour; // Lets us define block properties when we create a block
import net.minecraft.world.level.material.MapColor; // Lets us define the colors of our block on the map when we instantiate a block

/**
 * This class will register all blocks
 */
public class AllBlocks {
    
    // Create a new DeferredRegister.Blocks to hold all blocks for this class
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MCAlchemy.MODID);

    // Create a DeferredRegister.Items to hold all the blockitems for the blocks in this class
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(MCAlchemy.MODID);

    // Register SimpleAlchemyBlock
    public static final DeferredBlock<SimpleAlchemyBlock> SIMPLE_ALCHEMY_BLOCK = BLOCKS.registerBlock(
        "simple_alchemy_block", // Set the block's registry name to "mcalchemy:simple_alchemy_block"
        // Instantiate the block
        registyName -> new SimpleAlchemyBlock(
            BlockBehaviour.Properties.of()
                // Set block properties
                .mapColor(MapColor.STONE)
        ) // End SimpleAlchemyBlock instantiation
    ); // End SIMPLE_ALCHEMY_BLOCK registerBlock() call

    // Register the block item for SimpleAlchemyBlock
    public static final DeferredItem<BlockItem> SIMPLE_ALCHEMY_BLOCK_ITEM = BLOCK_ITEMS.registerSimpleBlockItem(
        "simple_alchemy_block", // The registry name used for localization
        SIMPLE_ALCHEMY_BLOCK, // The block this block item places
        new Item.Properties() // This could be ommitted, but lets us set item properties
    );

    // TODO create a creative menu tab for blocks

    // Submit our DeferredRegister to the mod event bus
    public static void register(IEventBus modEventBus) {

        BLOCKS.register(modEventBus);
        BLOCK_ITEMS.register(modEventBus);

    } // End register

} // End AllBlocks class
