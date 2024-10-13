package net.tarzan.world_depth.block;

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
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.block.custom.EnergizerBlock;
import net.tarzan.world_depth.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS=
            DeferredRegister.create(ForgeRegistries.BLOCKS, World_Depth.MODID);
    public static final RegistryObject<Block> ALUMINIUM_BLOCK=registerBlock("aluminium_block",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> RAW_ALUMINIUM_BLOCK=registerBlock("raw_aluminium_block",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
    public static final RegistryObject<Block> TITANIUM_BLOCK=registerBlock("titanium_block",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> RAW_TITANIUM_BLOCK=registerBlock("raw_titanium_block",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)));
    public static final RegistryObject<Block> ALUMINIUM_ORE=registerBlock("aluminium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEEPSLATE_ALUMINIUM_ORE=registerBlock("deepslate_aluminium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FERYL_STONE=registerBlock("feryl_stone",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.7f,7f)));
    public static final RegistryObject<Block> SOAP_STONE=registerBlock("soap_stone",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.0f,4.0f)));

    public static final RegistryObject<Block> SOAP_STONE_ALUMINIUM_ORE=registerBlock("soap_stone_aluminium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(ModBlocks.SOAP_STONE.get()).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FERYL_STONE_ALUMINIUM_ORE=registerBlock("feryl_stone_aluminium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(ModBlocks.FERYL_STONE.get()).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRANITE_ALUMINIUM_ORE=registerBlock("granite_aluminium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DIORITE_ALUMINIUM_ORE=registerBlock("diorite_aluminium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TUFF_ALUMINIUM_ORE=registerBlock("tuff_aluminium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.TUFF).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ANDESITE_ALUMINIUM_ORE=registerBlock("andesite_aluminium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GRANITE_COAL_ORE=registerBlock("granite_coal_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(0,2)));
    public static final RegistryObject<Block> DIORITE_COAL_ORE=registerBlock("diorite_coal_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(0,2)));
    public static final RegistryObject<Block> TUFF_COAL_ORE=registerBlock("tuff_coal_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.TUFF).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(0,2)));
    public static final RegistryObject<Block> ANDESITE_COAL_ORE=registerBlock("andesite_coal_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(0,2)));

    public static final RegistryObject<Block> GRANITE_IRON_ORE=registerBlock("granite_iron_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DIORITE_IRON_ORE=registerBlock("diorite_iron_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TUFF_IRON_ORE=registerBlock("tuff_iron_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.TUFF).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ANDESITE_IRON_ORE=registerBlock("andesite_iron_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GRANITE_GOLD_ORE=registerBlock("granite_gold_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DIORITE_GOLD_ORE=registerBlock("diorite_gold_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TUFF_GOLD_ORE=registerBlock("tuff_gold_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.TUFF).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ANDESITE_GOLD_ORE=registerBlock("andesite_gold_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GRANITE_COPPER_ORE=registerBlock("granite_copper_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DIORITE_COPPER_ORE=registerBlock("diorite_copper_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TUFF_COPPER_ORE=registerBlock("tuff_copper_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.TUFF).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ANDESITE_COPPER_ORE=registerBlock("andesite_copper_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GRANITE_DIAMOND_ORE=registerBlock("granite_diamond_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> DIORITE_DIAMOND_ORE=registerBlock("diorite_diamond_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> TUFF_DIAMOND_ORE=registerBlock("tuff_diamond_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.TUFF).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ANDESITE_DIAMOND_ORE=registerBlock("andesite_diamond_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> SOAP_STONE_DIAMOND_ORE=registerBlock("soap_stone_diamond_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(ModBlocks.SOAP_STONE.get()).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> FERYL_STONE_DIAMOND_ORE=registerBlock("feryl_stone_diamond_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(ModBlocks.FERYL_STONE.get()).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> GRANITE_LAPIS_ORE=registerBlock("granite_lapis_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(2, 2)));
    public static final RegistryObject<Block> DIORITE_LAPIS_ORE=registerBlock("diorite_lapis_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(2, 2)));
    public static final RegistryObject<Block> TUFF_LAPIS_ORE=registerBlock("tuff_lapis_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.TUFF).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(2, 2)));
    public static final RegistryObject<Block> ANDESITE_LAPIS_ORE=registerBlock("andesite_lapis_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(2, 2)));

    public static final RegistryObject<Block> GRANITE_REDSTONE_ORE=registerBlock("granite_redstone_ore",
            ()->new RedStoneOreBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DIORITE_REDSTONE_ORE=registerBlock("diorite_redstone_ore",
            ()->new RedStoneOreBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TUFF_REDSTONE_ORE=registerBlock("tuff_redstone_ore",
            ()->new RedStoneOreBlock(BlockBehaviour.Properties.copy(Blocks.TUFF).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ANDESITE_REDSTONE_ORE=registerBlock("andesite_redstone_ore",
            ()->new RedStoneOreBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE).strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GRANITE_EMERALD_ORE=registerBlock("granite_emerald_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> DIORITE_EMERALD_ORE=registerBlock("diorite_emerald_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> TUFF_EMERALD_ORE=registerBlock("tuff_emerald_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.TUFF).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ANDESITE_EMERALD_ORE=registerBlock("andesite_emerald_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> GRANITE_TITANIUM_ORE=registerBlock("granite_titanium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DIORITE_TITANIUM_ORE=registerBlock("diorite_titanium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TUFF_TITANIUM_ORE=registerBlock("tuff_titanium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.TUFF).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ANDESITE_TITANIUM_ORE=registerBlock("andesite_titanium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SOAP_STONE_TITANIUM_ORE=registerBlock("soap_stone_titanium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(ModBlocks.SOAP_STONE.get()).strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FERYL_STONE_TITANIUM_ORE=registerBlock("feryl_stone_titanium_ore",
            ()->new DropExperienceBlock(BlockBehaviour.Properties.copy(ModBlocks.FERYL_STONE.get()).strength(2f).requiresCorrectToolForDrops()));




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

    public static final RegistryObject<Block> ENERGIZER=registerBlock("energizer",
            ()->new EnergizerBlock(BlockBehaviour.Properties.copy(ModBlocks.SOAP_STONE.get()).noOcclusion()));

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
