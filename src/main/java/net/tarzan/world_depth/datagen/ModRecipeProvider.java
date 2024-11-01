package net.tarzan.world_depth.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.block.ModBlocks;
import net.tarzan.world_depth.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> ALUMINIUM_SMELTABLES=List.of(ModItems.RAW_ALUMINIUM.get(),
            ModBlocks.ALUMINIUM_ORE.get(),ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get(), ModBlocks.SOAP_STONE_ALUMINIUM_ORE.get(), ModBlocks.FERYL_STONE_ALUMINIUM_ORE.get(), ModBlocks.ANDESITE_ALUMINIUM_ORE.get(), ModBlocks.DIORITE_ALUMINIUM_ORE.get(), ModBlocks.GRANITE_ALUMINIUM_ORE.get(), ModBlocks.TUFF_ALUMINIUM_ORE.get());
    private static final List<ItemLike> RAW_ALUMINIUM_BLOCK=List.of(ModBlocks.RAW_ALUMINIUM_BLOCK.get());
    private static final List<ItemLike> RAW_TITANIUM_BLOCK=List.of(ModBlocks.RAW_TITANIUM_BLOCK.get());
    private static final List<ItemLike> TITANIUM_SMELTABLES=List.of(ModItems.RAW_TITANIUM.get(),
        ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get(), ModBlocks.SOAP_STONE_TITANIUM_ORE.get(), ModBlocks.FERYL_STONE_TITANIUM_ORE.get(), ModBlocks.ANDESITE_TITANIUM_ORE.get(), ModBlocks.DIORITE_TITANIUM_ORE.get(), ModBlocks.GRANITE_TITANIUM_ORE.get(), ModBlocks.TUFF_TITANIUM_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        oreSmelting(consumer, ALUMINIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINIUM.get(), 0.7f,200,"aluminium");
        oreBlasting(consumer, ALUMINIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINIUM.get(), 0.7f,100,"aluminium");
        oreSmelting(consumer, TITANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.TITANIUM.get(), 1f,200,"titanium");
        oreBlasting(consumer, TITANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.TITANIUM.get(), 1f,100,"titanium");
        oreBlasting(consumer, RAW_ALUMINIUM_BLOCK, RecipeCategory.MISC, ModBlocks.ALUMINIUM_BLOCK.get(), 2.1f,300,"aluminium");
        oreSmelting(consumer, RAW_ALUMINIUM_BLOCK, RecipeCategory.MISC, ModBlocks.ALUMINIUM_BLOCK.get(), 2.1f,600,"aluminium");
        oreBlasting(consumer, RAW_TITANIUM_BLOCK, RecipeCategory.MISC, ModBlocks.TITANIUM_BLOCK.get(), 3f,300,"titanium");
        oreSmelting(consumer, RAW_TITANIUM_BLOCK, RecipeCategory.MISC, ModBlocks.TITANIUM_BLOCK.get(), 3f,600,"titanium");

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.ENERGIZER.get())
                .pattern("STS")
                .pattern(" S ")
                .pattern("RRR")
                .define('S', ModItems.TITANIUM.get())
                .define('T', Items.NETHERITE_INGOT)
                .define('R', Items.REDSTONE_BLOCK)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT),has(Items.NETHERITE_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALUMINIUM_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.ALUMINIUM.get())
                .unlockedBy(getHasName(ModItems.ALUMINIUM.get()), has(ModItems.ALUMINIUM.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TITANIUM_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.TITANIUM.get())
                .unlockedBy(getHasName(ModItems.TITANIUM.get()), has(ModItems.TITANIUM.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_ALUMINIUM_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.RAW_ALUMINIUM.get())
                .unlockedBy(getHasName(ModItems.RAW_ALUMINIUM.get()), has(ModItems.RAW_ALUMINIUM.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_TITANIUM_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.RAW_TITANIUM.get())
                .unlockedBy(getHasName(ModItems.RAW_TITANIUM.get()), has(ModItems.RAW_TITANIUM.get()))
                .save(consumer);

        armorBoots(ModItems.ALUMINIUM.get(),ModItems.ALUMINIUM_BOOTS.get(),consumer);
        armorLeggings(ModItems.ALUMINIUM.get(),ModItems.ALUMINIUM_LEGGINGS.get(),consumer);
        armorChestplate(ModItems.ALUMINIUM.get(),ModItems.ALUMINIUM_CHESTPLATE.get(),consumer);
        armorHelmet(ModItems.ALUMINIUM.get(),ModItems.ALUMINIUM_HELMET.get(),consumer);

        armorBoots(ModItems.TITANIUM.get(),ModItems.TITANIUM_BOOTS.get(),consumer);
        armorLeggings(ModItems.TITANIUM.get(),ModItems.TITANIUM_LEGGINGS.get(),consumer);
        armorChestplate(ModItems.TITANIUM.get(),ModItems.TITANIUM_CHESTPLATE.get(),consumer);
        armorHelmet(ModItems.TITANIUM.get(),ModItems.TITANIUM_HELMET.get(),consumer);

        armorBoots(ModItems.ENERGIZED_ALUMINIUM.get(),ModItems.ENERGIZED_ALUMINIUM_BOOTS.get(),consumer);
        armorLeggings(ModItems.ENERGIZED_ALUMINIUM.get(),ModItems.ENERGIZED_ALUMINIUM_LEGGINGS.get(),consumer);
        armorChestplate(ModItems.ENERGIZED_ALUMINIUM.get(),ModItems.ENERGIZED_ALUMINIUM_CHESTPLATE.get(),consumer);
        armorHelmet(ModItems.ENERGIZED_ALUMINIUM.get(),ModItems.ENERGIZED_ALUMINIUM_HELMET.get(),consumer);

        armorBoots(ModItems.ENERGIZED_TITANIUM.get(),ModItems.ENERGIZED_TITANIUM_BOOTS.get(),consumer);
        armorLeggings(ModItems.ENERGIZED_TITANIUM.get(),ModItems.ENERGIZED_TITANIUM_LEGGINGS.get(),consumer);
        armorChestplate(ModItems.ENERGIZED_TITANIUM.get(),ModItems.ENERGIZED_TITANIUM_CHESTPLATE.get(),consumer);
        armorHelmet(ModItems.ENERGIZED_TITANIUM.get(),ModItems.ENERGIZED_TITANIUM_HELMET.get(),consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ALUMINIUM.get(), 9)
                .requires(ModBlocks.ALUMINIUM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ALUMINIUM_BLOCK.get()), has(ModBlocks.ALUMINIUM_BLOCK.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TITANIUM.get(), 9)
                .requires(ModBlocks.TITANIUM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.TITANIUM_BLOCK.get()), has(ModBlocks.TITANIUM_BLOCK.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ALUMINIUM.get(), 9)
                .requires(ModBlocks.RAW_ALUMINIUM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RAW_ALUMINIUM_BLOCK.get()), has(ModBlocks.RAW_ALUMINIUM_BLOCK.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_TITANIUM.get(), 9)
                .requires(ModBlocks.RAW_TITANIUM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RAW_TITANIUM_BLOCK.get()), has(ModBlocks.RAW_TITANIUM_BLOCK.get()))
                .save(consumer);

        armorBoots(ModItems.TALIUM.get(),ModItems.TALIUM_BOOTS.get(),consumer);
        armorLeggings(ModItems.TALIUM.get(),ModItems.TALIUM_LEGGINGS.get(),consumer);
        armorChestplate(ModItems.TALIUM.get(),ModItems.TALIUM_CHESTPLATE.get(),consumer);
        armorHelmet(ModItems.TALIUM.get(),ModItems.TALIUM_HELMET.get(),consumer);
    }

    protected static void armorBoots(Item input, Item output, Consumer<FinishedRecipe> consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output)
                .pattern("S S")
                .pattern("S S")
                .define('S',input)
                .unlockedBy(getHasName(input), has(input))
                .save(consumer);
    }

    protected static void armorHelmet(Item input,Item output, Consumer<FinishedRecipe> consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,output)
                .pattern("SSS")
                .pattern("S S")
                .define('S',input)
                .unlockedBy(getHasName(input), has(input))
                .save(consumer);
    }

    protected static void armorLeggings(Item input,Item output, Consumer<FinishedRecipe> consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,output)
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .define('S',input)
                .unlockedBy(getHasName(input), has(input))
                .save(consumer);
    }

    protected static void armorChestplate(Item input,Item output, Consumer<FinishedRecipe> consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,output)
                .pattern("S S")
                .pattern("SSS")
                .pattern("SSS")
                .define('S',input)
                .unlockedBy(getHasName(input), has(input))
                .save(consumer);
    }
    protected static void oreSmelting(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience, int pCookingTIme, @NotNull String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience, int pCookingTime, @NotNull String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer, @NotNull RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience, int pCookingTime, @NotNull String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer, World_Depth.MODID+":" +getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
