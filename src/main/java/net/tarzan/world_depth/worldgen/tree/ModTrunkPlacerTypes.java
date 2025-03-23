package net.tarzan.world_depth.worldgen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.worldgen.tree.custom.StookTrunkPlacer;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER=
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, World_Depth.MODID);

    public static final RegistryObject<TrunkPlacerType<StookTrunkPlacer>> STOOK_TRUNK_PLACER=
            TRUNK_PLACER.register("stook_trunk_placer",()->new TrunkPlacerType<>(StookTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus){
        TRUNK_PLACER.register(eventBus);
    }
}
