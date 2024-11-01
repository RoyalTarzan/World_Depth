package net.tarzan.world_depth.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_,@Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, World_Depth.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.ALUMINIUM_HELMET.get(),
                ModItems.ALUMINIUM_CHESTPLATE.get(),
                        ModItems.ALUMINIUM_LEGGINGS.get(),
                        ModItems.ALUMINIUM_BOOTS.get(),
                        ModItems.TITANIUM_HELMET.get(),
                        ModItems.TITANIUM_CHESTPLATE.get(),
                        ModItems.TITANIUM_LEGGINGS.get(),
                        ModItems.TITANIUM_BOOTS.get(),
                        ModItems.ENERGIZED_ALUMINIUM_HELMET.get(),
                        ModItems.ENERGIZED_ALUMINIUM_CHESTPLATE.get(),
                        ModItems.ENERGIZED_ALUMINIUM_LEGGINGS.get(),
                        ModItems.ENERGIZED_ALUMINIUM_BOOTS.get(),
                        ModItems.ENERGIZED_TITANIUM_HELMET.get(),
                        ModItems.ENERGIZED_TITANIUM_CHESTPLATE.get(),
                        ModItems.ENERGIZED_TITANIUM_LEGGINGS.get(),
                        ModItems.ENERGIZED_TITANIUM_BOOTS.get(),
                        ModItems.TALIUM_HELMET.get(),
                        ModItems.TALIUM_CHESTPLATE.get(),
                        ModItems.TALIUM_LEGGINGS.get(),
                        ModItems.TALIUM_BOOTS.get());
    }
}
