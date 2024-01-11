package net.tarzan.testmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.tarzan.testmod.TestMod1;
import net.tarzan.testmod.block.ModBlocks;
import net.tarzan.testmod.item.ModItems;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> ALUMINIUM_SMELTABLES=List.of(ModItems.RAW_ALUMINIUM.get(),
            ModBlocks.ALUMINIUM_ORE.get(),ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get());
    private static final List<ItemLike> RAW_ALUMINIUM_BLOCK=List.of(ModBlocks.RAW_ALUMINIUM_BLOCK.get());
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        oreSmelting(consumer, ALUMINIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINIUM.get(), 0.7f,200,"aluminium");
        oreBlasting(consumer, ALUMINIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINIUM.get(), 0.7f,100,"aluminium");
        oreBlasting(consumer, RAW_ALUMINIUM_BLOCK, RecipeCategory.MISC, ModBlocks.ALUMINIUM_BLOCK.get(), 2.1f,300,"aluminium");
        oreSmelting(consumer, RAW_ALUMINIUM_BLOCK, RecipeCategory.MISC, ModBlocks.ALUMINIUM_BLOCK.get(), 2.1f,600,"aluminium");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALUMINIUM_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.ALUMINIUM.get())
                .unlockedBy(getHasName(ModItems.ALUMINIUM.get()), has(ModItems.ALUMINIUM.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ALUMINIUM.get(), 9)
                .requires(ModBlocks.ALUMINIUM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ALUMINIUM_BLOCK.get()), has(ModBlocks.ALUMINIUM_BLOCK.get()))
                .save(consumer);
    }
    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer, TestMod1.MODID+":" +getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
