package net.tarzan.testmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.testmod.TestMod1;
import net.tarzan.testmod.item.custom.ChargedFoods;
import net.tarzan.testmod.item.custom.test;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS=
            DeferredRegister.create(ForgeRegistries.ITEMS, TestMod1.MODID);
    public static final RegistryObject<Item> ALUMINIUM = ITEMS.register("aluminium",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ALUMINIUM = ITEMS.register("raw_aluminium",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM = ITEMS.register("titanium",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHARGED_REDSTONE = ITEMS.register("charged_redstone",()->new Item(new Item.Properties().food(ChargedFoods.CHARGEDREDSTONE)));
    public static final RegistryObject<Item> CHARGED_REDSTONEP = ITEMS.register("charged_redstonep",()->new test(new Item.Properties()));

    public static void register(IEventBus eventBus){ITEMS.register(eventBus);}
}
