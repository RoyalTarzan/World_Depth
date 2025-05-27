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
import org.jetbrains.annotations.NotNull;

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
    protected @NotNull TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.STOOK_TRUNK_PLACER.get();
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader pLevel, @NotNull BiConsumer<BlockPos, BlockState> blockSetter, @NotNull RandomSource random, int height, BlockPos blockPos, @NotNull TreeConfiguration treeConfiguration) {

        setDirtAt(pLevel,blockSetter,random,blockPos.below(),treeConfiguration);
        int treeHeight=height+random.nextInt(heightRandA,heightRandA+heightRandB);
        List<FoliagePlacer.FoliageAttachment> foliageAttachments = Lists.newArrayList();

        for (int currentHeight = 0; currentHeight < treeHeight; currentHeight++) {
            placeLog(pLevel, blockSetter, random, blockPos.above(currentHeight),treeConfiguration);
            if (currentHeight>4){
                if (currentHeight%5==0){
                    foliageAttachments.addAll(branch(currentHeight,blockPos,blockSetter,random,treeConfiguration, treeHeight, random.nextInt(5,10),
                            switch (random.nextInt(1,4)){
                        case 1 -> Direction.SOUTH;
                        case 2 -> Direction.NORTH;
                        case 3 -> Direction.EAST;
                        case 4 -> Direction.WEST;
                        default -> throw new IllegalStateException("Unexpected value");
                    }));
                }
            }
        }
        foliageAttachments.add(new FoliagePlacer.FoliageAttachment(blockPos.above(treeHeight),0,false));

        return foliageAttachments;
    }

    private static List<FoliagePlacer.FoliageAttachment> branch(int currentHeight, BlockPos startpos, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, TreeConfiguration treeConfiguration, int treeHeight, int length,Direction direction){
        List<FoliagePlacer.FoliageAttachment> foliageAttachments = Lists.newArrayList();
        int north = 0,east=0;
        for (int j = 0; j < length; j++) {
            switch (direction){
                case SOUTH,NORTH ->
                {switch (random.nextInt(1,5)){
                    case 1-> {north=north(direction,north);east=east(Direction.WEST,east);
                        blockSetter.accept(startpos.above(currentHeight).relative(Direction.NORTH,north).relative(Direction.EAST,east), ((BlockState)
                                Function.identity().apply(treeConfiguration.trunkProvider.getState(random, startpos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
                    }
                    case 2-> {north=north(direction,north);east=east(Direction.EAST,east);
                        blockSetter.accept(startpos.above(currentHeight).relative(Direction.NORTH,north).relative(Direction.EAST,east), ((BlockState)
                                Function.identity().apply(treeConfiguration.trunkProvider.getState(random, startpos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
                    }
                    case 3-> {north=north(direction,north);
                        blockSetter.accept(startpos.above(currentHeight).relative(Direction.NORTH,north).relative(Direction.EAST,east), ((BlockState)
                                Function.identity().apply(treeConfiguration.trunkProvider.getState(random, startpos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
                    }
                    case 4-> {if (currentHeight<treeHeight){currentHeight++;}
                        north=north(direction,north);
                        blockSetter.accept(startpos.above(currentHeight).relative(Direction.NORTH,north).relative(Direction.EAST,east), ((BlockState)
                                Function.identity().apply(treeConfiguration.trunkProvider.getState(random, startpos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
                    }
                    }
                }

                case EAST,WEST ->
                {switch (random.nextInt(1,5)){
                    case 1-> {north=north(Direction.NORTH,north);east=east(direction,east);
                        blockSetter.accept(startpos.above(currentHeight).relative(Direction.NORTH,north).relative(Direction.EAST,east), ((BlockState)
                                Function.identity().apply(treeConfiguration.trunkProvider.getState(random, startpos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
                    }
                    case 2-> {north=north(Direction.SOUTH,north);east=east(direction,east);
                        blockSetter.accept(startpos.above(currentHeight).relative(Direction.NORTH,north).relative(Direction.EAST,east), ((BlockState)
                                Function.identity().apply(treeConfiguration.trunkProvider.getState(random, startpos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
                    }
                    case 3-> {east=east(direction,east);
                        blockSetter.accept(startpos.above(currentHeight).relative(Direction.NORTH,north).relative(Direction.EAST,east), ((BlockState)
                                Function.identity().apply(treeConfiguration.trunkProvider.getState(random, startpos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
                    }
                    case 4-> {if (currentHeight<treeHeight){currentHeight++;}
                        east=east(direction,east);
                        blockSetter.accept(startpos.above(currentHeight).relative(Direction.NORTH,north).relative(Direction.EAST,east), ((BlockState)
                                Function.identity().apply(treeConfiguration.trunkProvider.getState(random, startpos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
                    }
                }
                }
            }
            if (j%3==0 && j>3){
                foliageAttachments.addAll(branch(currentHeight,
                        startpos.relative(Direction.NORTH,north).relative(Direction.EAST,east), blockSetter, random, treeConfiguration, treeHeight, random.nextInt(0,length),
                        switch (direction){
                            case DOWN,UP -> null;
                            case NORTH,SOUTH -> random.nextBoolean() ? Direction.EAST:Direction.WEST;
                            case WEST,EAST -> random.nextBoolean() ? Direction.NORTH:Direction.SOUTH;
                        }));
            }
            if (j==length-1){
                foliageAttachments.add(new FoliagePlacer.FoliageAttachment(startpos.above(currentHeight).relative(Direction.NORTH,north).relative(Direction.EAST,east), 0,false));
            }
        }
        return foliageAttachments;
    }

    private static int north(Direction direction,int north){
        switch (direction){
            case NORTH -> north++;
            case SOUTH -> north--;
        }
        return north;
    }

    private static int east(Direction direction,int east){
        switch (direction){
            case EAST -> east++;
            case WEST -> east--;
        }
        return east;
    }
}
