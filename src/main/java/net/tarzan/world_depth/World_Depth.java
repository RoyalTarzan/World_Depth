package net.tarzan.world_depth;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tarzan.world_depth.block.ModBlocks;
import net.tarzan.world_depth.block.entity.ModBlockEntities;
import net.tarzan.world_depth.item.ModCreativeModeTabs;
import net.tarzan.world_depth.item.ModItems;
import net.tarzan.world_depth.recipe.ModRecipes;
import net.tarzan.world_depth.screen.EnergizerScreen;
import net.tarzan.world_depth.screen.ModMenuTypes;
import org.slf4j.Logger;

@Mod(World_Depth.MODID)
public class World_Depth
{
    public static final String MODID = "world_depth";
    private static final Logger LOGGER = LogUtils.getLogger();
    public World_Depth()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if(event.getTabKey()== CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.ALUMINIUM);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            MenuScreens.register(ModMenuTypes.ENERGIZER_MENU.get(), EnergizerScreen::new);
        }
    }
}
