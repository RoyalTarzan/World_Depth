package net.tarzan.world_depth.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier ALUMINIUM= TierSortingRegistry.registerTier(
            new ForgeTier(2,300,9f,2f,16,
                    ModTags.Blocks.NEEDS_ALUMINIUM_TOOL,()-> Ingredient.of(ModItems.ALUMINIUM.get())),
                    new ResourceLocation(World_Depth.MODID,"aluminium"), List.of(Tiers.STONE),List.of(Tiers.DIAMOND)
    );

    public static final Tier TITANIUM= TierSortingRegistry.registerTier(
            new ForgeTier(5,2500,15f,5f,6,
                    ModTags.Blocks.NEEDS_TITANIUM_TOOL,()-> Ingredient.of(ModItems.TITANIUM.get())),
            new ResourceLocation(World_Depth.MODID,"titanium"), List.of(Tiers.NETHERITE),List.of()
    );
}
