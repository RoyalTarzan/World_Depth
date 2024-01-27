package net.tarzan.testmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.testmod.TestMod1;
import net.tarzan.testmod.block.ModBlocks;


public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS=
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMod1.MODID);
    public static final RegistryObject<CreativeModeTab> TEST_TAB=CREATIVE_MODE_TABS.register("test_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.ALUMINIUM.get()))
                    .title(Component.translatable("creativetab.test_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ALUMINIUM.get());
                        output.accept(ModItems.RAW_ALUMINIUM.get());
                        output.accept(ModItems.TITANIUM.get());
                        output.accept(ModItems.RAW_TITANIUM.get());
                        output.accept(ModItems.CHARGED_REDSTONE.get());
                        output.accept(ModBlocks.ALUMINIUM_BLOCK.get());
                        output.accept(ModBlocks.RAW_ALUMINIUM_BLOCK.get());
                        output.accept(ModBlocks.TITANIUM_BLOCK.get());
                        output.accept(ModBlocks.RAW_TITANIUM_BLOCK.get());
                        output.accept(ModBlocks.ALUMINIUM_ORE.get());
                        output.accept(ModBlocks.SOAP_STONE_ALUMINIUM_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get());
                        output.accept(ModBlocks.FERYL_STONE.get());
                        output.accept(ModBlocks.SOAP_STONE.get());
                        output.accept(ModBlocks.FERYL_STAIRS.get());
                        output.accept(ModBlocks.FERYL_SLAB.get());
                        output.accept(ModBlocks.FERYL_BUTTON.get());
                        output.accept(ModBlocks.FERYL_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.FERYL_WALL.get());
                        output.accept(ModBlocks.SOAP_STONE_STAIRS.get());
                        output.accept(ModBlocks.SOAP_STONE_SLAB.get());
                        output.accept(ModBlocks.SOAP_STONE_BUTTON.get());
                        output.accept(ModBlocks.SOAP_STONE_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.SOAP_STONE_WALL.get());
                        output.accept(ModBlocks.ANDESITE_COAL_ORE.get());
                        output.accept(ModBlocks.DIORITE_COAL_ORE.get());
                        output.accept(ModBlocks.GRANITE_COAL_ORE.get());
                        output.accept(ModBlocks.TUFF_COAL_ORE.get());
                        output.accept(ModBlocks.FERYL_STONE_ALUMINIUM_ORE.get());

                    })
                    .build());
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
