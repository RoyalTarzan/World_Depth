package net.tarzan.world_depth.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.item.custom.ChargedFoods;
import net.tarzan.world_depth.item.custom.ModArmorItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS=
            DeferredRegister.create(ForgeRegistries.ITEMS, World_Depth.MODID);
    public static final RegistryObject<Item> ALUMINIUM = ITEMS.register("aluminium",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENERGIZED_ALUMINIUM = ITEMS.register("energized_aluminium",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ALUMINIUM = ITEMS.register("raw_aluminium",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM = ITEMS.register("titanium",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENERGIZED_TITANIUM = ITEMS.register("energized_titanium",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHARGED_REDSTONE = ITEMS.register("charged_redstone",()->new ChargedFoods(new Item.Properties()));
    public static final RegistryObject<Item> TALIUM = ITEMS.register("talium",()->new Item(new Item.Properties()));


    public static final RegistryObject<Item> TITANIUM_HELMET=ITEMS.register("titanium_helmet",
            ()->new ArmorItem(ModArmorMaterials.TITANIUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_CHESTPLATE=ITEMS.register("titanium_chestplate",
            ()->new ArmorItem(ModArmorMaterials.TITANIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_LEGGINGS=ITEMS.register("titanium_leggings",
            ()->new ArmorItem(ModArmorMaterials.TITANIUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_BOOTS=ITEMS.register("titanium_boots",
        ()->new ArmorItem(ModArmorMaterials.TITANIUM, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> ENERGIZED_TITANIUM_HELMET=ITEMS.register("energized_titanium_helmet",
            ()->new ModArmorItem(ModArmorMaterials.ENERGIZED_TITANIUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> ENERGIZED_TITANIUM_CHESTPLATE=ITEMS.register("energized_titanium_chestplate",
            ()->new ModArmorItem(ModArmorMaterials.ENERGIZED_TITANIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> ENERGIZED_TITANIUM_LEGGINGS=ITEMS.register("energized_titanium_leggings",
            ()->new ModArmorItem(ModArmorMaterials.ENERGIZED_TITANIUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> ENERGIZED_TITANIUM_BOOTS=ITEMS.register("energized_titanium_boots",
            ()->new ModArmorItem(ModArmorMaterials.ENERGIZED_TITANIUM, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> TITANIUM_SWORD=ITEMS.register("titanium_sword",
            ()-> new SwordItem(ModToolTiers.TITANIUM,3,4, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_PICKAXE=ITEMS.register("titanium_pickaxe",
            ()-> new PickaxeItem(ModToolTiers.TITANIUM,1,1, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_AXE=ITEMS.register("titanium_axe",
            ()-> new AxeItem(ModToolTiers.TITANIUM,5,2, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_SHOVEL=ITEMS.register("titanium_shovel",
            ()-> new ShovelItem(ModToolTiers.TITANIUM,0,0, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_HOE=ITEMS.register("titanium_hoe",
            ()-> new HoeItem(ModToolTiers.TITANIUM,0,0, new Item.Properties()));


    public static final RegistryObject<Item> ALUMINIUM_HELMET=ITEMS.register("aluminium_helmet",
            ()->new ArmorItem(ModArmorMaterials.ALUMINIUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_CHESTPLATE=ITEMS.register("aluminium_chestplate",
            ()->new ArmorItem(ModArmorMaterials.ALUMINIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_LEGGINGS=ITEMS.register("aluminium_leggings",
            ()->new ArmorItem(ModArmorMaterials.ALUMINIUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_BOOTS=ITEMS.register("aluminium_boots",
            ()->new ArmorItem(ModArmorMaterials.ALUMINIUM, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> ENERGIZED_ALUMINIUM_HELMET=ITEMS.register("energized_aluminium_helmet",
            ()->new ModArmorItem(ModArmorMaterials.ENERGIZED_ALUMINIUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> ENERGIZED_ALUMINIUM_CHESTPLATE=ITEMS.register("energized_aluminium_chestplate",
            ()->new ModArmorItem(ModArmorMaterials.ENERGIZED_ALUMINIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> ENERGIZED_ALUMINIUM_LEGGINGS=ITEMS.register("energized_aluminium_leggings",
            ()->new ModArmorItem(ModArmorMaterials.ENERGIZED_ALUMINIUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> ENERGIZED_ALUMINIUM_BOOTS=ITEMS.register("energized_aluminium_boots",
            ()->new ModArmorItem(ModArmorMaterials.ENERGIZED_ALUMINIUM, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> ALUMINIUM_SWORD=ITEMS.register("aluminium_sword",
            ()-> new SwordItem(ModToolTiers.ALUMINIUM,3,4, new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_PICKAXE=ITEMS.register("aluminium_pickaxe",
            ()-> new PickaxeItem(ModToolTiers.ALUMINIUM,1,1, new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_AXE=ITEMS.register("aluminium_axe",
            ()-> new AxeItem(ModToolTiers.ALUMINIUM,5,2, new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_SHOVEL=ITEMS.register("aluminium_shovel",
            ()-> new ShovelItem(ModToolTiers.ALUMINIUM,0,0, new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_HOE=ITEMS.register("aluminium_hoe",
            ()-> new HoeItem(ModToolTiers.ALUMINIUM,0,0, new Item.Properties()));

    public static final RegistryObject<Item> TALIUM_HELMET =ITEMS.register("talium_helmet",
            ()->new ModArmorItem(ModArmorMaterials.TALIUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> TALIUM_CHESTPLATE=ITEMS.register("talium_chestplate",
            ()->new ModArmorItem(ModArmorMaterials.TALIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> TALIUM_LEGGINGS =ITEMS.register("talium_leggings",
            ()->new ModArmorItem(ModArmorMaterials.TALIUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> TALIUM_BOOTS =ITEMS.register("talium_boots",
            ()->new ModArmorItem(ModArmorMaterials.TALIUM, ArmorItem.Type.BOOTS, new Item.Properties()));


    public static void register(IEventBus eventBus){ITEMS.register(eventBus);}
}
