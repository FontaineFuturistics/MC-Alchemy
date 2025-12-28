package org.fontainefuturistics.mcalchemy.block;

import java.util.function.Supplier;

import org.fontainefuturistics.mcalchemy.MCAlchemy;
import org.fontainefuturistics.mcalchemy.item.AllItems;
import net.minecraft.world.level.block.Blocks; // This is where all vanilla blocks are defined, interesting to peruse
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("unused") // Because I have included the vanilla minecraft blocks as an import just for perusal
public class AllBlocks {
    
    // Make the block register
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MCAlchemy.MOD_ID);

    // Make a block
    /* Steps:
     * 1) Define and register the block below
     * 2) Set its properties using Properties.of(), can reference the vanilla blocks to see how properties work
     * 3) Add it to a creative tab in MCAlchemy.java
     * 4) Create a blockstate json in resources/assets/mcalchemy/blockstates that defines what model to use
     * 5) Create a model json in models/block that defines what texture to use
     * 6) Put the texture referenced above in textures/block
     * 7) Create a model for the blockitem in models/item, this model simply points at the block model to reuse it
     * 8) Create localizations for block.mcalchemy.example_block
     */
    public static final DeferredBlock<Block> EXAMPLE_BLOCK = registerBlock("example_block", 
        () -> new Block(BlockBehaviour.Properties.of()
            .strength(4f) // Set mining strength
            .requiresCorrectToolForDrops() // Makes it so the block isn't hand mineable
            .sound(SoundType.AMETHYST) // Set the sound from an existing default
    ));

    public static final DeferredBlock<Block> EXAMPLE_BLOCK_ORE = registerBlock("example_block_ore",
        () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
            .strength(3f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE)
    ));

    // Help method to create a block and block item
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        
        // Register the block
        DeferredBlock<T> newBlock = BLOCKS.register(name, block);

        // Make it a block item
        AllItems.registerBlockItem(name, newBlock);

        // Return it
        return newBlock;

    } // End registerBlock

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    } // End register

} // End AllBlocks class
