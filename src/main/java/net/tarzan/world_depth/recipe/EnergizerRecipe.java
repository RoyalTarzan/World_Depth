package net.tarzan.world_depth.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.tarzan.world_depth.World_Depth;
import org.jetbrains.annotations.Nullable;

public class EnergizerRecipe implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;
    private final int redstoneNeeded;
    private final int chargedRedstoneNeeded;

    public EnergizerRecipe(NonNullList<Ingredient> inputItems, ItemStack output, int redstoneNeeded, int chargedRedstoneNeeded, ResourceLocation id) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
        this.redstoneNeeded = redstoneNeeded;
        this.chargedRedstoneNeeded = chargedRedstoneNeeded;
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        if (level.isClientSide()){
            return false;
        }

        return inputItems.get(0).test(simpleContainer.getItem(0)) &&
                inputItems.get(1).test(simpleContainer.getItem(1)) &&
                inputItems.get(2).test(simpleContainer.getItem(2)) &&
                inputItems.get(3).test(simpleContainer.getItem(3)) &&
                inputItems.get(4).test(simpleContainer.getItem(4));
    }

    @Override
    public NonNullList<Ingredient> getIngredients(){
        return inputItems;
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return output.copy();
    }

    public int getRedstoneNeeded(){
        return redstoneNeeded;
    }

    public int getChargedRedstoneNeeded(){
        return chargedRedstoneNeeded;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<EnergizerRecipe>{
        public static final Type INSTANCE=new Type();
        public static final String ID="energizing";
    }

    public static class Serializer implements RecipeSerializer<EnergizerRecipe>{
        public static final Serializer INSTANCE=new Serializer();
        public static final ResourceLocation ID=new ResourceLocation(World_Depth.MODID,"energizing");

        @Override
        public EnergizerRecipe fromJson(ResourceLocation pRecipeId, JsonObject jsonObject) {
            ItemStack output= ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject,"output"));

            JsonArray ingredients=GsonHelper.getAsJsonArray(jsonObject, "ingredients");
            NonNullList<Ingredient> inputs=NonNullList.withSize(5,Ingredient.EMPTY);

            for (int i=0;i<inputs.size();i++){
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            int redstoneNeeded=GsonHelper.getAsInt(jsonObject, "redstone_needed");
            int chargedRedstoneNeeded=GsonHelper.getAsInt(jsonObject, "charged_redstone_needed");

            return new EnergizerRecipe(inputs, output, redstoneNeeded, chargedRedstoneNeeded, pRecipeId);
        }

        @Override
        public @Nullable EnergizerRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            NonNullList<Ingredient> inputs=NonNullList.withSize(friendlyByteBuf.readInt(),Ingredient.EMPTY);

            for (int i=0;i<inputs.size();i++){
                inputs.set(i, Ingredient.fromNetwork(friendlyByteBuf));
            }

            ItemStack output=friendlyByteBuf.readItem();
            int redstoneNeeded=friendlyByteBuf.readInt();
            int chargedRedstoneNeeded=friendlyByteBuf.readInt();
            return new EnergizerRecipe(inputs, output, redstoneNeeded, chargedRedstoneNeeded, resourceLocation);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, EnergizerRecipe energizerRecipe) {
            friendlyByteBuf.writeInt(energizerRecipe.inputItems.size());

            for(Ingredient ingredient:energizerRecipe.getIngredients()){
                ingredient.toNetwork(friendlyByteBuf);
            }

            friendlyByteBuf.writeItemStack(energizerRecipe.getResultItem(null),false);

            friendlyByteBuf.writeInt(energizerRecipe.redstoneNeeded);
            friendlyByteBuf.writeInt(energizerRecipe.chargedRedstoneNeeded);
        }
    }
}
