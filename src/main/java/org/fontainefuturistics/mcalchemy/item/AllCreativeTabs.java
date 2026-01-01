package org.fontainefuturistics.mcalchemy.item;

import java.util.function.Supplier;

import org.fontainefuturistics.mcalchemy.MCAlchemy;
import org.fontainefuturistics.mcalchemy.block.AllBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllCreativeTabs {
    
    // Get the deferred register
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MCAlchemy.MOD_ID);

    // Make a new tab
    public static final Supplier<CreativeModeTab> MOD_ITEMS_TAB = CREATIVE_MODE_TAB.register("mcalchemy_items_tab", // Tab's internal name
        () -> CreativeModeTab.builder()
        // Configure tab here
        .icon(() -> new ItemStack(AllItems.RAW_EXAMPLE_ITEM.get())) // Configure icon to be an item stack
        .title(Component.translatable("creativetab.mcalchemy.mod_items")) // Make a translatable title
        .displayItems((itemDisplayParameters, output) -> { // List the items
            output.accept(AllItems.EXAMPLE_ITEM);
            output.accept(AllItems.RAW_EXAMPLE_ITEM);
        })
        // Build the tab
        .build()
    );

    // Make a second tab
    public static final Supplier<CreativeModeTab> MOD_BLOCKS_TAB = CREATIVE_MODE_TAB.register("mcalchemy_blocks_tab", // Tab's internal name
        () -> CreativeModeTab.builder()
        // Configure tab here
        .icon(() -> new ItemStack(AllBlocks.EXAMPLE_BLOCK_ORE)) // Configure icon to be an item stack
        .title(Component.translatable("creativetab.mcalchemy.mod_blocks")) // Make a translatable title
        .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MCAlchemy.MOD_ID, "mcalchemy_items_tab")) // Obtuse way we have to position the tab
        .displayItems((itemDisplayParameters, output) -> { // List the items
            output.accept(AllBlocks.EXAMPLE_BLOCK);
            output.accept(AllBlocks.EXAMPLE_BLOCK_ORE);
        })
        // Build the tab
        .build()
    );

    // Register method
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    } // End register

} // End AllCreativeTabs
