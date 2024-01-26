package net.tarzan.testmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tarzan.testmod.TestMod1;
import net.tarzan.testmod.block.ModBlocks;
import net.tarzan.testmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TestMod1.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Blocks.ADDED_ORES).add(ModBlocks.ALUMINIUM_ORE.get()).add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get()).add(ModBlocks.SOAP_STONE_ALUMINIUM_ORE.get()).add(ModBlocks.FERYL_STONE_ALUMINIUM_ORE.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ALUMINIUM_ORE.get(),
                ModBlocks.ALUMINIUM_BLOCK.get(),
                ModBlocks.RAW_ALUMINIUM_BLOCK.get(),
                ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get(),
                ModBlocks.RAW_TITANIUM_BLOCK.get(),
                ModBlocks.TITANIUM_BLOCK.get(),
                ModBlocks.FERYL_STONE.get(),
                ModBlocks.SOAP_STONE.get(),
                ModBlocks.SOAP_STONE_ALUMINIUM_ORE.get()
        );

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.RAW_TITANIUM_BLOCK.get()).add(ModBlocks.TITANIUM_BLOCK.get());

        this.tag(BlockTags.WALLS).add(ModBlocks.FERYL_WALL.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.ALUMINIUM_BLOCK.get()).add(ModBlocks.RAW_ALUMINIUM_BLOCK.get()).add(ModBlocks.ALUMINIUM_ORE.get()).add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get()).add(ModBlocks.SOAP_STONE_ALUMINIUM_ORE.get());
    }
}
