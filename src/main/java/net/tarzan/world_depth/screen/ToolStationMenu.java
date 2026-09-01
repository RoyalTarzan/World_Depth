package net.tarzan.world_depth.screen;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.tarzan.world_depth.block.ModBlocks;
import net.tarzan.world_depth.block.entity.ToolStationBlockEntity;
import net.tarzan.world_depth.item.ModItems;
import org.jetbrains.annotations.NotNull;

public class ToolStationMenu extends AbstractContainerMenu {
    private final ToolStationBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public ToolStationMenu(int containerData, Inventory inv, FriendlyByteBuf extraData){
        this(containerData,inv, (ToolStationBlockEntity) inv.player.level().getBlockEntity(extraData.readBlockPos()),new SimpleContainerData(5));
    }

    public ToolStationMenu(int pContainerId, Inventory inv, ToolStationBlockEntity blockEntity,ContainerData data) {
        super(ModMenuTypes.TOOL_STATION_MENU.get(), pContainerId);
        checkContainerSize(inv,2);
        this.blockEntity=blockEntity;
        this.level=inv.player.level();
        this.data=data;

        addPlayerHotbar(inv);
        addPlayerInventory(inv);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler,0,58,11));
            this.addSlot(new SlotItemHandler(iItemHandler,1,102,11));
            this.addSlot(new SlotItemHandler(iItemHandler,2,124,59));
            this.addSlot(new SlotItemHandler(iItemHandler,3,80,59));
            this.addSlot(new SlotItemHandler(iItemHandler,4,29,59));
        });

        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(2);
        int progressArrowSize = 26;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getScaledChargedRedstone(){
        int chargedRedstoneAmount= this.data.get(1);
        int maxChargedRedstone= 1000;
        int chargedRedstoneProgressBarSize=50;

        return chargedRedstoneAmount != 0 ? chargedRedstoneAmount * chargedRedstoneProgressBarSize / maxChargedRedstone : 0;

    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    private static final int TE_INVENTORY_SLOT_COUNT = 5;
    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();


        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (sourceStack.is(Items.STICK)){
                if (!moveItemStackTo(sourceStack, 1+VANILLA_SLOT_COUNT, 2+VANILLA_SLOT_COUNT, false)) {
                    return ItemStack.EMPTY;
                }
            }else if (sourceStack.is(ModItems.CHARGED_REDSTONE.get())||sourceStack.is(ModBlocks.CHARGED_REDSTONE_BLOCK.get().asItem())) {
                if (!moveItemStackTo(sourceStack, 2+VANILLA_SLOT_COUNT, 3+VANILLA_SLOT_COUNT, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (sourceStack.is(ModItems.CUSTOM_MATERIAL.get())) {
                if (!moveItemStackTo(sourceStack, VANILLA_SLOT_COUNT, 1+VANILLA_SLOT_COUNT, false)) {
                    return ItemStack.EMPTY;
                }
            }else {
                return ItemStack.EMPTY;
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }
        if (sourceStack.isEmpty()) {
            sourceSlot.setByPlayer(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModBlocks.TOOL_STATION.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
