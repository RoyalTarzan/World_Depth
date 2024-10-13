package net.tarzan.world_depth.screen;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.world_depth.World_Depth;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS=
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, World_Depth.MODID);

    public static final RegistryObject<MenuType<EnergizerMenu>> ENERGIZER_MENU=
            registerMenuType("energizer_menu",EnergizerMenu::new);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory){
        return MENUS.register(name, ()-> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}
