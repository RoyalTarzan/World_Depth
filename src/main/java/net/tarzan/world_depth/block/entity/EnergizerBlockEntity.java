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
import net.tarzan.world_depth.block.ModBlocks;
import net.tarzan.world_depth.item.ModItems;
import net.tarzan.world_depth.recipe.EnergizerRecipe;
import net.tarzan.world_depth.screen.EnergizerMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class EnergizerBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler=new ItemStackHandler(8) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static final int INPUT_SLOT_1=0;
    private static final int INPUT_SLOT_2=1;
    private static final int INPUT_SLOT_3=2;
    private static final int INPUT_SLOT_4=3;
    private static final int INPUT_SLOT_5=4;
    private static final int OUTPUT_SLOT= 5;
    private static final int REDSTONE_SLOT=6;
    private static final int CHARGED_REDSTONE_SLOT=7;

    private LazyOptional<IItemHandler> lazyItemHandler=LazyOptional.empty();

    protected final ContainerData data;
    private int progress=0;
    private int maxProgress=78;
    private int redstoneAmount=0;
    private int maxRedstone=500;
    private int chargedRedstoneAmount=0;
    private int maxChargedRedstone=500;
    private final int redstoneAndChargedRedstone=125;

    public EnergizerBlockEntity( BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ENERGIZER_BE.get(),pPos, pBlockState);
        this.data=new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0->EnergizerBlockEntity.this.progress;
                    case 1->EnergizerBlockEntity.this.maxProgress;
                    case 2->EnergizerBlockEntity.this.redstoneAmount;
                    case 3->EnergizerBlockEntity.this.maxRedstone;
                    case 4->EnergizerBlockEntity.this.chargedRedstoneAmount;
                    case 5->EnergizerBlockEntity.this.maxChargedRedstone;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0->EnergizerBlockEntity.this.progress=pValue;
                    case 1->EnergizerBlockEntity.this.maxProgress=pValue;
                    case 2->EnergizerBlockEntity.this.redstoneAmount=pValue;
                    case 3->EnergizerBlockEntity.this.maxRedstone=pValue;
                    case 4->EnergizerBlockEntity.this.chargedRedstoneAmount=pValue;
                    case 5->EnergizerBlockEntity.this.maxChargedRedstone=pValue;
                }
            }

            @Override
            public int getCount() {
                return 6;
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

        assert this.level != null;
        Containers.dropContents(this.level,this.worldPosition,inventory);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.world_depth.energizer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory inventory, @NotNull Player player) {
        return new EnergizerMenu(pContainerId, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory",itemHandler.serializeNBT());
        pTag.putInt("energizer.progress",progress);
        pTag.putInt("energizer.redstone_amount",redstoneAmount);
        pTag.putInt("energizer.charged_redstone_amount",chargedRedstoneAmount);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress=pTag.getInt("energizer.progress");
        redstoneAmount=pTag.getInt("energizer.redstone_amount");
        chargedRedstoneAmount=pTag.getInt("energizer.charged_redstone_amount");
    }

    public void tick(Level pLevel, BlockPos blockPos, BlockState pState) {
        redstoneIncrease();
        chargedRedstoneIncrease();
        if(hasRecipe()||containsCustomMaterialsIngredients()){
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

    private void redstoneIncrease() {
        if((this.itemHandler.getStackInSlot(REDSTONE_SLOT).getItem()== Items.REDSTONE) && redstoneAmount<=(maxRedstone-10)) {
            this.data.set(2,redstoneAmount+10);
            this.itemHandler.extractItem(REDSTONE_SLOT, 1, false);
        } else if ((this.itemHandler.getStackInSlot(REDSTONE_SLOT).getItem()==Items.REDSTONE_BLOCK) && redstoneAmount<=(maxRedstone-99)) {
            this.data.set(2,redstoneAmount+99);
            this.itemHandler.extractItem(REDSTONE_SLOT,1,false);
        }
    }

    private void chargedRedstoneIncrease(){
        if((this.itemHandler.getStackInSlot(CHARGED_REDSTONE_SLOT).getItem()== ModItems.CHARGED_REDSTONE.get()) && chargedRedstoneAmount<=(maxChargedRedstone-10)) {
            this.data.set(4,chargedRedstoneAmount+10);
            this.itemHandler.extractItem(CHARGED_REDSTONE_SLOT, 1, false);
        } else if ((this.itemHandler.getStackInSlot(CHARGED_REDSTONE_SLOT).getItem()== ModBlocks.CHARGED_REDSTONE_BLOCK.get().asItem()) && chargedRedstoneAmount<=(maxChargedRedstone-99)) {
            this.data.set(4,chargedRedstoneAmount+99);
            this.itemHandler.extractItem(CHARGED_REDSTONE_SLOT,1,false);
        }
    }

    private void craftItem() {
        Optional<EnergizerRecipe> recipe= getCurrentRecipe();
        if(recipe.isPresent() && redstoneAmount >= recipe.get().getRedstoneNeeded() && chargedRedstoneAmount>=recipe.get().getChargedRedstoneNeeded()){
            ItemStack result=recipe.get().getResultItem(null);

            this.itemHandler.extractItem(INPUT_SLOT_1,1,false);
            this.itemHandler.extractItem(INPUT_SLOT_2,1,false);
            this.itemHandler.extractItem(INPUT_SLOT_3,1,false);
            this.itemHandler.extractItem(INPUT_SLOT_4,1,false);
            this.itemHandler.extractItem(INPUT_SLOT_5,1,false);

            redstoneAmount -= recipe.get().getRedstoneNeeded();
            chargedRedstoneAmount -= recipe.get().getChargedRedstoneNeeded();

            this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                    this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount()+result.getCount()));
        } else if (redstoneAmount >= redstoneAndChargedRedstone && chargedRedstoneAmount >= redstoneAndChargedRedstone&& containsCustomMaterialsIngredients()) {

            ItemStack result=new ItemStack(ModItems.CUSTOM_MATERIAL.get(),
                    this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount()+1);
            CompoundTag nbt=result.getOrCreateTag();
            CompoundTag nbtParent1=this.itemHandler.getStackInSlot(INPUT_SLOT_2).getOrCreateTag();
            CompoundTag nbtParent2=this.itemHandler.getStackInSlot(INPUT_SLOT_4).getOrCreateTag();
            String[] nbtTags=new String[]{"speed","swim_speed","damage","dig_speed","move_speed","health","armor","knockback_res","durability"};
            for (String nbtTag : nbtTags) {
                if (nbtParent1.contains(nbtTag)&&nbtParent2.contains(nbtTag)) nbt.putInt(nbtTag, (int) ((nbtParent1.getInt(nbtTag)+nbtParent2.getInt(nbtTag))/1.5));
                else if (nbtParent1.contains(nbtTag)) {
                    nbt.putInt(nbtTag, (int) ((nbtParent1.getInt(nbtTag)+1)/0.75));
                } else if (nbtParent2.contains(nbtTag)) {
                    nbt.putInt(nbtTag, (int) ((nbtParent2.getInt(nbtTag)+1)/0.75));
                }else {
                    nbt.putInt(nbtTag,1);
                }
            }
            if (nbtParent1.contains("color")&&nbtParent2.contains("color")) nbt.putInt("color",blendColors(nbtParent1.getInt("color"),nbtParent2.getInt("color")));
            result.setTag(nbt);
            if (this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(ModItems.CUSTOM_MATERIAL.get())){
                CompoundTag nbtOutput=this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag();
                var ref = new Object() {
                    boolean equals = true;
                };
                nbt.getAllKeys().forEach((key)->{
                    if(nbtOutput.contains(key)){
                        if(nbtOutput.get(key)!=nbt.get(key)){
                            ref.equals=false;
                        }
                    }else {
                        ref.equals =false;
                    }
                });
                if (!ref.equals){return;}
            }
            redstoneAmount-=redstoneAndChargedRedstone;
            chargedRedstoneAmount-=redstoneAndChargedRedstone;

            this.itemHandler.extractItem(INPUT_SLOT_1,1,false);
            this.itemHandler.extractItem(INPUT_SLOT_2,1,false);
            this.itemHandler.extractItem(INPUT_SLOT_3,1,false);
            this.itemHandler.extractItem(INPUT_SLOT_4,1,false);
            this.itemHandler.extractItem(INPUT_SLOT_5,1,false);

            this.itemHandler.setStackInSlot(OUTPUT_SLOT,result);
        }
    }

    private boolean containsCustomMaterialsIngredients() {
        return this.itemHandler.getStackInSlot(INPUT_SLOT_3).getItem() == ModItems.WORLD_GEM.get() && this.itemHandler.getStackInSlot(INPUT_SLOT_1).getItem() == ModItems.CHARGED_REDSTONE.get() && this.itemHandler.getStackInSlot(INPUT_SLOT_5).getItem() == ModItems.CHARGED_REDSTONE.get() && this.itemHandler.getStackInSlot(INPUT_SLOT_4).is(ModItems.CUSTOM_MATERIAL.get()) && this.itemHandler.getStackInSlot(INPUT_SLOT_2).is(ModItems.CUSTOM_MATERIAL.get());
    }

    private void resetProgress() {
        progress=0;
    }

    private boolean hasProgressFinished() {
        return progress>=maxProgress;
    }

    private void increaseCraftingProgress() {
        Optional<EnergizerRecipe> recipe=getCurrentRecipe();
        if(recipe.isPresent()&& redstoneAmount >= recipe.get().getRedstoneNeeded() && chargedRedstoneAmount>=recipe.get().getChargedRedstoneNeeded()) {
            progress++;
        }else if(redstoneAmount >= redstoneAndChargedRedstone && chargedRedstoneAmount >= redstoneAndChargedRedstone&& containsCustomMaterialsIngredients()){
            progress++;
        }
    }

    private boolean hasRecipe() {
        Optional<EnergizerRecipe> recipe= getCurrentRecipe();

        if (recipe.isEmpty()){
            return false;
        }
        ItemStack result=recipe.get().getResultItem(null);

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<EnergizerRecipe> getCurrentRecipe() {
        SimpleContainer inventory=new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i,this.itemHandler.getStackInSlot(i));
        }

        assert this.level != null;
        return this.level.getRecipeManager().getRecipeFor(EnergizerRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()|| this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount()+count<=this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private int blendColors(int color1,int color2){
        int r= (((color1>>16)&0xFF)+((color2>>16)&0xFF))/2;
        int g= (((color1>>8)&0xFF)+((color2>>8)&0xFF))/2;
        int b= (((color1)&0xFF)+((color2)&0xFF))/2;
        return (r<<8|g)<<8|b;
    }
}
