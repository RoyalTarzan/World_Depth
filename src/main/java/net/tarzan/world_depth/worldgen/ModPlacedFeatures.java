package net.tarzan.world_depth.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.tarzan.world_depth.World_Depth;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.data.worldgen.BootstapContext;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ALUMINIUM_ORE_PLACED_KEY=registerKey("aluminium_ore_placed");
    public static final ResourceKey<PlacedFeature> COAL_ORE_PLACED_KEY=registerKey("coal_ore_placed");
    public static final ResourceKey<PlacedFeature> IRON_ORE_PLACED_KEY=registerKey("iron_ore_placed");
    public static final ResourceKey<PlacedFeature> GOLD_ORE_PLACED_KEY=registerKey("gold_ore_placed");
    public static final ResourceKey<PlacedFeature> COPPER_ORE_PLACED_KEY=registerKey("copper_ore_placed");
    public static final ResourceKey<PlacedFeature> DIAMOND_ORE_PLACED_KEY=registerKey("diamond_ore_placed");
    public static final ResourceKey<PlacedFeature> LAPIS_ORE_PLACED_KEY=registerKey("lapis_ore_placed");
    public static final ResourceKey<PlacedFeature> REDSTONE_ORE_PLACED_KEY=registerKey("redstone_ore_placed");
    public static final ResourceKey<PlacedFeature> EMERALD_ORE_PLACED_KEY=registerKey("emerald_ore_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context){
        HolderGetter<ConfiguredFeature<?,?>> configuredFeatures=context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ALUMINIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALUMINIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-128), VerticalAnchor.absolute(100))));
        register(context, COAL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.COAL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(30, HeightRangePlacement.uniform(VerticalAnchor.absolute(-128), VerticalAnchor.absolute(100))));
        register(context, IRON_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.IRON_ORE_KEY),
                ModOrePlacement.commonOrePlacement(20, HeightRangePlacement.uniform(VerticalAnchor.absolute(-128), VerticalAnchor.absolute(70))));
        register(context, GOLD_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.GOLD_ORE_KEY),
                ModOrePlacement.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-128), VerticalAnchor.absolute(70))));
        register(context, COPPER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.COPPER_ORE_KEY),
                ModOrePlacement.commonOrePlacement(30, HeightRangePlacement.uniform(VerticalAnchor.absolute(-128), VerticalAnchor.absolute(70))));
        register(context, DIAMOND_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DIAMOND_ORE_KEY),
                ModOrePlacement.commonOrePlacement(30, HeightRangePlacement.uniform(VerticalAnchor.absolute(-128), VerticalAnchor.absolute(70))));
        register(context, LAPIS_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LAPIS_ORE_KEY),
                ModOrePlacement.commonOrePlacement(30, HeightRangePlacement.uniform(VerticalAnchor.absolute(-128), VerticalAnchor.absolute(70))));
        register(context, REDSTONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.REDSTONE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(30, HeightRangePlacement.uniform(VerticalAnchor.absolute(-128), VerticalAnchor.absolute(70))));
        register(context, EMERALD_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.EMERALD_ORE_KEY),
                ModOrePlacement.rareOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-128), VerticalAnchor.absolute(70))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(World_Depth.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
