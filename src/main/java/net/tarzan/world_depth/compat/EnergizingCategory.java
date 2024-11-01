package net.tarzan.world_depth.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.block.ModBlocks;
import net.tarzan.world_depth.recipe.EnergizerRecipe;

public class EnergizingCategory implements IRecipeCategory<EnergizerRecipe> {
    public static final ResourceLocation UID=new ResourceLocation(World_Depth.MODID, "energizing");
    public static final ResourceLocation TEXTURE=new ResourceLocation(World_Depth.MODID, "textures/gui/energizer_gui.png");

    public static final RecipeType<EnergizerRecipe> ENERGIZING_TYPE=
        new RecipeType<>(UID, EnergizerRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public EnergizingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE,3,3,170,80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ENERGIZER.get()));
    }

    @Override
    public RecipeType<EnergizerRecipe> getRecipeType() {
        return ENERGIZING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.world_depth.energizer");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder, EnergizerRecipe energizerRecipe, IFocusGroup iFocusGroup) {
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT,33,8 ).addIngredients(energizerRecipe.getIngredients().get(0));
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT,55,8 ).addIngredients(energizerRecipe.getIngredients().get(1));
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT,77,8).addIngredients(energizerRecipe.getIngredients().get(2));
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT,99,8 ).addIngredients(energizerRecipe.getIngredients().get(3));
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT,121,8 ).addIngredients(energizerRecipe.getIngredients().get(4));


        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT,77, 56).addItemStack(energizerRecipe.getResultItem(null));
    }
}
