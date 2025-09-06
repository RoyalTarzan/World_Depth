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
import net.minecraft.world.inventory.ContainerData;
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
    private final ItemStackHandler itemHandler=new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static final int INPUT_SLOT=0;

    private LazyOptional<IItemHandler> lazyItemHandler=LazyOptional.empty();

    protected final ContainerData data;
    private int previousAmount=0;
    private int currentAmount=0;
    private int wait;
    private final int wait_time=5; //time to wait between checks and updates in seconds

    public DeepLightBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.DEEP_LIGHT_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i){
                    case 0 ->DeepLightBlockEntity.this.currentAmount;
                    case 1 ->DeepLightBlockEntity.this.previousAmount;
                    case 2 ->DeepLightBlockEntity.this.wait;
                    default -> 1;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i){
                    case 0 ->DeepLightBlockEntity.this.currentAmount=value;
                    case 1 ->DeepLightBlockEntity.this.previousAmount=value;
                    case 2 ->DeepLightBlockEntity.this.wait=value;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
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
        this.data.set(0,this.itemHandler.getStackInSlot(INPUT_SLOT).getCount());
        if (this.data.get(0)>=this.data.get(1) && this.data.get(2)>wait_time*20){
            if (this.itemHandler.getStackInSlot(INPUT_SLOT).getItem()== ModItems.LIGHT_GEM.get() && !pLevel.isClientSide()){
                matrixPlacement(this.data.get(0)*2,pLevel,blockPos,
                        Blocks.AIR.defaultBlockState(),Blocks.LIGHT.defaultBlockState());
            }else {
                matrixPlacement(128,pLevel,blockPos,
                        Blocks.LIGHT.defaultBlockState(),Blocks.AIR.defaultBlockState());
            }
            this.data.set(2,0);
        } else if (this.data.get(0)<this.data.get(1)) {
            matrixPlacement(128,pLevel,blockPos,
                    Blocks.LIGHT.defaultBlockState(),Blocks.AIR.defaultBlockState());
            matrixPlacement(this.data.get(0)*2,pLevel,blockPos,
                    Blocks.AIR.defaultBlockState(),Blocks.LIGHT.defaultBlockState());
        } else { this.data.set(2,this.data.get(2)+1);}
        this.data.set(1,this.itemHandler.getStackInSlot(INPUT_SLOT).getCount());
    }

    public void matrixPlacement(int diameter,Level pLevel,BlockPos blockPos,BlockState Original,BlockState Replaced){
        for (int i = -diameter+1; i < diameter+1; i+=2) {
            for (int j = -diameter+1; j < diameter+1; j+=2) {
                for (int k = -diameter+1; k < diameter+1; k+=2) {
                    BlockPos blockPos2=new BlockPos(blockPos.getX()+i,blockPos.getY()+k,blockPos.getZ()+j);
                    if (pLevel.getBlockState(blockPos2)==Original){
                        pLevel.setBlockAndUpdate(blockPos2,Replaced);
                    }

                }
            }
        }
    }
}
