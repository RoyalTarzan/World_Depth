package net.tarzan.world_depth.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.tarzan.world_depth.World_Depth;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> ADDED_ORES=tag("added_ores");


        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(World_Depth.MODID, name));
        }
    }

    public static class Items{

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(World_Depth.MODID, name));
        }
    }
}
