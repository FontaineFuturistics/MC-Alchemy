package org.fontainefuturistics.mcalchemy; // Define our package

import net.neoforged.neoforge.registries.DeferredRegister; // So we can create a DeferredRegister.Blocks and DeferredRegister.Items
import net.neoforged.bus.api.IEventBus; // The modEventBus passed to us by MCAlchemy.java
import net.neoforged.neoforge.registries.DeferredBlock; // The return type when a block is registered with the Deferred Register
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem; // The return type when a block item is registered with the Deferred Register
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem; // So we can create block items
import net.minecraft.world.item.Item; // So we can set the properties of our block items
import net.minecraft.world.level.block.state.BlockBehaviour; // Lets us define block properties when we create a block
import net.minecraft.world.level.material.MapColor; // Lets us define the colors of our block on the map when we instantiate a block
import net.minecraft.world.item.CreativeModeTab; // The creative mode tab class so we can create a tab for our blocks
import net.minecraft.network.chat.Component; // So we can use Component for localization
import net.minecraft.world.item.CreativeModeTabs; // The list of vanilla creative mode tabs so we can position ours

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

    // TODO add the loot table so this block drops itself when broken
    /*
     * References for this:
     * https://docs.neoforged.net/docs/resources/server/loottables/#datagen
     * https://github.com/Tutorials-By-Kaupenjoe/NeoForge-Tutorial-1.21.X/blob/main/src/main/java/net/kaupenjoe/tutorialmod/datagen/ModBlockLootTableProvider.java
     */

    // Register the block item for SimpleAlchemyBlock
    public static final DeferredItem<BlockItem> SIMPLE_ALCHEMY_BLOCK_ITEM = BLOCK_ITEMS.registerSimpleBlockItem(
        "simple_alchemy_block", // The registry name used for localization
        SIMPLE_ALCHEMY_BLOCK, // The block this block item places
        new Item.Properties() // This could be ommitted, but lets us set item properties
    );

    // Create a DeferredRegister to hold the creative mode tab
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MCAlchemy.MODID);
    // Create a creative tab that holds all blocks
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS_TAB = CREATIVE_MODE_TABS.register(
        "blocks_tab", // The registry name of the tab
        () -> CreativeModeTab.builder()
            // Define the tab properties
            .title(Component.translatable("itemGroup.mcalchemy.blocks_tab")) // Set the tab title using localization
            .withTabsBefore(CreativeModeTabs.COMBAT) // Position the tab after the combat tab
            .displayItems((parameters, output) -> {
                // Add all blocks from this class to the tab
                output.accept(SIMPLE_ALCHEMY_BLOCK_ITEM.get());
            })
            .build() // The final step: Fix the return type
    );

    // Submit our DeferredRegister to the mod event bus
    public static void register(IEventBus modEventBus) {

        BLOCKS.register(modEventBus);
        BLOCK_ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

    } // End register

} // End AllBlocks class
