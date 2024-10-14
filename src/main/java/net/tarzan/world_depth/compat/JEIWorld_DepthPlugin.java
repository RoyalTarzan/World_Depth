package net.tarzan.world_depth.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.recipe.EnergizerRecipe;
import net.tarzan.world_depth.screen.EnergizerScreen;

import java.util.List;

@JeiPlugin
public class JEIWorld_DepthPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(World_Depth.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new EnergizingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager= Minecraft.getInstance().level.getRecipeManager();

        List<EnergizerRecipe> energizingRecipes= recipeManager.getAllRecipesFor(EnergizerRecipe.Type.INSTANCE);
        registration.addRecipes(EnergizingCategory.ENERGIZING_TYPE, energizingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(EnergizerScreen.class, 43,30,91,30,
                EnergizingCategory.ENERGIZING_TYPE);
    }
}