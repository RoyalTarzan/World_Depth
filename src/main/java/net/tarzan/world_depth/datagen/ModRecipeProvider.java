package net.tarzan.world_depth.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.block.ModBlocks;
import net.tarzan.world_depth.datagen.recipe_builders.EnergizedRecipeBuilder;
import net.tarzan.world_depth.item.ModItems;
import net.tarzan.world_depth.materials.CustomMaterial;
import net.tarzan.world_depth.materials.CustomMaterials;
import net.tarzan.world_depth.recipe.ModRecipes;
import org.jetbrains.annotations.NotNull;

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

        blockToSingleAndReverse(ModItems.TALIUM.get(),ModBlocks.TALIUM_BLOCK.get(),consumer);
        blockToSingleAndReverse(ModItems.ALUMINIUM.get(), ModBlocks.ALUMINIUM_BLOCK.get(), consumer);
        blockToSingleAndReverse(ModItems.RAW_ALUMINIUM.get(), ModBlocks.RAW_ALUMINIUM_BLOCK.get(), consumer);
        blockToSingleAndReverse(ModItems.TITANIUM.get(), ModBlocks.TITANIUM_BLOCK.get(), consumer);
        blockToSingleAndReverse(ModItems.RAW_TITANIUM.get(), ModBlocks.RAW_TITANIUM_BLOCK.get(), consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.ENERGIZER.get())
                .pattern("STS")
                .pattern(" S ")
                .pattern("RRR")
                .define('S', ModItems.TITANIUM.get())
                .define('T', Items.NETHERITE_INGOT)
                .define('R', Items.REDSTONE_BLOCK)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT),has(Items.NETHERITE_INGOT))
                .save(consumer);

        fullArmor(ModItems.ALUMINIUM.get(),ModItems.ALUMINIUM_HELMET.get(),ModItems.ALUMINIUM_CHESTPLATE.get(),
                ModItems.ALUMINIUM_LEGGINGS.get(),ModItems.ALUMINIUM_BOOTS.get(),consumer);
        fullArmor(ModItems.TITANIUM.get(),ModItems.TITANIUM_HELMET.get(),ModItems.TITANIUM_CHESTPLATE.get(),
                ModItems.TITANIUM_LEGGINGS.get(),ModItems.TITANIUM_BOOTS.get(),consumer);
        fullArmor(ModItems.ENERGIZED_ALUMINIUM.get(),ModItems.ENERGIZED_ALUMINIUM_HELMET.get(),ModItems.ENERGIZED_ALUMINIUM_CHESTPLATE.get(),
                ModItems.ENERGIZED_ALUMINIUM_LEGGINGS.get(),ModItems.ENERGIZED_ALUMINIUM_BOOTS.get(),consumer);
        fullArmor(ModItems.ENERGIZED_TITANIUM.get(),ModItems.ENERGIZED_TITANIUM_HELMET.get(),ModItems.ENERGIZED_TITANIUM_CHESTPLATE.get(),
                ModItems.ENERGIZED_TITANIUM_LEGGINGS.get(),ModItems.ENERGIZED_TITANIUM_BOOTS.get(),consumer);
        fullArmor(ModItems.TALIUM.get(), ModItems.TALIUM_HELMET.get(),ModItems.TALIUM_CHESTPLATE.get(),
                ModItems.TALIUM_LEGGINGS.get(),ModItems.TALIUM_BOOTS.get(),consumer);

        toolSet(ModItems.TITANIUM.get(), ModItems.TITANIUM_SHOVEL.get(),ModItems.TITANIUM_SWORD.get(),
                ModItems.TITANIUM_AXE.get(),ModItems.TITANIUM_PICKAXE.get(),ModItems.TITANIUM_HOE.get(),consumer);
        toolSet(ModItems.ALUMINIUM.get(), ModItems.ALUMINIUM_SHOVEL.get(),ModItems.ALUMINIUM_SWORD.get(),
                ModItems.ALUMINIUM_AXE.get(),ModItems.ALUMINIUM_PICKAXE.get(),ModItems.ALUMINIUM_HOE.get(),consumer);

        Ingredient[] ingredients=new Ingredient[]{Ingredient.of(ModItems.CHARGED_REDSTONE.get()),Ingredient.of(Items.REDSTONE),Ingredient.of(ModItems.TALIUM.get()),Ingredient.of(ModItems.ALUMINIUM.get()),Ingredient.of(ModItems.TITANIUM.get())};
        energizing(Items.GOLDEN_CARROT, ingredients,5,4,"golden_carrot",consumer,ModItems.CHARGED_REDSTONE.get());

        for (CustomMaterial material: CustomMaterials.getAddedMaterials()){
            material.registerRecipes(consumer);
        }
    }

    public static void toolSet(ItemLike material,ItemLike shovel,ItemLike sword,ItemLike axe,ItemLike pickaxe,ItemLike hoe, Consumer<FinishedRecipe> consumer){
        shovel(material,shovel, consumer);
        axe(material,axe, consumer);
        pickaxe(material,pickaxe, consumer);
        sword(material,sword, consumer);
        hoe(material,hoe, consumer);
    }

    protected static void sword(ItemLike input, ItemLike output, Consumer<FinishedRecipe> consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,output)
                .pattern("S")
                .pattern("S")
                .pattern("T")
                .define('T',Items.STICK)
                .define('S', input)
                .unlockedBy(getHasName(input), has(input))
                .save(consumer);

    }

    protected static void hoe(ItemLike input, ItemLike output, Consumer<FinishedRecipe> consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,output)
                .pattern("SS")
                .pattern("T ")
                .pattern("T ")
                .define('T',Items.STICK)
                .define('S', input)
                .unlockedBy(getHasName(input), has(input))
                .save(consumer);

    }

    protected static void pickaxe(ItemLike input, ItemLike output, Consumer<FinishedRecipe> consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,output)
                .pattern("SSS")
                .pattern(" T ")
                .pattern(" T ")
                .define('T',Items.STICK)
                .define('S', input)
                .unlockedBy(getHasName(input), has(input))
                .save(consumer);

    }

    protected static void axe(ItemLike input, ItemLike output, Consumer<FinishedRecipe> consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,output)
                .pattern("SS")
                .pattern("TS")
                .pattern("T ")
                .define('T',Items.STICK)
                .define('S', input)
                .unlockedBy(getHasName(input), has(input))
                .save(consumer);

    }

    protected static void shovel(ItemLike input, ItemLike output, Consumer<FinishedRecipe> consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,output)
                .pattern("S")
                .pattern("T")
                .pattern("T")
                .define('T',Items.STICK)
                .define('S', input)
                .unlockedBy(getHasName(input), has(input))
                .save(consumer);
    }

    public static void blockToSingleAndReverse(ItemLike item, ItemLike block, Consumer<FinishedRecipe> consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, block)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', item)
                .unlockedBy(getHasName(item), has(item))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,item, 9)
                .requires(block)
                .unlockedBy(getHasName(block), has(block))
                .save(consumer);
    }

    public static void fullArmor(Item material, Item helmet, Item chestplate, Item leggings, Item boots, Consumer<FinishedRecipe> consumer){
        armorBoots(material,boots,consumer);
        armorChestplate(material,chestplate,consumer);
        armorHelmet(material,helmet,consumer);
        armorLeggings(material,leggings,consumer);
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

        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer, World_Depth.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }

    public static void energizing(Item result, Ingredient[] ingredients, int redstoneNeeded, int chargedRedstoneNeeded,String group,Consumer<FinishedRecipe> consumer,ItemLike unlockBy){
        EnergizedRecipeBuilder.create(RecipeCategory.MISC,
                result, ingredients, ModRecipes.ENERGIZER_SERIALIZER.get(), redstoneNeeded, chargedRedstoneNeeded).group(group).unlockedBy(getHasName(unlockBy),has(unlockBy))
                .save(consumer, World_Depth.MODID+":"+getItemName(result)+"_from_energizing");
    }
    public static void energizing(CustomMaterial result, Ingredient[] ingredients, int redstoneNeeded, int chargedRedstoneNeeded,String group,Consumer<FinishedRecipe> consumer,ItemLike unlockBy){
        EnergizedRecipeBuilder.create(RecipeCategory.MISC,
                result, ingredients, ModRecipes.ENERGIZER_SERIALIZER.get(), redstoneNeeded, chargedRedstoneNeeded).group(group).unlockedBy(getHasName(unlockBy),has(unlockBy))
                .save(consumer, new ResourceLocation(World_Depth.MODID+":"+result.getName()+"_from_energizing"));
    }
    public static void energizing(Item result, CustomMaterial[] ingredients, int redstoneNeeded, int chargedRedstoneNeeded,String group,Consumer<FinishedRecipe> consumer,ItemLike unlockBy){
        EnergizedRecipeBuilder.create(RecipeCategory.MISC,
                result, ingredients, ModRecipes.ENERGIZER_SERIALIZER.get(), redstoneNeeded, chargedRedstoneNeeded).group(group).unlockedBy(getHasName(unlockBy),has(unlockBy))
                .save(consumer, World_Depth.MODID+":"+getItemName(result)+"_from_energizing");
    }
    public static void energizing(CustomMaterial result, CustomMaterial[] ingredients, int redstoneNeeded, int chargedRedstoneNeeded,String group,Consumer<FinishedRecipe> consumer,ItemLike unlockBy){
        EnergizedRecipeBuilder.create(RecipeCategory.MISC,
                result, ingredients, ModRecipes.ENERGIZER_SERIALIZER.get(), redstoneNeeded, chargedRedstoneNeeded).group(group).unlockedBy(getHasName(unlockBy),has(unlockBy))
                .save(consumer, new ResourceLocation(World_Depth.MODID+":"+result.getName()+"_from_energizing"));
    }
}
