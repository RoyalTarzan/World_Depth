package net.tarzan.world_depth.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALUMINIUM_ORE_KEY=registerKey("aluminium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COAL_ORE_KEY=registerKey("coal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> IRON_ORE_KEY=registerKey("iron_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLD_ORE_KEY=registerKey("gold_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COPPER_ORE_KEY=registerKey("copper_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DIAMOND_ORE_KEY=registerKey("diamond_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAPIS_ORE_KEY=registerKey("lapis_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> REDSTONE_ORE_KEY=registerKey("redstone_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EMERALD_ORE_KEY=registerKey("emerald_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TITANIUM_ORE_KEY=registerKey("titanium_ore");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>> context){
        RuleTest stone=new BlockMatchTest(Blocks.STONE);
        RuleTest deepslate=new BlockMatchTest(Blocks.DEEPSLATE);
        RuleTest feryl_stone=new BlockMatchTest(ModBlocks.FERYL_STONE.get());
        RuleTest soap_stone=new BlockMatchTest(ModBlocks.SOAP_STONE.get());
        RuleTest granite=new BlockMatchTest(Blocks.GRANITE);
        RuleTest diorite=new BlockMatchTest(Blocks.DIORITE);
        RuleTest andesite=new BlockMatchTest(Blocks.ANDESITE);
        RuleTest tuff=new BlockMatchTest(Blocks.TUFF);

        List<OreConfiguration.TargetBlockState> aluminiumOres=List.of(OreConfiguration.target(stone, ModBlocks.ALUMINIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslate, ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(soap_stone, ModBlocks.SOAP_STONE_ALUMINIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(feryl_stone, ModBlocks.FERYL_STONE_ALUMINIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(granite, ModBlocks.GRANITE_ALUMINIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(diorite, ModBlocks.DIORITE_ALUMINIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(tuff, ModBlocks.TUFF_ALUMINIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(andesite, ModBlocks.ANDESITE_ALUMINIUM_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> coalOres=List.of(OreConfiguration.target(granite, ModBlocks.GRANITE_COAL_ORE.get().defaultBlockState()),
                OreConfiguration.target(diorite, ModBlocks.DIORITE_COAL_ORE.get().defaultBlockState()),
                OreConfiguration.target(tuff, ModBlocks.TUFF_COAL_ORE.get().defaultBlockState()),
                OreConfiguration.target(andesite, ModBlocks.ANDESITE_COAL_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> ironOres=List.of(OreConfiguration.target(granite, ModBlocks.GRANITE_IRON_ORE.get().defaultBlockState()),
                OreConfiguration.target(diorite, ModBlocks.DIORITE_IRON_ORE.get().defaultBlockState()),
                OreConfiguration.target(tuff, ModBlocks.TUFF_IRON_ORE.get().defaultBlockState()),
                OreConfiguration.target(andesite, ModBlocks.ANDESITE_IRON_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> goldOres=List.of(OreConfiguration.target(granite, ModBlocks.GRANITE_GOLD_ORE.get().defaultBlockState()),
                OreConfiguration.target(diorite, ModBlocks.DIORITE_GOLD_ORE.get().defaultBlockState()),
                OreConfiguration.target(tuff, ModBlocks.TUFF_GOLD_ORE.get().defaultBlockState()),
                OreConfiguration.target(andesite, ModBlocks.ANDESITE_GOLD_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> copperOres=List.of(OreConfiguration.target(granite, ModBlocks.GRANITE_COPPER_ORE.get().defaultBlockState()),
                OreConfiguration.target(diorite, ModBlocks.DIORITE_COPPER_ORE.get().defaultBlockState()),
                OreConfiguration.target(tuff, ModBlocks.TUFF_COPPER_ORE.get().defaultBlockState()),
                OreConfiguration.target(andesite, ModBlocks.ANDESITE_COPPER_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> diamondOres=List.of(OreConfiguration.target(granite, ModBlocks.GRANITE_DIAMOND_ORE.get().defaultBlockState()),
                OreConfiguration.target(diorite, ModBlocks.DIORITE_DIAMOND_ORE.get().defaultBlockState()),
                OreConfiguration.target(tuff, ModBlocks.TUFF_DIAMOND_ORE.get().defaultBlockState()),
                OreConfiguration.target(andesite, ModBlocks.ANDESITE_DIAMOND_ORE.get().defaultBlockState()),
                OreConfiguration.target(soap_stone, ModBlocks.SOAP_STONE_DIAMOND_ORE.get().defaultBlockState()),
                OreConfiguration.target(feryl_stone, ModBlocks.FERYL_STONE_DIAMOND_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> lapisOres=List.of(OreConfiguration.target(granite, ModBlocks.GRANITE_LAPIS_ORE.get().defaultBlockState()),
                OreConfiguration.target(diorite, ModBlocks.DIORITE_LAPIS_ORE.get().defaultBlockState()),
                OreConfiguration.target(tuff, ModBlocks.TUFF_LAPIS_ORE.get().defaultBlockState()),
                OreConfiguration.target(andesite, ModBlocks.ANDESITE_LAPIS_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> redstoneOres=List.of(OreConfiguration.target(granite, ModBlocks.GRANITE_REDSTONE_ORE.get().defaultBlockState()),
                OreConfiguration.target(diorite, ModBlocks.DIORITE_REDSTONE_ORE.get().defaultBlockState()),
                OreConfiguration.target(tuff, ModBlocks.TUFF_REDSTONE_ORE.get().defaultBlockState()),
                OreConfiguration.target(andesite, ModBlocks.ANDESITE_REDSTONE_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> emeraldOres=List.of(OreConfiguration.target(granite, ModBlocks.GRANITE_EMERALD_ORE.get().defaultBlockState()),
                OreConfiguration.target(diorite, ModBlocks.DIORITE_EMERALD_ORE.get().defaultBlockState()),
                OreConfiguration.target(tuff, ModBlocks.TUFF_EMERALD_ORE.get().defaultBlockState()),
                OreConfiguration.target(andesite, ModBlocks.ANDESITE_EMERALD_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> titaniumOres=List.of(OreConfiguration.target(granite, ModBlocks.GRANITE_TITANIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(diorite, ModBlocks.DIORITE_TITANIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(tuff, ModBlocks.TUFF_TITANIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(andesite, ModBlocks.ANDESITE_TITANIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(soap_stone, ModBlocks.SOAP_STONE_TITANIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(feryl_stone, ModBlocks.FERYL_STONE_TITANIUM_ORE.get().defaultBlockState()));

        register(context, ALUMINIUM_ORE_KEY, Feature.ORE, new OreConfiguration(aluminiumOres, 15));
        register(context, COAL_ORE_KEY, Feature.ORE, new OreConfiguration(coalOres, 10));
        register(context, IRON_ORE_KEY, Feature.ORE, new OreConfiguration(ironOres, 8));
        register(context, GOLD_ORE_KEY, Feature.ORE, new OreConfiguration(goldOres, 6));
        register(context, COPPER_ORE_KEY, Feature.ORE, new OreConfiguration(copperOres, 10));
        register(context, DIAMOND_ORE_KEY, Feature.ORE, new OreConfiguration(diamondOres, 8));
        register(context, LAPIS_ORE_KEY, Feature.ORE, new OreConfiguration(lapisOres, 8));
        register(context, EMERALD_ORE_KEY, Feature.ORE, new OreConfiguration(emeraldOres, 2));
        register(context, REDSTONE_ORE_KEY, Feature.ORE, new OreConfiguration(redstoneOres, 8));
        register(context, TITANIUM_ORE_KEY, Feature.ORE, new OreConfiguration(titaniumOres, 8));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(World_Depth.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
