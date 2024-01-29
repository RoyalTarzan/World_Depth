package net.tarzan.testmod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.testmod.TestMod1;
import net.tarzan.testmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS=
            DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod1.MODID);
    public static final RegistryObject<Block> ALUMINIUM_BLOCK=registerBlock("aluminium_block",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> RAW_ALUMINIUM_BLOCK=registerBlock("raw_aluminium_block",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
    public static final RegistryObject<Block> TITANIUM_BLOCK=registerBlock("titanium_block",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> RAW_TITANIUM_BLOCK=registerBlock("raw_titanium_block",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)));
    public static final RegistryObject<Block> ALUMINIUM_ORE=registerBlock("aluminium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3,6)));
    public static final RegistryObject<Block> DEEPSLATE_ALUMINIUM_ORE=registerBlock("deepslate_aluminium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3,6)));
    public static final RegistryObject<Block> FERYL_STONE=registerBlock("feryl_stone",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.7f,7f)));
    public static final RegistryObject<Block> SOAP_STONE=registerBlock("soap_stone",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.0f,4.0f)));
    public static final RegistryObject<Block> SOAP_STONE_ALUMINIUM_ORE=registerBlock("soap_stone_aluminium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(ModBlocks.SOAP_STONE.get()).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3,6)));
    public static final RegistryObject<Block> FERYL_STONE_ALUMINIUM_ORE=registerBlock("feryl_stone_aluminium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(ModBlocks.FERYL_STONE.get()).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3,6)));
    public static final RegistryObject<Block> GRANITE_COAL_ORE=registerBlock("granite_coal_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(0,2)));
    public static final RegistryObject<Block> DIORITE_COAL_ORE=registerBlock("diorite_coal_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(0,2)));
    public static final RegistryObject<Block> TUFF_COAL_ORE=registerBlock("tuff_coal_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.TUFF).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(0,2)));
    public static final RegistryObject<Block> ANDESITE_COAL_ORE=registerBlock("andesite_coal_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(0,2)));

    public static final RegistryObject<Block> FERYL_STAIRS=registerBlock("feryl_stairs",
            ()->new StairBlock(()->ModBlocks.FERYL_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.7f,7f)));
    public static final RegistryObject<Block> FERYL_SLAB=registerBlock("feryl_slab",
            ()->new SlabBlock(BlockBehaviour.Properties.copy(ModBlocks.FERYL_STONE.get()).strength(1.7f,7f)));

    public static final RegistryObject<Block> FERYL_BUTTON=registerBlock("feryl_button",
            ()->new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON), BlockSetType.STONE,10,true));
    public static final RegistryObject<Block> FERYL_PRESSURE_PLATE=registerBlock("feryl_pressure_plate",
            ()->new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS,BlockBehaviour.Properties.copy(ModBlocks.FERYL_STONE.get()), BlockSetType.STONE));

    public static final RegistryObject<Block> FERYL_WALL=registerBlock("feryl_wall",
            ()->new WallBlock(BlockBehaviour.Properties.copy(ModBlocks.FERYL_STONE.get()).strength(1.7f,7f)));

    public static final RegistryObject<Block> SOAP_STONE_STAIRS=registerBlock("soap_stone_stairs",
            ()->new StairBlock(()->ModBlocks.SOAP_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.0f,7f)));
    public static final RegistryObject<Block> SOAP_STONE_SLAB=registerBlock("soap_stone_slab",
            ()->new SlabBlock(BlockBehaviour.Properties.copy(ModBlocks.SOAP_STONE.get()).strength(1.0f,7f)));

    public static final RegistryObject<Block> SOAP_STONE_BUTTON=registerBlock("soap_stone_button",
            ()->new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON), BlockSetType.GOLD,5,true));
    public static final RegistryObject<Block> SOAP_STONE_PRESSURE_PLATE=registerBlock("soap_stone_pressure_plate",
            ()->new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(ModBlocks.SOAP_STONE.get()), BlockSetType.GOLD));

    public static final RegistryObject<Block> SOAP_STONE_WALL=registerBlock("soap_stone_wall",
            ()->new WallBlock(BlockBehaviour.Properties.copy(ModBlocks.SOAP_STONE.get())));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn=BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, ()->new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
