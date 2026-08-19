package net.tarzan.world_depth.datagen.recipe_builders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.item.ModItems;
import net.tarzan.world_depth.materials.CustomMaterial;
import net.tarzan.world_depth.recipe.EnergizerRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.Consumer;

public class EnergizedRecipeBuilder implements RecipeBuilder {
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    private Item result=null;
    private CustomMaterial resultMaterial=null;
    private Ingredient[] ingredients = new Ingredient[0];
    private CustomMaterial[] ingredientsMaterial = new CustomMaterial[0];
    private final RecipeCategory category;
    @Nullable
    private String group;
    private final RecipeSerializer<EnergizerRecipe> serializer;
    private final int redstoneNeeded;
    private final int chargedRedstoneNeeded;

    public EnergizedRecipeBuilder(RecipeCategory pCategory, Item pResult, Ingredient[] pIngredient, RecipeSerializer<EnergizerRecipe> pSerializer, int redstoneNeeded, int chargedRedstoneNeeded) {
        this.category = pCategory;
        this.result = pResult.asItem();
        this.ingredients = pIngredient;
        this.serializer = pSerializer;
        this.redstoneNeeded = redstoneNeeded;
        this.chargedRedstoneNeeded = chargedRedstoneNeeded;
    }
    public EnergizedRecipeBuilder(RecipeCategory pCategory, Item pResult, CustomMaterial[] pIngredient, RecipeSerializer<EnergizerRecipe> pSerializer, int redstoneNeeded, int chargedRedstoneNeeded) {
        this.category = pCategory;
        this.result = pResult.asItem();
        this.ingredientsMaterial = pIngredient;
        this.serializer = pSerializer;
        this.redstoneNeeded = redstoneNeeded;
        this.chargedRedstoneNeeded = chargedRedstoneNeeded;
    }
    public EnergizedRecipeBuilder(RecipeCategory pCategory, CustomMaterial pResult, Ingredient[] pIngredient, RecipeSerializer<EnergizerRecipe> pSerializer, int redstoneNeeded, int chargedRedstoneNeeded) {
        this.category = pCategory;
        this.resultMaterial = pResult;
        this.ingredients = pIngredient;
        this.serializer = pSerializer;
        this.redstoneNeeded = redstoneNeeded;
        this.chargedRedstoneNeeded = chargedRedstoneNeeded;
    }
    public EnergizedRecipeBuilder(RecipeCategory pCategory, CustomMaterial pResult, CustomMaterial[] pIngredient, RecipeSerializer<EnergizerRecipe> pSerializer, int redstoneNeeded, int chargedRedstoneNeeded) {
        this.category = pCategory;
        this.resultMaterial = pResult;
        this.ingredientsMaterial = pIngredient;
        this.serializer = pSerializer;
        this.redstoneNeeded = redstoneNeeded;
        this.chargedRedstoneNeeded = chargedRedstoneNeeded;
    }

    public static EnergizedRecipeBuilder create(RecipeCategory pCategory, Item pResult, Ingredient[] pIngredient, RecipeSerializer<EnergizerRecipe> pSerializer, int redstoneNeeded, int chargedRedstoneNeeded){
        return new EnergizedRecipeBuilder( pCategory,  pResult,  pIngredient,  pSerializer,  redstoneNeeded,  chargedRedstoneNeeded);
    }
    public static EnergizedRecipeBuilder create(RecipeCategory pCategory, Item pResult, CustomMaterial[] pIngredient, RecipeSerializer<EnergizerRecipe> pSerializer, int redstoneNeeded, int chargedRedstoneNeeded){
        return new EnergizedRecipeBuilder( pCategory,  pResult,  pIngredient,  pSerializer,  redstoneNeeded,  chargedRedstoneNeeded);
    }
    public static EnergizedRecipeBuilder create(RecipeCategory pCategory, CustomMaterial pResult, Ingredient[] pIngredient, RecipeSerializer<EnergizerRecipe> pSerializer, int redstoneNeeded, int chargedRedstoneNeeded){
        return new EnergizedRecipeBuilder( pCategory,  pResult,  pIngredient,  pSerializer,  redstoneNeeded,  chargedRedstoneNeeded);
    }
    public static EnergizedRecipeBuilder create(RecipeCategory pCategory, CustomMaterial pResult, CustomMaterial[] pIngredient, RecipeSerializer<EnergizerRecipe> pSerializer, int redstoneNeeded, int chargedRedstoneNeeded){
        return new EnergizedRecipeBuilder( pCategory,  pResult,  pIngredient,  pSerializer,  redstoneNeeded,  chargedRedstoneNeeded);
    }

    @Override
    public EnergizedRecipeBuilder unlockedBy(String criterion, CriterionTriggerInstance criterionTrigger) {
        this.advancement.addCriterion(criterion,criterionTrigger);
        return this;
    }

    @Override
    public EnergizedRecipeBuilder group(@Nullable String s) {
        this.group=s;
        return this;
    }

    @Override
    public Item getResult() {
            return this.result;
    }

    @Override
    public void save(@NotNull Consumer<FinishedRecipe> consumer, @NotNull ResourceLocation pRecipeId) {
        this.ensureValid(pRecipeId);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId)).rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
        if (Arrays.equals(ingredientsMaterial, new CustomMaterial[0])&&this.result!=null) {
            consumer.accept(new Result(pRecipeId, this.group == null ? "" : this.group, this.ingredients, this.result, this.advancement, pRecipeId.withPrefix("recipes/" + this.category.getFolderName() + "/"), this.serializer, this.redstoneNeeded, this.chargedRedstoneNeeded));
        }else if (this.result!=null&&!Arrays.equals(ingredientsMaterial, new CustomMaterial[0])){
            consumer.accept(new Result(pRecipeId, this.group == null ? "" : this.group, this.ingredientsMaterial, this.result, this.advancement, pRecipeId.withPrefix("recipes/" + this.category.getFolderName() + "/"), this.serializer, this.redstoneNeeded, this.chargedRedstoneNeeded));
        }else if(this.result==null&&!Arrays.equals(ingredients, new Ingredient[0])){
            consumer.accept(new Result(pRecipeId, this.group == null ? "" : this.group, this.ingredients, this.resultMaterial, this.advancement, pRecipeId.withPrefix("recipes/" + this.category.getFolderName() + "/"), this.serializer, this.redstoneNeeded, this.chargedRedstoneNeeded));
        }else {
            consumer.accept(new Result(pRecipeId, this.group == null ? "" : this.group, this.ingredientsMaterial, this.resultMaterial, this.advancement, pRecipeId.withPrefix("recipes/" + this.category.getFolderName() + "/"), this.serializer, this.redstoneNeeded, this.chargedRedstoneNeeded));
        }
    }

    private void ensureValid(ResourceLocation pId) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    static class Result implements FinishedRecipe{
        private final ResourceLocation id;
        private final String group;
        private Ingredient[] ingredients= new Ingredient[0];
        private CustomMaterial[] ingredientsMaterial = new CustomMaterial[0];
        private Item result=null;
        private CustomMaterial resultMaterial=null;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final RecipeSerializer<EnergizerRecipe> serializer;
        private final int redstoneNeeded;
        private final int chargedRedstoneNeeded;

        Result(ResourceLocation id, String group, Ingredient[] ingredients, Item result, Advancement.Builder advancement, ResourceLocation advancementId, RecipeSerializer<EnergizerRecipe> serializer, int redstoneNeeded, int chargedRedstoneNeeded) {
            this.id = id;
            this.group = group;
            this.ingredients = ingredients;
            this.result = result;
            this.advancement = advancement;
            this.advancementId = advancementId;
            this.serializer = serializer;
            this.redstoneNeeded = redstoneNeeded;
            this.chargedRedstoneNeeded = chargedRedstoneNeeded;
        }

        Result(ResourceLocation id, String group, CustomMaterial[] ingredients, Item result, Advancement.Builder advancement, ResourceLocation advancementId, RecipeSerializer<EnergizerRecipe> serializer, int redstoneNeeded, int chargedRedstoneNeeded) {
            this.id = id;
            this.group = group;
            this.ingredientsMaterial = ingredients;
            this.result = result;
            this.advancement = advancement;
            this.advancementId = advancementId;
            this.serializer = serializer;
            this.redstoneNeeded = redstoneNeeded;
            this.chargedRedstoneNeeded = chargedRedstoneNeeded;
        }
        Result(ResourceLocation id, String group, Ingredient[] ingredients, CustomMaterial result, Advancement.Builder advancement, ResourceLocation advancementId, RecipeSerializer<EnergizerRecipe> serializer, int redstoneNeeded, int chargedRedstoneNeeded) {
            this.id = id;
            this.group = group;
            this.ingredients = ingredients;
            this.resultMaterial = result;
            this.advancement = advancement;
            this.advancementId = advancementId;
            this.serializer = serializer;
            this.redstoneNeeded = redstoneNeeded;
            this.chargedRedstoneNeeded = chargedRedstoneNeeded;
        }

        Result(ResourceLocation id, String group, CustomMaterial[] ingredients, CustomMaterial result, Advancement.Builder advancement, ResourceLocation advancementId, RecipeSerializer<EnergizerRecipe> serializer, int redstoneNeeded, int chargedRedstoneNeeded) {
            this.id = id;
            this.group = group;
            this.ingredientsMaterial = ingredients;
            this.resultMaterial = result;
            this.advancement = advancement;
            this.advancementId = advancementId;
            this.serializer = serializer;
            this.redstoneNeeded = redstoneNeeded;
            this.chargedRedstoneNeeded = chargedRedstoneNeeded;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            if (this.group.isEmpty()){
                pJson.addProperty("group",this.group);
            }
            JsonArray ingredients=new JsonArray();
            if (!Arrays.equals(this.ingredients, new Ingredient[0])) {
                for (Ingredient ingredient : this.ingredients) {
                    ingredients.add(ingredient.toJson());
                }
                for (int i=0;ingredients.size()<5;i++){
                    ingredients.add(Ingredient.of(ModItems.CHARGED_REDSTONE.get()).toJson());
                }
                pJson.add("ingredients", ingredients);
            }else {
                ingredients.add(Ingredient.of(ModItems.CHARGED_REDSTONE.get()).toJson());
                JsonObject jsonobject = new JsonObject();
                jsonobject.addProperty("item","world_depth:"+ingredientsMaterial[0].getName());
                ingredients.add(jsonobject);
                ingredients.add(Ingredient.of(ModItems.WORLD_GEM.get()).toJson());
                jsonobject.addProperty("item","world_depth:"+ingredientsMaterial[1].getName());
                ingredients.add(jsonobject);
                for (int i=0;ingredients.size()<5;i++){
                    ingredients.add(Ingredient.of(ModItems.CHARGED_REDSTONE.get()).toJson());
                }
                pJson.add("ingredients", ingredients);
            }
            pJson.addProperty("redstone_needed",this.redstoneNeeded);
            pJson.addProperty("charged_redstone_needed",this.chargedRedstoneNeeded);
            JsonObject output=new JsonObject();
            if (this.result!=null) {
                output.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result).toString());
            }else {
                output.addProperty("item", World_Depth.MODID+":"+this.resultMaterial.getName());
            }
            output.addProperty("count",1);
            pJson.add("output", output);
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return this.serializer;
        }

        @Override
        public @Nullable JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
