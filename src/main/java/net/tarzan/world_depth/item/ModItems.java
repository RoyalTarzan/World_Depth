package net.tarzan.world_depth.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.item.custom.ChargedFoods;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS=
            DeferredRegister.create(ForgeRegistries.ITEMS, World_Depth.MODID);
    public static final RegistryObject<Item> ALUMINIUM = ITEMS.register("aluminium",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ALUMINIUM = ITEMS.register("raw_aluminium",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM = ITEMS.register("titanium",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHARGED_REDSTONE = ITEMS.register("charged_redstone",()->new ChargedFoods(new Item.Properties()));

    public static void register(IEventBus eventBus){ITEMS.register(eventBus);}
}
