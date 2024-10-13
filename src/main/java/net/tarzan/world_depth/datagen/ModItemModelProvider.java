package net.tarzan.world_depth.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.block.ModBlocks;
import net.tarzan.world_depth.item.ModItems;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, World_Depth.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.ALUMINIUM);
        simpleItem(ModItems.ENERGIZED_ALUMINIUM);
        simpleItem(ModItems.CHARGED_REDSTONE);
        simpleItem(ModItems.RAW_ALUMINIUM);
        simpleItem(ModItems.RAW_TITANIUM);
        simpleItem(ModItems.TITANIUM);
        simpleItem(ModItems.ENERGIZED_TITANIUM);
        simpleItem(ModItems.TANIUM);

        evenSimplerBlockItem(ModBlocks.FERYL_STAIRS);
        evenSimplerBlockItem(ModBlocks.FERYL_SLAB);
        evenSimplerBlockItem(ModBlocks.FERYL_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.SOAP_STONE_STAIRS);
        evenSimplerBlockItem(ModBlocks.SOAP_STONE_SLAB);
        evenSimplerBlockItem(ModBlocks.SOAP_STONE_PRESSURE_PLATE);

        buttonItem(ModBlocks.FERYL_BUTTON, ModBlocks.FERYL_STONE);
        wallItem(ModBlocks.FERYL_WALL, ModBlocks.FERYL_STONE);
        buttonItem(ModBlocks.SOAP_STONE_BUTTON, ModBlocks.SOAP_STONE);
        wallItem(ModBlocks.SOAP_STONE_WALL, ModBlocks.SOAP_STONE);

        trimmedArmorItem(ModItems.ALUMINIUM_HELMET);
        trimmedArmorItem(ModItems.ALUMINIUM_CHESTPLATE);
        trimmedArmorItem(ModItems.ALUMINIUM_LEGGINGS);
        trimmedArmorItem(ModItems.ALUMINIUM_BOOTS);
        trimmedArmorItem(ModItems.ENERGIZED_ALUMINIUM_HELMET);
        trimmedArmorItem(ModItems.ENERGIZED_ALUMINIUM_CHESTPLATE);
        trimmedArmorItem(ModItems.ENERGIZED_ALUMINIUM_LEGGINGS);
        trimmedArmorItem(ModItems.ENERGIZED_ALUMINIUM_BOOTS);

        trimmedArmorItem(ModItems.TITANIUM_HELMET);
        trimmedArmorItem(ModItems.TITANIUM_CHESTPLATE);
        trimmedArmorItem(ModItems.TITANIUM_LEGGINGS);
        trimmedArmorItem(ModItems.TITANIUM_BOOTS);
        trimmedArmorItem(ModItems.ENERGIZED_TITANIUM_HELMET);
        trimmedArmorItem(ModItems.ENERGIZED_TITANIUM_CHESTPLATE);
        trimmedArmorItem(ModItems.ENERGIZED_TITANIUM_LEGGINGS);
        trimmedArmorItem(ModItems.ENERGIZED_TITANIUM_BOOTS);

        trimmedArmorItem(ModItems.TANIUM_HELMET);
        trimmedArmorItem(ModItems.TANIUM_CHESTPLATE);
        trimmedArmorItem(ModItems.TANIUM_LEGGINGS);
        trimmedArmorItem(ModItems.TANIUM_BOOTS);
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = World_Depth.MODID;

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(World_Depth.MODID,"item/"+item.getId().getPath()));
    }
    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(World_Depth.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(World_Depth.MODID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }
    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(World_Depth.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(World_Depth.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(World_Depth.MODID,"item/" + item.getId().getPath()));
    }
}
