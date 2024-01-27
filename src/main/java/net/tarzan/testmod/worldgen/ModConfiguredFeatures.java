package net.tarzan.testmod.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.fml.common.Mod;
import net.tarzan.testmod.TestMod1;
import net.tarzan.testmod.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALUMINIUM_ORE_KEY=registerKey("aluminium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COAL_ORE_KEY=registerKey("aluminium_ore");
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
                OreConfiguration.target(feryl_stone, ModBlocks.FERYL_STONE_ALUMINIUM_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> coalOres=List.of(OreConfiguration.target(granite, ModBlocks.GRANITE_COAL_ORE.get().defaultBlockState()),
                OreConfiguration.target(diorite, ModBlocks.DIORITE_COAL_ORE.get().defaultBlockState()),
                OreConfiguration.target(tuff, ModBlocks.TUFF_COAL_ORE.get().defaultBlockState()),
                OreConfiguration.target(andesite, ModBlocks.ANDESITE_COAL_ORE.get().defaultBlockState()));

        register(context, ALUMINIUM_ORE_KEY, Feature.ORE, new OreConfiguration(aluminiumOres, 15));
        register(context, COAL_ORE_KEY, Feature.ORE, new OreConfiguration(coalOres, 10));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(TestMod1.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
