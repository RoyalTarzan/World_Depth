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
import net.tarzan.world_depth.item.ModItems;
import net.tarzan.world_depth.recipe.EnergizerRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.Consumer;

public class EnergizedRecipeBuilder implements RecipeBuilder {
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    private Item result=null;
    private Ingredient[] ingredients = new Ingredient[0];
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

    public static EnergizedRecipeBuilder create(RecipeCategory pCategory, Item pResult, Ingredient[] pIngredient, RecipeSerializer<EnergizerRecipe> pSerializer, int redstoneNeeded, int chargedRedstoneNeeded){
        return new EnergizedRecipeBuilder( pCategory,  pResult,  pIngredient,  pSerializer,  redstoneNeeded,  chargedRedstoneNeeded);
    }

    @Override
    public @NotNull EnergizedRecipeBuilder unlockedBy(@NotNull String criterion, @NotNull CriterionTriggerInstance criterionTrigger) {
        this.advancement.addCriterion(criterion,criterionTrigger);
        return this;
    }

    @Override
    public @NotNull EnergizedRecipeBuilder group(@Nullable String s) {
        this.group=s;
        return this;
    }

    @Override
    public @NotNull Item getResult() {
            return this.result;
    }

    @Override
    public void save(@NotNull Consumer<FinishedRecipe> consumer, @NotNull ResourceLocation pRecipeId) {
        this.ensureValid(pRecipeId);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId)).rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
            consumer.accept(new Result(pRecipeId, this.group == null ? "" : this.group, this.ingredients, this.result, this.advancement, pRecipeId.withPrefix("recipes/" + this.category.getFolderName() + "/"), this.serializer, this.redstoneNeeded, this.chargedRedstoneNeeded));
    }

    private void ensureValid(ResourceLocation pId) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    static class Result implements FinishedRecipe{
        private final ResourceLocation id;
        private final String group;
        private final Ingredient[] ingredients;
        private final Item result;
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

        @Override
        public void serializeRecipeData(@NotNull JsonObject pJson) {
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
            }
            pJson.addProperty("redstone_needed",this.redstoneNeeded);
            pJson.addProperty("charged_redstone_needed",this.chargedRedstoneNeeded);
            JsonObject output=new JsonObject();
            if (this.result!=null) {
                output.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result).toString());
            }
            output.addProperty("count",1);
            pJson.add("output", output);
        }

        @Override
        public @NotNull ResourceLocation getId() {
            return this.id;
        }

        @Override
        public @NotNull RecipeSerializer<?> getType() {
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
