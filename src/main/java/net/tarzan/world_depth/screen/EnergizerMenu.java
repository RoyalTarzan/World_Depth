package net.tarzan.world_depth.screen;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.tarzan.world_depth.block.ModBlocks;
import net.tarzan.world_depth.block.entity.EnergizerBlockEntity;
import net.tarzan.world_depth.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static net.tarzan.world_depth.block.entity.EnergizerBlockEntity.*;

public class EnergizerMenu extends AbstractContainerMenu {
    public final EnergizerBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public EnergizerMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData){
        this(pContainerId,inv,inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(9));
    }

    public EnergizerMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data){
        super(ModMenuTypes.ENERGIZER_MENU.get(),pContainerId);
        checkContainerSize(inv,2);
        blockEntity= ((EnergizerBlockEntity) entity);
        this.level=inv.player.level();
        this.data=data;

        addPlayerHotbar(inv);
        addPlayerInventory(inv);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler,1,58,11));
            this.addSlot(new SlotItemHandler(iItemHandler,2,80,11));
            this.addSlot(new SlotItemHandler(iItemHandler,3,102,11));
            this.addSlot(new SlotItemHandler(iItemHandler,0,36,11));
            this.addSlot(new SlotItemHandler(iItemHandler,4,124,11));
            this.addSlot(new SlotItemHandler(iItemHandler,5,80,59));
            this.addSlot(new SlotItemHandler(iItemHandler,6,29,59));
            this.addSlot(new SlotItemHandler(iItemHandler,7,124,59));
            this.addSlot(new SlotItemHandler(iItemHandler,8,7,7));
        });

        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 26;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getScaledRedstone(){
        int redstoneAmount= this.data.get(2);
        int maxRedstone= this.data.get(3);
        int redstoneProgressBarSize=50;

        return maxRedstone != 0 && redstoneAmount !=0 ? redstoneAmount * redstoneProgressBarSize / maxRedstone : 0;
    }

    public int getScaledChargedRedstone(){
        int chargedRedstoneAmount= this.data.get(4);
        int maxChargedRedstone= this.data.get(5);
        int chargedRedstoneProgressBarSize=50;

        return maxChargedRedstone != 0 && chargedRedstoneAmount !=0 ? chargedRedstoneAmount * chargedRedstoneProgressBarSize / maxChargedRedstone : 0;

    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    private static final int TE_INVENTORY_SLOT_COUNT = 9;  // must be the number of slots you have!
    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (sourceStack.is(Items.REDSTONE)||sourceStack.is(Items.REDSTONE_BLOCK)){
                if (!moveItemStackTo(sourceStack, VANILLA_SLOT_COUNT+REDSTONE_SLOT,VANILLA_SLOT_COUNT+7, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (sourceStack.is(ModItems.CHARGED_REDSTONE.get())||sourceStack.is(ModBlocks.CHARGED_REDSTONE_BLOCK.get().asItem())) {
                if (!moveItemStackTo(sourceStack, VANILLA_SLOT_COUNT+CHARGED_REDSTONE_SLOT,VANILLA_SLOT_COUNT+8, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (Arrays.asList(blockEntity.lockedIngredients).contains(sourceStack.getItem())) {
                if (!moveItemStackTo(sourceStack,TE_INVENTORY_FIRST_SLOT_INDEX+ Arrays.asList(blockEntity.lockedIngredients).indexOf(sourceStack.getItem())+EnergizerBlockEntity.INPUT_SLOT_1,TE_INVENTORY_FIRST_SLOT_INDEX+TE_INVENTORY_SLOT_COUNT,false)){
                    return ItemStack.EMPTY;
                }
            } else if (sourceStack.is(Items.NETHER_STAR)) {
                if (!moveItemStackTo(sourceStack, VANILLA_SLOT_COUNT+LOCK_RECIPE_SLOT,VANILLA_SLOT_COUNT+8, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX + 5, false)) {
                    return ItemStack.EMPTY;
                }
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModBlocks.ENERGIZER.get());
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
