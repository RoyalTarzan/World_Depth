package net.tarzan.testmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tarzan.testmod.TestMod1;
import net.tarzan.testmod.block.ModBlocks;
import net.tarzan.testmod.util.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TestMod1.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        this.tag(ModTags.Blocks.ADDED_ORES)
                .add(ModBlocks.ALUMINIUM_ORE.get(),
                ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get(),
                ModBlocks.SOAP_STONE_ALUMINIUM_ORE.get(),
                ModBlocks.FERYL_STONE_ALUMINIUM_ORE.get(),
                ModBlocks.GRANITE_COAL_ORE.get(),
                ModBlocks.DIORITE_COAL_ORE.get(),
                ModBlocks.TUFF_COAL_ORE.get(),
                ModBlocks.ANDESITE_COAL_ORE.get(),
                        ModBlocks.GRANITE_IRON_ORE.get(),
                        ModBlocks.DIORITE_IRON_ORE.get(),
                        ModBlocks.TUFF_IRON_ORE.get(),
                        ModBlocks.ANDESITE_IRON_ORE.get(),
                        ModBlocks.GRANITE_ALUMINIUM_ORE.get(),
                        ModBlocks.DIORITE_ALUMINIUM_ORE.get(),
                        ModBlocks.TUFF_ALUMINIUM_ORE.get(),
                        ModBlocks.ANDESITE_ALUMINIUM_ORE.get(),
                        ModBlocks.GRANITE_GOLD_ORE.get(),
                        ModBlocks.DIORITE_GOLD_ORE.get(),
                        ModBlocks.TUFF_GOLD_ORE.get(),
                        ModBlocks.ANDESITE_GOLD_ORE.get(),
                        ModBlocks.GRANITE_COPPER_ORE.get(),
                        ModBlocks.DIORITE_COPPER_ORE.get(),
                        ModBlocks.TUFF_COPPER_ORE.get(),
                        ModBlocks.ANDESITE_COPPER_ORE.get(),
                        ModBlocks.GRANITE_DIAMOND_ORE.get(),
                        ModBlocks.DIORITE_DIAMOND_ORE.get(),
                        ModBlocks.TUFF_DIAMOND_ORE.get(),
                        ModBlocks.ANDESITE_DIAMOND_ORE.get(),
                        ModBlocks.SOAP_STONE_DIAMOND_ORE.get(),
                        ModBlocks.FERYL_STONE_DIAMOND_ORE.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ALUMINIUM_BLOCK.get(),
                ModBlocks.RAW_ALUMINIUM_BLOCK.get(),
                ModBlocks.RAW_TITANIUM_BLOCK.get(),
                ModBlocks.TITANIUM_BLOCK.get(),
                ModBlocks.FERYL_STONE.get(),
                ModBlocks.SOAP_STONE.get(),
                        ModBlocks.SOAP_STONE_BUTTON.get(),
                        ModBlocks.SOAP_STONE_SLAB.get(),
                        ModBlocks.SOAP_STONE_PRESSURE_PLATE.get(),
                        ModBlocks.SOAP_STONE_STAIRS.get(),
                        ModBlocks.FERYL_BUTTON.get(),
                        ModBlocks.FERYL_SLAB.get(),
                        ModBlocks.FERYL_PRESSURE_PLATE.get(),
                        ModBlocks.FERYL_STAIRS.get())
                .addTag(ModTags.Blocks.ADDED_ORES)
                .addTag(BlockTags.WALLS);

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.RAW_TITANIUM_BLOCK.get())
                .add(ModBlocks.TITANIUM_BLOCK.get());

        this.tag(BlockTags.WALLS)
                .add(ModBlocks.FERYL_WALL.get(), ModBlocks.SOAP_STONE_WALL.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.GRANITE_COAL_ORE.get(),
                        ModBlocks.DIORITE_COAL_ORE.get(),
                        ModBlocks.TUFF_COAL_ORE.get(),
                        ModBlocks.ANDESITE_COAL_ORE.get(),
                        ModBlocks.GRANITE_IRON_ORE.get(),
                        ModBlocks.DIORITE_IRON_ORE.get(),
                        ModBlocks.TUFF_IRON_ORE.get(),
                        ModBlocks.ANDESITE_IRON_ORE.get(),
                        ModBlocks.GRANITE_COPPER_ORE.get(),
                        ModBlocks.DIORITE_COPPER_ORE.get(),
                        ModBlocks.TUFF_COPPER_ORE.get(),
                        ModBlocks.ANDESITE_COPPER_ORE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ALUMINIUM_BLOCK.get(),
                        ModBlocks.GRANITE_ALUMINIUM_ORE.get(),
                        ModBlocks.DIORITE_ALUMINIUM_ORE.get(),
                        ModBlocks.TUFF_ALUMINIUM_ORE.get(),
                        ModBlocks.ANDESITE_ALUMINIUM_ORE.get(),
                        ModBlocks.GRANITE_GOLD_ORE.get(),
                        ModBlocks.DIORITE_GOLD_ORE.get(),
                        ModBlocks.TUFF_GOLD_ORE.get(),
                        ModBlocks.ANDESITE_GOLD_ORE.get(),
                        ModBlocks.RAW_ALUMINIUM_BLOCK.get(),
                        ModBlocks.ALUMINIUM_ORE.get(),
                        ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get(),
                        ModBlocks.SOAP_STONE_ALUMINIUM_ORE.get(),
                        ModBlocks.GRANITE_DIAMOND_ORE.get(),
                        ModBlocks.DIORITE_DIAMOND_ORE.get(),
                        ModBlocks.TUFF_DIAMOND_ORE.get(),
                        ModBlocks.ANDESITE_DIAMOND_ORE.get(),
                        ModBlocks.SOAP_STONE_DIAMOND_ORE.get(),
                        ModBlocks.FERYL_STONE_DIAMOND_ORE.get());
    }
}
