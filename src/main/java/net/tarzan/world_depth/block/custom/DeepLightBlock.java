package net.tarzan.world_depth.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.tarzan.world_depth.block.entity.DeepLightBlockEntity;
import net.tarzan.world_depth.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class DeepLightBlock extends BaseEntityBlock {

    public DeepLightBlock(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new DeepLightBlockEntity(blockPos,blockState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving){
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof DeepLightBlockEntity) {
                ((DeepLightBlockEntity) blockEntity).drops();
                if (!pLevel.isClientSide()){
                    for (int i = -127; i < 129; i+=2) {
                        for (int j = -127; j < 129; j+=2) {
                            BlockPos blockPos2=new BlockPos(pPos.getX()+i,pPos.getY(),pPos.getZ()+j);
                            if (pLevel.getBlockState(blockPos2)==Blocks.LIGHT.defaultBlockState()){
                                pLevel.setBlockAndUpdate(blockPos2, Blocks.AIR.defaultBlockState());
                            }
                        }
                    }
                }
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof DeepLightBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer)pPlayer), (DeepLightBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide()){
            return null;
        }

        return createTickerHelper(pBlockEntityType, ModBlockEntities.DEEP_LIGHT_BE.get(),
                (pLevel1, blockPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1,blockPos));
    }
}
