package net.tarzan.world_depth.datagen.recipe_builders;

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
import net.tarzan.world_depth.recipe.EnergizerRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class EnergizedRecipeBuilder implements RecipeBuilder {
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    private final Item result;
    private final List<Ingredient> ingredients;
    private final RecipeCategory category;
    @Nullable
    private String group;
    private final RecipeSerializer<EnergizerRecipe> serializer;
    private int redstoneNeeded;
    private int chargedRedstoneNeeded;

    public EnergizedRecipeBuilder(RecipeCategory pCategory, Item pResult, List<Ingredient> pIngredient, RecipeSerializer<EnergizerRecipe> pSerializer, int redstoneNeeded, int chargedRedstoneNeeded) {
        this.category = pCategory;
        this.result = pResult.asItem();
        this.ingredients = pIngredient;
        this.serializer = pSerializer;
        this.redstoneNeeded = redstoneNeeded;
        this.chargedRedstoneNeeded = chargedRedstoneNeeded;
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
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation pRecipeId) {
        this.ensureValid(pRecipeId);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId)).rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
        consumer.accept(new EnergizedRecipeBuilder.Result(pRecipeId, this.group == null ? "" : this.group, this.ingredients , this.result, this.advancement, pRecipeId.withPrefix("recipes/" + this.category.getFolderName() + "/"), this.serializer, this.redstoneNeeded, this.chargedRedstoneNeeded));

    }

    private void ensureValid(ResourceLocation pId) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    static class Result implements FinishedRecipe{
        private final ResourceLocation id;
        private final String group;
        private final List<Ingredient> ingredients;
        private final Item result;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final RecipeSerializer<EnergizerRecipe> serializer;
        private int redstoneNeeded;
        private int chargedRedstoneNeeded;

        Result(ResourceLocation id, String group, List<Ingredient> ingredients, Item result, Advancement.Builder advancement, ResourceLocation advancementId, RecipeSerializer<EnergizerRecipe> serializer, int redstoneNeeded, int chargedRedstoneNeeded) {
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
        public void serializeRecipeData(JsonObject pJson) {
            if (this.group.isEmpty()){
                pJson.addProperty("group",this.group);
            }

            pJson.add("ingredients",this.ingredients.listIterator().next().toJson());
            pJson.addProperty("redstone_needed",this.redstoneNeeded);
            pJson.addProperty("charged_redstone_needed",this.chargedRedstoneNeeded);
            pJson.addProperty("result", BuiltInRegistries.ITEM.getKey(this.result).toString());
        }

        @Override
        public JsonObject serializeRecipe() {
            return FinishedRecipe.super.serializeRecipe();
        }

        @Override
        public ResourceLocation getId() {
            return null;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return null;
        }

        @Override
        public @Nullable JsonObject serializeAdvancement() {
            return null;
        }

        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
