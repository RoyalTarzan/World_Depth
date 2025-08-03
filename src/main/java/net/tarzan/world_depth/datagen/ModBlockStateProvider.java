package net.tarzan.world_depth.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, World_Depth.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ALUMINIUM_BLOCK);
        blockWithItem(ModBlocks.RAW_ALUMINIUM_BLOCK);
        blockWithItem(ModBlocks.RAW_TITANIUM_BLOCK);
        blockWithItem(ModBlocks.TITANIUM_BLOCK);
        blockWithItem(ModBlocks.TALIUM_BLOCK);
        blockWithItem(ModBlocks.FERYL_STONE);
        blockWithItem(ModBlocks.SOAP_STONE);
        blockWithItem(ModBlocks.COBBLED_FERYL_STONE);
        blockWithItem(ModBlocks.COBBLED_SOAP_STONE);
        blockWithItem(ModBlocks.CHARGED_REDSTONE_BLOCK);
        blockWithItem(ModBlocks.DEEP_LIGHT);

        blockWithItem(ModBlocks.ALUMINIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ALUMINIUM_ORE);
        blockWithItem(ModBlocks.GRANITE_ALUMINIUM_ORE);
        blockWithItem(ModBlocks.DIORITE_ALUMINIUM_ORE);
        blockWithItem(ModBlocks.TUFF_ALUMINIUM_ORE);
        blockWithItem(ModBlocks.ANDESITE_ALUMINIUM_ORE);
        blockWithItem(ModBlocks.SOAP_STONE_ALUMINIUM_ORE);
        blockWithItem(ModBlocks.FERYL_STONE_ALUMINIUM_ORE);

        blockWithItem(ModBlocks.GRANITE_COAL_ORE);
        blockWithItem(ModBlocks.DIORITE_COAL_ORE);
        blockWithItem(ModBlocks.TUFF_COAL_ORE);
        blockWithItem(ModBlocks.ANDESITE_COAL_ORE);
        blockWithItem(ModBlocks.SOAP_STONE_COAL_ORE);
        blockWithItem(ModBlocks.FERYL_STONE_COAL_ORE);

        blockWithItem(ModBlocks.GRANITE_IRON_ORE);
        blockWithItem(ModBlocks.DIORITE_IRON_ORE);
        blockWithItem(ModBlocks.TUFF_IRON_ORE);
        blockWithItem(ModBlocks.ANDESITE_IRON_ORE);
        blockWithItem(ModBlocks.SOAP_STONE_IRON_ORE);
        blockWithItem(ModBlocks.FERYL_STONE_IRON_ORE);

        blockWithItem(ModBlocks.GRANITE_GOLD_ORE);
        blockWithItem(ModBlocks.DIORITE_GOLD_ORE);
        blockWithItem(ModBlocks.TUFF_GOLD_ORE);
        blockWithItem(ModBlocks.ANDESITE_GOLD_ORE);
        blockWithItem(ModBlocks.SOAP_STONE_GOLD_ORE);
        blockWithItem(ModBlocks.FERYL_STONE_GOLD_ORE);

        blockWithItem(ModBlocks.GRANITE_COPPER_ORE);
        blockWithItem(ModBlocks.DIORITE_COPPER_ORE);
        blockWithItem(ModBlocks.TUFF_COPPER_ORE);
        blockWithItem(ModBlocks.ANDESITE_COPPER_ORE);
        blockWithItem(ModBlocks.SOAP_STONE_COPPER_ORE);
        blockWithItem(ModBlocks.FERYL_STONE_COPPER_ORE);

        blockWithItem(ModBlocks.GRANITE_DIAMOND_ORE);
        blockWithItem(ModBlocks.DIORITE_DIAMOND_ORE);
        blockWithItem(ModBlocks.TUFF_DIAMOND_ORE);
        blockWithItem(ModBlocks.ANDESITE_DIAMOND_ORE);
        blockWithItem(ModBlocks.SOAP_STONE_DIAMOND_ORE);
        blockWithItem(ModBlocks.FERYL_STONE_DIAMOND_ORE);

        blockWithItem(ModBlocks.GRANITE_LAPIS_ORE);
        blockWithItem(ModBlocks.DIORITE_LAPIS_ORE);
        blockWithItem(ModBlocks.TUFF_LAPIS_ORE);
        blockWithItem(ModBlocks.ANDESITE_LAPIS_ORE);
        blockWithItem(ModBlocks.SOAP_STONE_LAPIS_ORE);
        blockWithItem(ModBlocks.FERYL_STONE_LAPIS_ORE);

        blockWithItem(ModBlocks.GRANITE_REDSTONE_ORE);
        blockWithItem(ModBlocks.DIORITE_REDSTONE_ORE);
        blockWithItem(ModBlocks.TUFF_REDSTONE_ORE);
        blockWithItem(ModBlocks.ANDESITE_REDSTONE_ORE);
        blockWithItem(ModBlocks.SOAP_STONE_REDSTONE_ORE);
        blockWithItem(ModBlocks.FERYL_STONE_REDSTONE_ORE);

        blockWithItem(ModBlocks.GRANITE_EMERALD_ORE);
        blockWithItem(ModBlocks.DIORITE_EMERALD_ORE);
        blockWithItem(ModBlocks.TUFF_EMERALD_ORE);
        blockWithItem(ModBlocks.ANDESITE_EMERALD_ORE);
        blockWithItem(ModBlocks.SOAP_STONE_EMERALD_ORE);
        blockWithItem(ModBlocks.FERYL_STONE_EMERALD_ORE);

        blockWithItem(ModBlocks.TITANIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_TITANIUM_ORE);
        blockWithItem(ModBlocks.GRANITE_TITANIUM_ORE);
        blockWithItem(ModBlocks.DIORITE_TITANIUM_ORE);
        blockWithItem(ModBlocks.TUFF_TITANIUM_ORE);
        blockWithItem(ModBlocks.ANDESITE_TITANIUM_ORE);
        blockWithItem(ModBlocks.SOAP_STONE_TITANIUM_ORE);
        blockWithItem(ModBlocks.FERYL_STONE_TITANIUM_ORE);

        stairsBlock(((StairBlock) ModBlocks.FERYL_STAIRS.get()), blockTexture(ModBlocks.FERYL_STONE.get()));
        slabBlock(((SlabBlock) ModBlocks.FERYL_SLAB.get()), blockTexture(ModBlocks.FERYL_STONE.get()),blockTexture(ModBlocks.FERYL_STONE.get()));
        stairsBlock(((StairBlock) ModBlocks.SOAP_STONE_STAIRS.get()), blockTexture(ModBlocks.SOAP_STONE.get()));
        slabBlock(((SlabBlock) ModBlocks.SOAP_STONE_SLAB.get()), blockTexture(ModBlocks.SOAP_STONE.get()),blockTexture(ModBlocks.SOAP_STONE.get()));
        stairsBlock(((StairBlock) ModBlocks.COBBLED_FERYL_STAIRS.get()), blockTexture(ModBlocks.COBBLED_FERYL_STONE.get()));
        slabBlock(((SlabBlock) ModBlocks.COBBLED_FERYL_SLAB.get()), blockTexture(ModBlocks.COBBLED_FERYL_STONE.get()),blockTexture(ModBlocks.COBBLED_FERYL_STONE.get()));
        stairsBlock(((StairBlock) ModBlocks.COBBLED_SOAP_STONE_STAIRS.get()), blockTexture(ModBlocks.COBBLED_SOAP_STONE.get()));
        slabBlock(((SlabBlock) ModBlocks.COBBLED_SOAP_STONE_SLAB.get()), blockTexture(ModBlocks.COBBLED_SOAP_STONE.get()),blockTexture(ModBlocks.COBBLED_SOAP_STONE.get()));

        buttonBlock(((ButtonBlock) ModBlocks.FERYL_BUTTON.get()), blockTexture(ModBlocks.FERYL_STONE.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.FERYL_PRESSURE_PLATE.get()), blockTexture(ModBlocks.FERYL_STONE.get()));
        buttonBlock(((ButtonBlock) ModBlocks.SOAP_STONE_BUTTON.get()), blockTexture(ModBlocks.SOAP_STONE.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.SOAP_STONE_PRESSURE_PLATE.get()), blockTexture(ModBlocks.SOAP_STONE.get()));
        buttonBlock(((ButtonBlock) ModBlocks.COBBLED_FERYL_BUTTON.get()), blockTexture(ModBlocks.COBBLED_FERYL_STONE.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.COBBLED_FERYL_PRESSURE_PLATE.get()), blockTexture(ModBlocks.COBBLED_FERYL_STONE.get()));
        buttonBlock(((ButtonBlock) ModBlocks.COBBLED_SOAP_STONE_BUTTON.get()), blockTexture(ModBlocks.COBBLED_SOAP_STONE.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.COBBLED_SOAP_STONE_PRESSURE_PLATE.get()), blockTexture(ModBlocks.COBBLED_SOAP_STONE.get()));

        wallBlock(((WallBlock) ModBlocks.FERYL_WALL.get()), blockTexture(ModBlocks.FERYL_STONE.get()));
        wallBlock(((WallBlock) ModBlocks.SOAP_STONE_WALL.get()), blockTexture(ModBlocks.SOAP_STONE.get()));
        wallBlock(((WallBlock) ModBlocks.COBBLED_FERYL_WALL.get()), blockTexture(ModBlocks.COBBLED_FERYL_STONE.get()));
        wallBlock(((WallBlock) ModBlocks.COBBLED_SOAP_STONE_WALL.get()), blockTexture(ModBlocks.COBBLED_SOAP_STONE.get()));

        saplingBlock(ModBlocks.STOOK_SAPLING);
        leavesBlock(ModBlocks.STOOK_LEAVES);
        logBlock(((RotatedPillarBlock) ModBlocks.STOOK_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STOOK_WOOD.get()),blockTexture(ModBlocks.STOOK_LOG.get()),blockTexture(ModBlocks.STOOK_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_STOOK_LOG.get()),blockTexture(ModBlocks.STRIPPED_STOOK_LOG.get()),
                new ResourceLocation(World_Depth.MODID,"block/stripped_stook_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_STOOK_WOOD.get()),blockTexture(ModBlocks.STRIPPED_STOOK_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_STOOK_LOG.get()));

        blockItem(ModBlocks.STOOK_LOG);
        blockItem(ModBlocks.STRIPPED_STOOK_LOG);
        blockItem(ModBlocks.STRIPPED_STOOK_WOOD);
        blockItem(ModBlocks.STOOK_WOOD);

        simpleBlockWithItem(ModBlocks.ENERGIZER.get(),new ModelFile.UncheckedModelFile(modLoc("block/energizer")));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(World_Depth.MODID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(),cubeAll(blockRegistryObject.get()));
    }
}
