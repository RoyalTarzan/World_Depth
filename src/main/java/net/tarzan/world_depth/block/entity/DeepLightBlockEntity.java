package net.tarzan.world_depth.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.tarzan.world_depth.item.ModItems;
import net.tarzan.world_depth.screen.DeepLightMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DeepLightBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler=new ItemStackHandler(1);

    private static final int INPUT_SLOT=0;

    private LazyOptional<IItemHandler> lazyItemHandler=LazyOptional.empty();


    public DeepLightBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.DEEP_LIGHT_BE.get(), pPos, pBlockState);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap== ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.world_depth.deep_light");
    }

    public void drops(){
        SimpleContainer inventory= new SimpleContainer(itemHandler.getSlots());
        for (int i=0;i<itemHandler.getSlots();i++){
            inventory.setItem(i,itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level,this.worldPosition,inventory);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler=LazyOptional.of(()->itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory inventory, Player player) {
        return new DeepLightMenu(pContainerId, inventory,this, null);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory",itemHandler.serializeNBT());

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
    }
    public void tick(Level pLevel, BlockPos blockPos) {
        if (this.itemHandler.getStackInSlot(INPUT_SLOT).getItem()== ModItems.LIGHT_GEM.get() && !pLevel.isClientSide()){
            for (int i = this.itemHandler.getStackInSlot(INPUT_SLOT).getCount()*-2; i < this.itemHandler.getStackInSlot(INPUT_SLOT).getCount()*2; i+=2) {
                for (int j = this.itemHandler.getStackInSlot(INPUT_SLOT).getCount()*-2; j < this.itemHandler.getStackInSlot(INPUT_SLOT).getCount()*2; j+=2) {
                    for (int k = this.itemHandler.getStackInSlot(INPUT_SLOT).getCount()*-2; k < this.itemHandler.getStackInSlot(INPUT_SLOT).getCount()*2; k+=2) {
                        BlockPos blockPos2=new BlockPos(blockPos.getX()+i,blockPos.getY()+k,blockPos.getZ()+j);
                        if (pLevel.getBlockState(blockPos2)==Blocks.AIR.defaultBlockState()){
                            pLevel.setBlockAndUpdate(blockPos2,Blocks.LIGHT.defaultBlockState());
                        }
                    }
                }
            }
        }else {
            for (int i = -128; i < 128; i+=2) {
                for (int j = -128; j < 128; j+=2) {
                    for (int k = -128; k < 128; k+=2) {
                        BlockPos blockPos2=new BlockPos(blockPos.getX()+i,blockPos.getY()+k,blockPos.getZ()+j);
                        if (pLevel.getBlockState(blockPos2)==Blocks.LIGHT.defaultBlockState()){
                            pLevel.setBlockAndUpdate(blockPos2,Blocks.AIR.defaultBlockState());
                        }
                    }
                }
            }
        }
    }
}
