package net.tarzan.world_depth.worldgen.tree.custom;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.tarzan.world_depth.worldgen.tree.ModTrunkPlacerTypes;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class StookTrunkPlacer extends TrunkPlacer {
    public static final Codec<StookTrunkPlacer> CODEC= RecordCodecBuilder.create(stookTrunkPlacerInstance ->
            trunkPlacerParts(stookTrunkPlacerInstance).apply(stookTrunkPlacerInstance, StookTrunkPlacer::new));

    public StookTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.STOOK_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int height, BlockPos blockPos, TreeConfiguration treeConfiguration) {

        setDirtAt(pLevel,blockSetter,random,blockPos.below(),treeConfiguration);
        int treeHeight=height+random.nextInt(heightRandA,heightRandA+heightRandB);
        List<FoliagePlacer.FoliageAttachment> foliageAttachments = Lists.newArrayList();

        for (int i = 0; i < treeHeight; i++) {
            placeLog(pLevel, blockSetter, random, blockPos.above(i),treeConfiguration);
            if (i>4){
                if (i%5==0){
                    for (int j = 1; j < 5; j++) {
                        blockSetter.accept(blockPos.above(i).relative(Direction.NORTH, j), ((BlockState)
                                Function.identity().apply(treeConfiguration.trunkProvider.getState(random, blockPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
                        blockSetter.accept(blockPos.above(i).relative(Direction.SOUTH, j), ((BlockState)
                                Function.identity().apply(treeConfiguration.trunkProvider.getState(random, blockPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
                        blockSetter.accept(blockPos.above(i).relative(Direction.EAST, j), ((BlockState)
                                Function.identity().apply(treeConfiguration.trunkProvider.getState(random, blockPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
                        blockSetter.accept(blockPos.above(i).relative(Direction.WEST, j), ((BlockState)
                                Function.identity().apply(treeConfiguration.trunkProvider.getState(random, blockPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
                    }
                    foliageAttachments.add(new FoliagePlacer.FoliageAttachment(blockPos.above(i).relative(Direction.NORTH,5),0,false));
                    foliageAttachments.add(new FoliagePlacer.FoliageAttachment(blockPos.above(i).relative(Direction.SOUTH,5),0,false));
                    foliageAttachments.add(new FoliagePlacer.FoliageAttachment(blockPos.above(i).relative(Direction.EAST,5),0,false));
                    foliageAttachments.add(new FoliagePlacer.FoliageAttachment(blockPos.above(i).relative(Direction.WEST,5),0,false));

                }
            }
        }
        foliageAttachments.add(new FoliagePlacer.FoliageAttachment(blockPos.above(treeHeight),0,false));

        return foliageAttachments;
    }
}
