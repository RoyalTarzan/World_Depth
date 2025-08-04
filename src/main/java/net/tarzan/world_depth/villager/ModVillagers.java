package net.tarzan.world_depth.villager;

import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.block.ModBlocks;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, World_Depth.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, World_Depth.MODID);

    public static final RegistryObject<PoiType> ENERGIZER_POI = POI_TYPES.register("energizer_poi",
            ()-> new PoiType(ImmutableSet.copyOf(ModBlocks.ENERGIZER.get().getStateDefinition().getPossibleStates()),1,2));

    public static final RegistryObject<VillagerProfession> ENERGIZER_SMITH = VILLAGER_PROFESSIONS.register("energizer_smith",
            ()-> new VillagerProfession("energizer_smith",
                    holder -> holder.get() == ENERGIZER_POI.get(), holder -> holder.get()== ENERGIZER_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(), SoundEvents.REDSTONE_TORCH_BURNOUT));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
