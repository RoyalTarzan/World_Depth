package net.tarzan.world_depth.worldgen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.worldgen.tree.custom.StookFoliagePlacer;

public class ModFoliagePlacerTypes {
    public  static final DeferredRegister<FoliagePlacerType<?>> Foliage_PLACERS=
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, World_Depth.MODID);

    public static final RegistryObject<FoliagePlacerType<StookFoliagePlacer>> STOOK_FOLIAGE_PLACER=
            Foliage_PLACERS.register("stook_foliage_placer",()-> new FoliagePlacerType<>(StookFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus){
        Foliage_PLACERS.register(eventBus);
    }
}
