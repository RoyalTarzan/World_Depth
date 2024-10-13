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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.tarzan.world_depth.item.ModItems;
import net.tarzan.world_depth.screen.EnergizerMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnergizerBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler=new ItemStackHandler(4);

    private static final int INPUT_SLOT_1=0;
    private static final int INPUT_SLOT_2=1;
    private static final int INPUT_SLOT_3=2;
    private static final int OUTPUT_SLOT= 3;

    private LazyOptional<IItemHandler> lazyItemHandler=LazyOptional.empty();

    protected final ContainerData data;
    private int progress=0;
    private int maxProgress=78;

    public EnergizerBlockEntity( BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ENERGIZER_BE.get(),pPos, pBlockState);
        this.data=new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0->EnergizerBlockEntity.this.progress;
                    case 1->EnergizerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0->EnergizerBlockEntity.this.progress=pValue;
                    case 1->EnergizerBlockEntity.this.maxProgress=pValue;
                };
            }

            @Override
            public int getCount() {
                return 2;
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
    public void onLoad() {
        super.onLoad();
        lazyItemHandler=LazyOptional.of(()->itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops(){
        SimpleContainer inventory= new SimpleContainer(itemHandler.getSlots());
        for (int i=0;i<itemHandler.getSlots();i++){
            inventory.setItem(i,itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level,this.worldPosition,inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.world_depth.energizer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory inventory, Player player) {
        return new EnergizerMenu(pContainerId, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory",itemHandler.serializeNBT());
        pTag.putInt("energizer.progress",progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress=pTag.getInt("energizer.progress");
    }

    public void tick(Level pLevel, BlockPos blockPos, BlockState pState) {
        if(hasRecipe()){
            increaseCraftingProgress();
            setChanged(pLevel, blockPos, pState);

            if (hasProgressFinished()){
                craftItem();
                resetProgress();
            }
        }else {
            resetProgress();
        }
    }

    private void craftItem() {
        ItemStack result=new ItemStack(ModItems.CHARGED_REDSTONE.get(),1);
        this.itemHandler.extractItem(INPUT_SLOT_1,1,false);
        this.itemHandler.extractItem(INPUT_SLOT_2,1,false);
        this.itemHandler.extractItem(INPUT_SLOT_3,1,false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount()+result.getCount()));
    }

    private void resetProgress() {
        progress=0;
    }

    private boolean hasProgressFinished() {
        return progress>=maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        boolean hasCraftingItem=this.itemHandler.getStackInSlot(INPUT_SLOT_1).getItem()== Items.REDSTONE && this.itemHandler.getStackInSlot(INPUT_SLOT_2).getItem()== Items.REDSTONE && this.itemHandler.getStackInSlot(INPUT_SLOT_3).getItem()== Items.REDSTONE;
        ItemStack result=new ItemStack(ModItems.CHARGED_REDSTONE.get());

        return hasCraftingItem && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()|| this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount()+count<=this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }


}
