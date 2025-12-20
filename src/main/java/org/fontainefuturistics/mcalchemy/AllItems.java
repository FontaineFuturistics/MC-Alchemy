package org.fontainefuturistics.mcalchemy;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab; 
import net.minecraft.world.item.CreativeModeTabs; //list of creative mode tabs
import net.minecraft.world.item.Item; //for adding items


/**
 * here we register all items that are not block items
 */
public class AllItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MCAlchemy.MODID);

    //register 'raw' item; this will be the input for the processor
    public static final DeferredItem<Item> RAW_ITEM = ITEMS.registerSimpleItem(
        "raw_item", //registry name
        new Item.Properties()
    );

    //register 'processed' item; output of processor
     public static final DeferredItem<Item> PROCESSED_ITEM = ITEMS.registerSimpleItem(
        "processed_item",
        new Item.Properties()
    );
    

    //stolen creative mode tab code, see org\fontainefuturistics\mcalchemy\AllBlocks.java 
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MCAlchemy.MODID);
    // Create a creative tab that holds all [items]
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ITEMS_TAB = CREATIVE_MODE_TABS.register(
        "items_tab", // The registry name of the tab
        () -> CreativeModeTab.builder()
            // Define the tab properties
            .title(Component.translatable("itemGroup.mcalchemy.items_tab")) // Set the tab title using localization
            .withTabsBefore(CreativeModeTabs.COMBAT) 
            .displayItems((parameters, output) -> {
                // Add all [items] from this class to the tab
                output.accept(RAW_ITEM.get());
                output.accept(PROCESSED_ITEM.get());
            })
            .build() // The final step: Fix the return type
    ); //end excerpt
    
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}

/* 
JSON doesn't allow comments, so imagine that src\main\resources\assets\mcalchemy\models\item\raw_item.json looks like this:

//models can be datagenned, do that later and remove this
{
    "parent": "minecraft:item/generated", 
    //^resource location for parent model type for items whose model is an extrusion of their texture (most items)
    
    "textures": {
        "layer0": "mcalchemy:item/raw_item" 
        //^set internal item/generated variable 'layer0' to resource location of associated texture (relative to texture folder)
    }
}
*/