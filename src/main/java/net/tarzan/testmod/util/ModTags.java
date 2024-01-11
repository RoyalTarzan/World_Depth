package net.tarzan.testmod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.tarzan.testmod.TestMod1;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> ADDED_ORES=tag("added_ores");


        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(TestMod1.MODID, name));
        }
    }

    public static class Items{

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(TestMod1.MODID, name));
        }
    }
}
