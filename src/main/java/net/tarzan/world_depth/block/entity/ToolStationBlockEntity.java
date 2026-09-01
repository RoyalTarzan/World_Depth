package net.tarzan.world_depth.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.*;
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
import net.tarzan.world_depth.screen.ToolStationMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ToolStationBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler=new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            assert level != null;
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler=LazyOptional.empty();

    protected final ContainerData data;
    private int chargedRedstone=0;
    private final int maxChargedRedstone=1000;
    private int progress=0;
    private final int maxProgress=50;
    private static final int MATERIAL_SLOT=0;
    private static final int STICK_SLOT=1;
    private static final int CHARGED_REDSTONE_SLOT=2;
    private static final int OUTPUT_SLOT=3;
    private static final int CRAFT_TYPE_SLOT=4;
    private CraftType craftType=null;

    public ToolStationBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.TOOL_STATION_BE.get(), pPos, pBlockState);
        this.data=new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0->progress;
                    case 1->chargedRedstone;
                    case 2->maxProgress;
                    default -> throw new IllegalStateException("Unexpected value: " + pIndex);
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0->progress=pValue;
                    case 1->chargedRedstone=pValue;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatableWithFallback("block.world_depth.tool_station_block_entity","Tool Station");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory inventory, @NotNull Player pPlayer) {
        return new ToolStationMenu(pContainerId,inventory,this,this.data);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("inventory",itemHandler.serializeNBT());
        pTag.putInt("tool_station.progress",progress);
        pTag.putInt("tool_station.charged_redstone",chargedRedstone);
        pTag.putInt("tool_station.craft_type",CraftType.getAsInt(this.craftType));
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress=pTag.getInt("tool_station.progress");
        chargedRedstone=pTag.getInt("tool_station.charged_redstone");
        craftType=CraftType.fromInt(pTag.getInt("tool_station.craft_type"));
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap,@Nullable Direction side) {
        if(cap== ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    public void drops() {
        SimpleContainer inventory= new SimpleContainer(itemHandler.getSlots());
        for (int i=0;i<itemHandler.getSlots();i++){
            inventory.setItem(i,itemHandler.getStackInSlot(i));
        }

        assert this.level != null;
        Containers.dropContents(this.level,this.worldPosition,inventory);
    }

    public void tick(Level pLevel1, BlockPos blockPos, BlockState pState1) {
        chargedRedstoneIncrease();
        if (!itemHandler.getStackInSlot(CRAFT_TYPE_SLOT).isEmpty()){
            Item item= itemHandler.getStackInSlot(CRAFT_TYPE_SLOT).getItem();
            if (item instanceof SwordItem){
                setCraftType(CraftType.SWORD);
            } else if (item instanceof AxeItem) {
                setCraftType(CraftType.AXE);
            } else if (item instanceof ShovelItem) {
                setCraftType(CraftType.SHOVEL);
            } else if (item instanceof HoeItem) {
                setCraftType(CraftType.HOE);
            } else if (item instanceof PickaxeItem) {
                setCraftType(CraftType.PICKAXE);
            } else if (item instanceof ArmorItem) {
                EquipmentSlot slot=((ArmorItem) item).getEquipmentSlot();
                if (slot==EquipmentSlot.CHEST){
                    setCraftType(CraftType.CHESTPLATE);
                } else if (slot==EquipmentSlot.HEAD) {
                    setCraftType(CraftType.HELMET);
                } else if (slot==EquipmentSlot.FEET) {
                    setCraftType(CraftType.BOOTS);
                } else if (slot==EquipmentSlot.LEGS) {
                    setCraftType(CraftType.LEGGINGS);
                }
            }
        }else {
            setCraftType(null);
        }
        if (checkCrafting()){
            progress++;
            setChanged(pLevel1,blockPos,pState1);
            if (progress>=maxProgress){
                craft();
                progress=0;
            }
        }else {
            progress=0;
        }
    }

    private void craft() {
        ItemStack materialStack=this.itemHandler.getStackInSlot(MATERIAL_SLOT);
        ItemStack stickStack=this.itemHandler.getStackInSlot(STICK_SLOT);
        switch (this.craftType){
            case PICKAXE -> {
                if (!this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()){return;}
                ItemStack result=new ItemStack(ModItems.CUSTOM_MATERIAL_PICKAXE.get());
                CompoundTag nbt=new CompoundTag();

                if(materialStack.getTag()==null){return;}
                if (materialStack.getTag().contains("damage"))nbt.putInt("damage",materialStack.getTag().getInt("damage"));else nbt.putInt("damage",1);
                if (materialStack.getTag().contains("speed"))nbt.putInt("speed",materialStack.getTag().getInt("speed"));else nbt.putInt("speed",1);
                if (materialStack.getTag().contains("durability"))nbt.putInt("durability",materialStack.getTag().getInt("durability"));else nbt.putInt("durability",100);
                if (materialStack.getTag().contains("dig_speed"))nbt.putInt("dig_speed",materialStack.getTag().getInt("dig_speed"));else nbt.putInt("dig_speed",100);
                if (materialStack.getTag().contains("color")) nbt.putInt("color",materialStack.getTag().getInt("color"));

                materialStack.setCount(materialStack.getCount()-3);
                stickStack.setCount(stickStack.getCount()-2);
                chargedRedstone-=250;
                result.setTag(nbt);
                this.itemHandler.setStackInSlot(OUTPUT_SLOT,result);
            }
            case AXE -> {
                if (!this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()){return;}
                ItemStack result=new ItemStack(ModItems.CUSTOM_MATERIAL_AXE.get());
                CompoundTag nbt=new CompoundTag();

                if(materialStack.getTag()==null){return;}
                if (materialStack.getTag().contains("color")) nbt.putInt("color",materialStack.getTag().getInt("color"));
                if (materialStack.getTag().contains("damage"))nbt.putInt("damage",materialStack.getTag().getInt("damage"));else nbt.putInt("damage",1);
                if (materialStack.getTag().contains("speed"))nbt.putInt("speed",materialStack.getTag().getInt("speed"));else nbt.putInt("speed",1);
                if (materialStack.getTag().contains("durability"))nbt.putInt("durability",materialStack.getTag().getInt("durability"));else nbt.putInt("durability",100);
                if (materialStack.getTag().contains("dig_speed"))nbt.putInt("dig_speed",materialStack.getTag().getInt("dig_speed"));else nbt.putInt("dig_speed",100);

                materialStack.setCount(materialStack.getCount()-3);
                stickStack.setCount(stickStack.getCount()-2);
                chargedRedstone-=250;
                result.setTag(nbt);
                this.itemHandler.setStackInSlot(OUTPUT_SLOT,result);
            }
            case SWORD -> {
                if (!this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()){return;}
                ItemStack result=new ItemStack(ModItems.CUSTOM_MATERIAL_SWORD.get());
                CompoundTag nbt=new CompoundTag();

                if(materialStack.getTag()==null){return;}
                if (materialStack.getTag().contains("color")) nbt.putInt("color",materialStack.getTag().getInt("color"));
                if (materialStack.getTag().contains("damage"))nbt.putInt("damage",materialStack.getTag().getInt("damage"));else nbt.putInt("damage",1);
                if (materialStack.getTag().contains("speed"))nbt.putInt("speed",materialStack.getTag().getInt("speed"));else nbt.putInt("speed",1);
                if (materialStack.getTag().contains("durability"))nbt.putInt("durability",materialStack.getTag().getInt("durability"));else nbt.putInt("durability",100);

                materialStack.setCount(materialStack.getCount()-2);
                stickStack.setCount(stickStack.getCount()-1);
                chargedRedstone-=250;
                result.setTag(nbt);
                this.itemHandler.setStackInSlot(OUTPUT_SLOT,result);
            }
            case HOE -> {
                if (!this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()){return;}
                ItemStack result=new ItemStack(ModItems.CUSTOM_MATERIAL_HOE.get());
                CompoundTag nbt=new CompoundTag();

                if(materialStack.getTag()==null){return;}
                if (materialStack.getTag().contains("color")) nbt.putInt("color",materialStack.getTag().getInt("color"));
                if (materialStack.getTag().contains("damage"))nbt.putInt("damage",materialStack.getTag().getInt("damage"));else nbt.putInt("damage",1);
                if (materialStack.getTag().contains("speed"))nbt.putInt("speed",materialStack.getTag().getInt("speed"));else nbt.putInt("speed",1);
                if (materialStack.getTag().contains("durability"))nbt.putInt("durability",materialStack.getTag().getInt("durability"));else nbt.putInt("durability",100);
                if (materialStack.getTag().contains("dig_speed"))nbt.putInt("dig_speed",materialStack.getTag().getInt("dig_speed"));else nbt.putInt("dig_speed",100);

                materialStack.setCount(materialStack.getCount()-2);
                stickStack.setCount(stickStack.getCount()-2);
                chargedRedstone-=250;
                result.setTag(nbt);
                this.itemHandler.setStackInSlot(OUTPUT_SLOT,result);
            }
            case SHOVEL -> {
                if (!this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()){return;}
                ItemStack result=new ItemStack(ModItems.CUSTOM_MATERIAL_SHOVEL.get());
                CompoundTag nbt=new CompoundTag();

                if(materialStack.getTag()==null){return;}
                if (materialStack.getTag().contains("color")) nbt.putInt("color",materialStack.getTag().getInt("color"));
                if (materialStack.getTag().contains("damage"))nbt.putInt("damage",materialStack.getTag().getInt("damage"));else nbt.putInt("damage",1);
                if (materialStack.getTag().contains("speed"))nbt.putInt("speed",materialStack.getTag().getInt("speed"));else nbt.putInt("speed",1);
                if (materialStack.getTag().contains("durability"))nbt.putInt("durability",materialStack.getTag().getInt("durability"));else nbt.putInt("durability",100);
                if (materialStack.getTag().contains("dig_speed"))nbt.putInt("dig_speed",materialStack.getTag().getInt("dig_speed"));else nbt.putInt("dig_speed",100);

                materialStack.setCount(materialStack.getCount()-1);
                stickStack.setCount(stickStack.getCount()-2);
                chargedRedstone-=250;
                result.setTag(nbt);
                this.itemHandler.setStackInSlot(OUTPUT_SLOT,result);
            }
            case BOOTS -> {
                if (!this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()){return;}
                ItemStack result=new ItemStack(ModItems.CUSTOM_MATERIAL_BOOTS.get());
                CompoundTag nbt=new CompoundTag();

                if(materialStack.getTag()==null){return;}
                if (materialStack.getTag().contains("color")) nbt.putInt("color",materialStack.getTag().getInt("color"));
                if (materialStack.getTag().contains("durability"))nbt.putInt("durability",materialStack.getTag().getInt("durability"));else nbt.putInt("durability",100);
                if (materialStack.getTag().contains("knockback_res"))nbt.putInt("knockback_res",materialStack.getTag().getInt("knockback_res"));else nbt.putInt("knockback_res",0);
                if (materialStack.getTag().contains("armor"))nbt.putInt("armor",materialStack.getTag().getInt("armor"));else nbt.putInt("armor",1);
                if (materialStack.getTag().contains("move_speed"))nbt.putInt("move_speed",materialStack.getTag().getInt("move_speed"));else nbt.putInt("move_speed",0);

                materialStack.setCount(materialStack.getCount()-4);
                chargedRedstone-=300;
                result.setTag(nbt);
                this.itemHandler.setStackInSlot(OUTPUT_SLOT,result);
            }
            case HELMET -> {
                if (!this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()){return;}
                ItemStack result=new ItemStack(ModItems.CUSTOM_MATERIAL_HELMET.get());
                CompoundTag nbt=new CompoundTag();

                if(materialStack.getTag()==null){return;}
                if (materialStack.getTag().contains("color")) nbt.putInt("color",materialStack.getTag().getInt("color"));
                if (materialStack.getTag().contains("durability"))nbt.putInt("durability",materialStack.getTag().getInt("durability"));else nbt.putInt("durability",100);
                if (materialStack.getTag().contains("knockback_res"))nbt.putInt("knockback_res",materialStack.getTag().getInt("knockback_res"));else nbt.putInt("knockback_res",0);
                if (materialStack.getTag().contains("armor"))nbt.putInt("armor",materialStack.getTag().getInt("armor"));else nbt.putInt("armor",1);
                if (materialStack.getTag().contains("swim_speed"))nbt.putInt("swim_speed",materialStack.getTag().getInt("swim_speed"));else nbt.putInt("swim_speed",0);

                materialStack.setCount(materialStack.getCount()-5);
                chargedRedstone-=300;
                result.setTag(nbt);
                this.itemHandler.setStackInSlot(OUTPUT_SLOT,result);
            }
            case LEGGINGS -> {
                if (!this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()){return;}
                ItemStack result=new ItemStack(ModItems.CUSTOM_MATERIAL_LEGGINGS.get());
                CompoundTag nbt=new CompoundTag();

                if(materialStack.getTag()==null){return;}
                if (materialStack.getTag().contains("color")) nbt.putInt("color",materialStack.getTag().getInt("color"));
                if (materialStack.getTag().contains("durability"))nbt.putInt("durability",materialStack.getTag().getInt("durability"));else nbt.putInt("durability",100);
                if (materialStack.getTag().contains("knockback_res"))nbt.putInt("knockback_res",materialStack.getTag().getInt("knockback_res"));else nbt.putInt("knockback_res",0);
                if (materialStack.getTag().contains("armor"))nbt.putInt("armor",materialStack.getTag().getInt("armor"));else nbt.putInt("armor",1);
                if (materialStack.getTag().contains("damage"))nbt.putInt("damage",materialStack.getTag().getInt("damage"));else nbt.putInt("damage",0);
                if (materialStack.getTag().contains("speed"))nbt.putInt("speed",materialStack.getTag().getInt("speed"));else nbt.putInt("speed",0);

                materialStack.setCount(materialStack.getCount()-7);
                chargedRedstone-=300;
                result.setTag(nbt);
                this.itemHandler.setStackInSlot(OUTPUT_SLOT,result);
            }
            case CHESTPLATE -> {
                if (!this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()){return;}
                ItemStack result=new ItemStack(ModItems.CUSTOM_MATERIAL_CHESTPLATE.get());
                CompoundTag nbt=new CompoundTag();

                if(materialStack.getTag()==null){return;}
                if (materialStack.getTag().contains("color")) nbt.putInt("color",materialStack.getTag().getInt("color"));
                if (materialStack.getTag().contains("durability"))nbt.putInt("durability",materialStack.getTag().getInt("durability"));else nbt.putInt("durability",100);
                if (materialStack.getTag().contains("knockback_res"))nbt.putInt("knockback_res",materialStack.getTag().getInt("knockback_res"));else nbt.putInt("knockback_res",0);
                if (materialStack.getTag().contains("armor"))nbt.putInt("armor",materialStack.getTag().getInt("armor"));else nbt.putInt("armor",1);
                if (materialStack.getTag().contains("health"))nbt.putInt("health",materialStack.getTag().getInt("health"));else nbt.putInt("health",0);

                materialStack.setCount(materialStack.getCount()-8);
                chargedRedstone-=300;
                result.setTag(nbt);
                this.itemHandler.setStackInSlot(OUTPUT_SLOT,result);
            }
        }
    }

    private boolean checkCrafting() {
        ItemStack materialStack=this.itemHandler.getStackInSlot(MATERIAL_SLOT);
        ItemStack stickStack=this.itemHandler.getStackInSlot(STICK_SLOT);
        if (!this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()||craftType==null){return false;}
        switch (this.craftType){
            case PICKAXE, AXE -> {
                if (materialStack.getCount()>=3&&stickStack.getCount()>=2 &&materialStack.is(ModItems.CUSTOM_MATERIAL.get())&&stickStack.is(Items.STICK)&&chargedRedstone>=250){
                    return true;
                }
            }
            case SWORD -> {
                if (materialStack.getCount()>=2&&stickStack.getCount()>=1 &&materialStack.is(ModItems.CUSTOM_MATERIAL.get())&&stickStack.is(Items.STICK)&&chargedRedstone>=250){
                    return true;
                }
            }
            case HOE -> {
                if (materialStack.getCount()>=2&&stickStack.getCount()>=2 &&materialStack.is(ModItems.CUSTOM_MATERIAL.get())&&stickStack.is(Items.STICK)&&chargedRedstone>=250){
                    return true;
                }
            }
            case SHOVEL -> {
                if (materialStack.getCount()>=1&&stickStack.getCount()>=2 &&materialStack.is(ModItems.CUSTOM_MATERIAL.get())&&stickStack.is(Items.STICK)&&chargedRedstone>=250){
                    return true;
                }
            }
            case CHESTPLATE -> {
                if (materialStack.getCount()>=8&&materialStack.is(ModItems.CUSTOM_MATERIAL.get())&&chargedRedstone>=300){
                    return true;
                }
            }
            case LEGGINGS -> {
                if (materialStack.getCount()>=7&&materialStack.is(ModItems.CUSTOM_MATERIAL.get())&&chargedRedstone>=300){
                    return true;
                }
            }
            case HELMET -> {
                if (materialStack.getCount()>=5&&materialStack.is(ModItems.CUSTOM_MATERIAL.get())&&chargedRedstone>=300){
                    return true;
                }
            }
            case BOOTS -> {
                if (materialStack.getCount()>=4&&materialStack.is(ModItems.CUSTOM_MATERIAL.get())&&chargedRedstone>=300){
                    return true;
                }
            }
        }
        return false;
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

    private void chargedRedstoneIncrease(){
        if((this.itemHandler.getStackInSlot(CHARGED_REDSTONE_SLOT).getItem()== ModItems.CHARGED_REDSTONE.get()) && chargedRedstone<=(maxChargedRedstone-10)) {
            this.data.set(1,chargedRedstone+10);
            this.itemHandler.extractItem(CHARGED_REDSTONE_SLOT, 1, false);
        } else if ((this.itemHandler.getStackInSlot(CHARGED_REDSTONE_SLOT).getItem()== ModBlocks.CHARGED_REDSTONE_BLOCK.get().asItem()) && chargedRedstone<=(maxChargedRedstone-99)) {
            this.data.set(1,chargedRedstone+99);
            this.itemHandler.extractItem(CHARGED_REDSTONE_SLOT,1,false);
        }
    }

    public void setCraftType(CraftType type) {
        craftType=type;
    }

    public enum CraftType{
        PICKAXE,
        AXE,
        SWORD,
        HOE,
        SHOVEL,
        HELMET,
        BOOTS,
        CHESTPLATE,
        LEGGINGS;

        public static int getAsInt(CraftType type){
            return switch (type){
                case PICKAXE -> 0;
                case AXE -> 1;
                case SWORD -> 2;
                case HOE -> 3;
                case SHOVEL -> 4;
                case HELMET -> 5;
                case BOOTS -> 6;
                case CHESTPLATE -> 7;
                case LEGGINGS -> 8;
            };
        }

        public static CraftType fromInt(int number){
            return switch (number){
                case 0 -> PICKAXE;
                case 1 -> AXE;
                case 2 -> SWORD;
                case 3 -> HOE;
                case 4 -> SHOVEL;
                case 5 -> HELMET;
                case 6 -> BOOTS;
                case 7 -> CHESTPLATE;
                case 8 -> LEGGINGS;
                default -> throw new IllegalStateException("Unexpected value: " + number);
            };
        }
    }
}
