package net.tarzan.world_depth.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES=
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, World_Depth.MODID);

    public static final RegistryObject<BlockEntityType<EnergizerBlockEntity>> ENERGIZER_BE=
            BLOCK_ENTITIES.register("energizer_be",()->
                BlockEntityType.Builder.of(EnergizerBlockEntity::new, ModBlocks.ENERGIZER.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
