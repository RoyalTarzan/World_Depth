package net.tarzan.world_depth.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.block.ModBlocks;


public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS=
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, World_Depth.MODID);
    public static final RegistryObject<CreativeModeTab> TEST_TAB=CREATIVE_MODE_TABS.register("test_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.ALUMINIUM.get()))
                    .title(Component.translatable("creativetab.test_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ALUMINIUM.get());
                        output.accept(ModItems.RAW_ALUMINIUM.get());
                        output.accept(ModBlocks.ALUMINIUM_BLOCK.get());
                        output.accept(ModBlocks.RAW_ALUMINIUM_BLOCK.get());

                        output.accept(ModItems.ALUMINIUM_BOOTS.get());
                        output.accept(ModItems.ALUMINIUM_LEGGINGS.get());
                        output.accept(ModItems.ALUMINIUM_CHESTPLATE.get());
                        output.accept(ModItems.ALUMINIUM_HELMET.get());
                        output.accept(ModItems.ENERGIZED_ALUMINIUM_BOOTS.get());
                        output.accept(ModItems.ENERGIZED_ALUMINIUM_LEGGINGS.get());
                        output.accept(ModItems.ENERGIZED_ALUMINIUM_CHESTPLATE.get());
                        output.accept(ModItems.ENERGIZED_ALUMINIUM_HELMET.get());

                        output.accept(ModItems.TITANIUM_HELMET.get());
                        output.accept(ModItems.TITANIUM_CHESTPLATE.get());
                        output.accept(ModItems.TITANIUM_LEGGINGS.get());
                        output.accept(ModItems.TITANIUM_BOOTS.get());
                        output.accept(ModItems.ENERGIZED_TITANIUM_HELMET.get());
                        output.accept(ModItems.ENERGIZED_TITANIUM_CHESTPLATE.get());
                        output.accept(ModItems.ENERGIZED_TITANIUM_LEGGINGS.get());
                        output.accept(ModItems.ENERGIZED_TITANIUM_BOOTS.get());

                        output.accept(ModItems.TALIUM_HELMET.get());
                        output.accept(ModItems.TALIUM_CHESTPLATE.get());
                        output.accept(ModItems.TITANIUM_LEGGINGS.get());
                        output.accept(ModItems.TITANIUM_BOOTS.get());

                        output.accept(ModItems.TITANIUM.get());
                        output.accept(ModItems.RAW_TITANIUM.get());
                        output.accept(ModBlocks.TITANIUM_BLOCK.get());
                        output.accept(ModBlocks.RAW_TITANIUM_BLOCK.get());
                        output.accept(ModItems.CHARGED_REDSTONE.get());
                        output.accept(ModBlocks.CHARGED_REDSTONE_BLOCK.get());
                        output.accept(ModItems.ENERGIZED_TITANIUM.get());
                        output.accept(ModItems.ENERGIZED_ALUMINIUM.get());
                        output.accept(ModItems.TALIUM.get());

                        output.accept(ModBlocks.ENERGIZER.get());

                        output.accept(ModItems.ALUMINIUM_AXE.get());
                        output.accept(ModItems.ALUMINIUM_HOE.get());
                        output.accept(ModItems.ALUMINIUM_PICKAXE.get());
                        output.accept(ModItems.ALUMINIUM_SWORD.get());
                        output.accept(ModItems.ALUMINIUM_SHOVEL.get());

                        output.accept(ModItems.TITANIUM_AXE.get());
                        output.accept(ModItems.TITANIUM_HOE.get());
                        output.accept(ModItems.TITANIUM_PICKAXE.get());
                        output.accept(ModItems.TITANIUM_SWORD.get());
                        output.accept(ModItems.TITANIUM_SHOVEL.get());

                        output.accept(ModBlocks.FERYL_STONE.get());
                        output.accept(ModBlocks.FERYL_STAIRS.get());
                        output.accept(ModBlocks.FERYL_SLAB.get());
                        output.accept(ModBlocks.FERYL_BUTTON.get());
                        output.accept(ModBlocks.FERYL_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.FERYL_WALL.get());

                        output.accept(ModBlocks.SOAP_STONE.get());
                        output.accept(ModBlocks.SOAP_STONE_STAIRS.get());
                        output.accept(ModBlocks.SOAP_STONE_SLAB.get());
                        output.accept(ModBlocks.SOAP_STONE_BUTTON.get());
                        output.accept(ModBlocks.SOAP_STONE_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.SOAP_STONE_WALL.get());

                        output.accept(ModBlocks.ANDESITE_COAL_ORE.get());
                        output.accept(ModBlocks.DIORITE_COAL_ORE.get());
                        output.accept(ModBlocks.GRANITE_COAL_ORE.get());
                        output.accept(ModBlocks.TUFF_COAL_ORE.get());

                        output.accept(ModBlocks.ANDESITE_IRON_ORE.get());
                        output.accept(ModBlocks.DIORITE_IRON_ORE.get());
                        output.accept(ModBlocks.GRANITE_IRON_ORE.get());
                        output.accept(ModBlocks.TUFF_IRON_ORE.get());

                        output.accept(ModBlocks.ANDESITE_GOLD_ORE.get());
                        output.accept(ModBlocks.DIORITE_GOLD_ORE.get());
                        output.accept(ModBlocks.GRANITE_GOLD_ORE.get());
                        output.accept(ModBlocks.TUFF_GOLD_ORE.get());

                        output.accept(ModBlocks.ANDESITE_COPPER_ORE.get());
                        output.accept(ModBlocks.DIORITE_COPPER_ORE.get());
                        output.accept(ModBlocks.GRANITE_COPPER_ORE.get());
                        output.accept(ModBlocks.TUFF_COPPER_ORE.get());

                        output.accept(ModBlocks.ANDESITE_DIAMOND_ORE.get());
                        output.accept(ModBlocks.DIORITE_DIAMOND_ORE.get());
                        output.accept(ModBlocks.GRANITE_DIAMOND_ORE.get());
                        output.accept(ModBlocks.TUFF_DIAMOND_ORE.get());
                        output.accept(ModBlocks.SOAP_STONE_DIAMOND_ORE.get());
                        output.accept(ModBlocks.FERYL_STONE_DIAMOND_ORE.get());

                        output.accept(ModBlocks.ANDESITE_LAPIS_ORE.get());
                        output.accept(ModBlocks.DIORITE_LAPIS_ORE.get());
                        output.accept(ModBlocks.GRANITE_LAPIS_ORE.get());
                        output.accept(ModBlocks.TUFF_LAPIS_ORE.get());

                        output.accept(ModBlocks.ANDESITE_REDSTONE_ORE.get());
                        output.accept(ModBlocks.DIORITE_REDSTONE_ORE.get());
                        output.accept(ModBlocks.GRANITE_REDSTONE_ORE.get());
                        output.accept(ModBlocks.TUFF_REDSTONE_ORE.get());

                        output.accept(ModBlocks.ANDESITE_EMERALD_ORE.get());
                        output.accept(ModBlocks.DIORITE_EMERALD_ORE.get());
                        output.accept(ModBlocks.GRANITE_EMERALD_ORE.get());
                        output.accept(ModBlocks.TUFF_EMERALD_ORE.get());

                        output.accept(ModBlocks.ANDESITE_TITANIUM_ORE.get());
                        output.accept(ModBlocks.DIORITE_TITANIUM_ORE.get());
                        output.accept(ModBlocks.GRANITE_TITANIUM_ORE.get());
                        output.accept(ModBlocks.TUFF_TITANIUM_ORE.get());
                        output.accept(ModBlocks.SOAP_STONE_TITANIUM_ORE.get());
                        output.accept(ModBlocks.FERYL_STONE_TITANIUM_ORE.get());

                        output.accept(ModBlocks.ALUMINIUM_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get());
                        output.accept(ModBlocks.SOAP_STONE_ALUMINIUM_ORE.get());
                        output.accept(ModBlocks.FERYL_STONE_ALUMINIUM_ORE.get());
                        output.accept(ModBlocks.ANDESITE_ALUMINIUM_ORE.get());
                        output.accept(ModBlocks.TUFF_ALUMINIUM_ORE.get());
                        output.accept(ModBlocks.GRANITE_ALUMINIUM_ORE.get());
                        output.accept(ModBlocks.DIORITE_ALUMINIUM_ORE.get());
                    })
                    .build());
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
