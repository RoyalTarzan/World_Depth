package net.tarzan.world_depth.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.tarzan.world_depth.World_Depth;

public class ModBiomeTags{
    public static final TagKey<Biome> IS_DEEP_PLAINS = create("is_deep_plains");

    private static TagKey<Biome> create(String pName) {
        return TagKey.create(Registries.BIOME, new ResourceLocation(World_Depth.MODID,pName));
    }
}
