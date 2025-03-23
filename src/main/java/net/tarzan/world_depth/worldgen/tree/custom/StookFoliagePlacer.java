package net.tarzan.world_depth.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.tarzan.world_depth.worldgen.tree.ModFoliagePlacerTypes;

public class StookFoliagePlacer extends FoliagePlacer {
    public static final Codec<StookFoliagePlacer> CODEC= RecordCodecBuilder.create(stookFoliagePlacerInstance ->
            foliagePlacerParts(stookFoliagePlacerInstance).and(Codec.intRange(0,16).fieldOf("height")
                    .forGetter(fp -> fp.height)).apply(stookFoliagePlacerInstance, StookFoliagePlacer::new));


    private final int height;

    public StookFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {
        super(pRadius, pOffset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.STOOK_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource randomSource, TreeConfiguration treeConfiguration, int i, FoliageAttachment foliageAttachment, int i1, int i2, int i3) {

    }

    @Override
    public int foliageHeight(RandomSource randomSource, int i, TreeConfiguration treeConfiguration) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource randomSource, int i, int i1, int i2, int i3, boolean b) {
        return false;
    }
}
