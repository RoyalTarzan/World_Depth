package net.tarzan.world_depth.block.entity;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ItemHandlerWrapper implements IItemHandlerModifiable {
    private final ItemStackHandler itemHandler;
    private final boolean allowInsert;
    private final boolean allowExtract;
    private final int targetSlot;
    private final ArrayList<Item> allowedItems;

    ItemHandlerWrapper(ItemStackHandler itemHandler, boolean allowInsert, boolean allowExtract, int targetSlotIndex, ArrayList<Item> allowedItems){
        this.itemHandler=itemHandler;
        this.allowInsert=allowInsert;
        this.allowExtract=allowExtract;
        this.targetSlot= targetSlotIndex;
        this.allowedItems=allowedItems;
    }

    @Override
    public void setStackInSlot(int slot, @NotNull ItemStack stack) {

    }

    @Override
    public int getSlots() {
        return itemHandler.getSlots();
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        return itemHandler.getStackInSlot(slot);
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if (allowInsert&&slot==targetSlot){
            return itemHandler.insertItem(slot, stack, simulate);
        }
        return stack;
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (allowExtract&&slot==targetSlot){
            return itemHandler.extractItem(slot,amount,simulate);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int slot) {
        return itemHandler.getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        if (allowedItems.contains(stack.getItem())){
            return true;
        }
        return itemHandler.isItemValid(slot, stack);
    }
}
