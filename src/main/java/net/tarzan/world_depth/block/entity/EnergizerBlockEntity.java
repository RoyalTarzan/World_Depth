package net.tarzan.world_depth.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
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

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class EnergizerBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler=new ItemStackHandler(9) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(level!=null&&!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if (slot==REDSTONE_SLOT){
                return stack.is(Items.REDSTONE) || stack.is(Items.REDSTONE_BLOCK);
            }else if (slot==CHARGED_REDSTONE_SLOT){
                return stack.is(ModItems.CHARGED_REDSTONE.get()) || stack.is(ModBlocks.CHARGED_REDSTONE_BLOCK.get().asItem());
            } else if (slot==OUTPUT_SLOT) {
                return false;
            } else if (slot==INPUT_SLOT_1) {
                if (recipeLocked){
                    return stack.is(lockedIngredients[0]);
                }
                else {return true;}
            } else if (slot==INPUT_SLOT_2) {
                if (recipeLocked){
                    return stack.is(lockedIngredients[1]);
                }
                else {return true;}
            } else if (slot==INPUT_SLOT_3) {
                if (recipeLocked){
                    return stack.is(lockedIngredients[2]);
                }
                else {return true;}
            } else if (slot==INPUT_SLOT_4) {
                if (recipeLocked){
                    return stack.is(lockedIngredients[3]);
                }
                else {return true;}
            } else if (slot==INPUT_SLOT_5) {
                if (recipeLocked){
                    return stack.is(lockedIngredients[4]);
                }
                else {return true;}
            } else if (slot==LOCK_RECIPE_SLOT) {
                return stack.is(Items.NETHER_STAR);
            }

            return super.isItemValid(slot, stack);
        }
    };

    public static final int INPUT_SLOT_1=0;
    public static final int INPUT_SLOT_2=1;
    public static final int INPUT_SLOT_3=2;
    public static final int INPUT_SLOT_4=3;
    public static final int INPUT_SLOT_5=4;
    public static final int OUTPUT_SLOT= 5;
    public static final int REDSTONE_SLOT=6;
    public static final int CHARGED_REDSTONE_SLOT=7;
    public static final int LOCK_RECIPE_SLOT=8;

    private LazyOptional<IItemHandler> lazyItemHandler=LazyOptional.empty();

    protected final ContainerData data;
    private int progress=0;
    private int maxProgress=78;
    private int redstoneAmount=0;
    private int maxRedstone=500;
    private int chargedRedstoneAmount=0;
    private int maxChargedRedstone=500;
    private final int redstoneAndChargedRedstone=125;
    private boolean recipeLocked=false;

    public final Item[] lockedIngredients=new Item[5];

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
                    case 6->recipeLocked?1:0;
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
                    case 6->recipeLocked= pValue == 1;
                }
            }

            @Override
            public int getCount() {
                return 7;
            }
        };
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap== ForgeCapabilities.ITEM_HANDLER){
            if(side==Direction.DOWN){
                return LazyOptional.of(()->new ItemHandlerWrapper(itemHandler,false,true,OUTPUT_SLOT,new ArrayList<>())).cast();
            } else if (side==Direction.EAST||side==Direction.WEST) {
                ArrayList<Item> items=new ArrayList<>();
                items.add(Items.REDSTONE);
                items.add(Items.REDSTONE_BLOCK);
                return LazyOptional.of(()->new ItemHandlerWrapper(itemHandler,true,false,REDSTONE_SLOT,items)).cast();
            } else if (side==Direction.NORTH||side==Direction.SOUTH) {
                ArrayList<Item> items=new ArrayList<>();
                items.add(ModItems.CHARGED_REDSTONE.get());
                items.add(ModBlocks.CHARGED_REDSTONE_BLOCK.get().asItem());
                return LazyOptional.of(()->new ItemHandlerWrapper(itemHandler,true,false,CHARGED_REDSTONE_SLOT,items)).cast();
            }
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
        if (pTag.contains("inventory")){
            ListTag invTag=pTag.getList("inventory", Tag.TAG_COMPOUND);
            while (invTag.size()<itemHandler.getSlots()){
                invTag.add(invTag.get(1));
            }
        }
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
        if (itemHandler.getStackInSlot(LOCK_RECIPE_SLOT).is(Items.NETHER_STAR)){
            recipeLocked=true;
            if (Arrays.equals(lockedIngredients, new Item[5])) {
                for (int i = 0; i < 5; i++) {
                    lockedIngredients[i] = itemHandler.getStackInSlot(i+INPUT_SLOT_1).getItem();
                }
            }
        }else {
            recipeLocked=false;
            Arrays.fill(lockedIngredients, null);
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
            ItemStack parent1=this.itemHandler.getStackInSlot(INPUT_SLOT_2);
            ItemStack parent2=this.itemHandler.getStackInSlot(INPUT_SLOT_4);
            ItemStack result=new ItemStack(ModItems.CUSTOM_MATERIAL.get(),
                    this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount()+1);
            CompoundTag nbt = getNbt(result, parent1, parent2);
            result.setTag(nbt);
            if (this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(ModItems.CUSTOM_MATERIAL.get())){
                CompoundTag nbtOutput=this.itemHandler.getStackInSlot(OUTPUT_SLOT).getOrCreateTag();
                if (!nbt.equals(nbtOutput))return;
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

    private @NotNull CompoundTag getNbt(ItemStack result, ItemStack parent1, ItemStack parent2) {
        String[] nbtTags=new String[]{"speed","swim_speed","damage","dig_speed","move_speed","health","armor","knockback_res","durability"};
        CompoundTag nbt= result.getOrCreateTag();
        CompoundTag nbtParent1= putNbt(parent1);
        CompoundTag nbtParent2= putNbt(parent2);
        for (String nbtTag : nbtTags) {
            if (nbtParent1.contains(nbtTag)&&nbtParent2.contains(nbtTag)) nbt.putInt(nbtTag, (int) Math.ceil((nbtParent1.getInt(nbtTag)+nbtParent2.getInt(nbtTag))/1.5));
            else if (nbtParent1.contains(nbtTag)) {
                nbt.putInt(nbtTag, (int) Math.ceil((nbtParent1.getInt(nbtTag)+1)/0.75));
            } else if (nbtParent2.contains(nbtTag)) {
                nbt.putInt(nbtTag, (int) Math.ceil((nbtParent2.getInt(nbtTag)+1)/0.75));
            }else {
                nbt.putInt(nbtTag,1);
            }
        }
        if (nbtParent1.contains("color")&&nbtParent2.contains("color")) nbt.putInt("color",blendColors(nbtParent1.getInt("color"),nbtParent2.getInt("color")));
        if (nbtParent1.contains("name")&&nbtParent2.contains("name")){
            String name1=nbtParent1.getString("name");
            String name2=nbtParent2.getString("name");
            String name;
            if (name1.equals(name2)){
                name="Energized "+name1;
            }else {
                name1=name1.replace("Energized ","");
                name2=name2.replace("Energized ","");
                if (name1.equals(name2)){
                    name="Half Energized "+name1;
                }else{
                    name=name1.substring(0,name1.length()/2+1)+name2.toLowerCase().substring(name2.length()/2+1);
                }
            }
            nbt.putString("name",name);
            CompoundTag nbt1=new CompoundTag();
            nbt1.putString("Name","{\"text\":\""+name+" Ingot\",\"italic\":false}");
            nbt.put("display",nbt1);
        }
        return nbt;
    }


    public CompoundTag putNbt(ItemStack item){
        CompoundTag nbt1= item.getTag();
        if (nbt1==null){
            nbt1=new CompoundTag();
        }
        if (item.is(Items.COPPER_INGOT)){
            nbt1.putString("name","Copper");
            nbt1.putInt("color", Color.ORANGE.getRGB());
            nbt1.putInt("swim_speed",0);
            nbt1.putInt("durability",2031);
            nbt1.putInt("speed",1);
            nbt1.putInt("damage",1);
            nbt1.putInt("dig_speed",1);
            nbt1.putInt("health",2);
            nbt1.putInt("armor",3);
            nbt1.putInt("knockback_res",0);
            nbt1.putDouble("move_speed",0.6);
        } else if (item.is(Items.IRON_INGOT)) {
            nbt1.putString("name","Iron");
            nbt1.putInt("color", Color.LIGHT_GRAY.getRGB());
            nbt1.putInt("swim_speed",0);
            nbt1.putInt("durability",191);
            nbt1.putInt("speed",1);
            nbt1.putInt("damage",2);
            nbt1.putInt("dig_speed",1);
            nbt1.putInt("health",1);
            nbt1.putInt("armor",4);
            nbt1.putInt("knockback_res",0);
            nbt1.putDouble("move_speed",0.5);
        } else if (item.is(Items.GOLD_INGOT)) {
            nbt1.putString("name","Gold");
            nbt1.putInt("color", Color.YELLOW.getRGB());
            nbt1.putInt("swim_speed",0);
            nbt1.putInt("durability",32);
            nbt1.putInt("speed",1);
            nbt1.putInt("damage",1);
            nbt1.putInt("dig_speed",3);
            nbt1.putInt("health",4);
            nbt1.putInt("armor",3);
            nbt1.putInt("knockback_res",0);
            nbt1.putDouble("move_speed",1.1);
        } else if (item.is(Items.NETHERITE_INGOT)) {
            nbt1.putString("name","Netherite");
            nbt1.putInt("color", Color.BLACK.getRGB());
            nbt1.putInt("swim_speed",0);
            nbt1.putInt("durability",2031);
            nbt1.putInt("speed",1);
            nbt1.putInt("damage",3);
            nbt1.putInt("dig_speed",2);
            nbt1.putInt("health",3);
            nbt1.putInt("armor",5);
            nbt1.putInt("knockback_res",4);
            nbt1.putDouble("move_speed",0.2);
        } else if (item.is(ModItems.ALUMINIUM.get())) {
            nbt1.putString("name","Aluminium");
            nbt1.putInt("color", new Color(65, 65, 83).getRGB());
            nbt1.putInt("swim_speed",2);
            nbt1.putInt("durability",1031);
            nbt1.putInt("speed",100);
            nbt1.putInt("damage",3);
            nbt1.putInt("dig_speed",3);
            nbt1.putInt("health",5);
            nbt1.putInt("armor",4);
            nbt1.putInt("knockback_res",0);
            nbt1.putDouble("move_speed",2);
        } else if (item.is(ModItems.TITANIUM.get())) {
            nbt1.putString("name","Titanium");
            nbt1.putInt("color", new Color(100,100,100).getRGB());
            nbt1.putInt("swim_speed",0);
            nbt1.putInt("durability",3000);
            nbt1.putInt("speed",1);
            nbt1.putInt("damage",6);
            nbt1.putInt("dig_speed",4);
            nbt1.putInt("health",10);
            nbt1.putInt("armor",8);
            nbt1.putInt("knockback_res",6);
            nbt1.putDouble("move_speed",0.1);
        }else {
            return nbt1;
        }
        return nbt1;
    }

    private boolean containsCustomMaterialsIngredients() {
        ItemStack stack1=this.itemHandler.getStackInSlot(INPUT_SLOT_1);
        ItemStack stack2=this.itemHandler.getStackInSlot(INPUT_SLOT_2);
        ItemStack stack3=this.itemHandler.getStackInSlot(INPUT_SLOT_3);
        ItemStack stack4=this.itemHandler.getStackInSlot(INPUT_SLOT_4);
        ItemStack stack5=this.itemHandler.getStackInSlot(INPUT_SLOT_5);
        ArrayList<Item> ingots=new ArrayList<>();
        ingots.add(Items.IRON_INGOT);ingots.add(Items.NETHERITE_INGOT);ingots.add(Items.GOLD_INGOT);ingots.add(Items.COPPER_INGOT);ingots.add(ModItems.CUSTOM_MATERIAL.get());ingots.add(ModItems.TITANIUM.get());ingots.add(ModItems.ALUMINIUM.get());

        return stack3.is(ModItems.WORLD_GEM.get()) &&
                stack1.is(ModItems.CHARGED_REDSTONE.get()) &&
                stack5.is(ModItems.CHARGED_REDSTONE.get()) &&
                (ingots.contains(stack4.getItem()))&&
                ingots.contains(stack2.getItem());
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
