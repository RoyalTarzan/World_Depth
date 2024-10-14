package net.tarzan.world_depth.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.world_depth.World_Depth;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS=
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, World_Depth.MODID);

    public static final RegistryObject<RecipeSerializer<EnergizerRecipe>> ENERGIZER_SERIALIZER=
            SERIALIZERS.register("energizing",()->EnergizerRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
