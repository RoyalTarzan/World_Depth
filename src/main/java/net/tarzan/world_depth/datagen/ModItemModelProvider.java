package net.tarzan.world_depth.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.block.ModBlocks;
import net.tarzan.world_depth.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, World_Depth.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.ALUMINIUM);
        simpleItem(ModItems.CHARGED_REDSTONE);
        simpleItem(ModItems.RAW_ALUMINIUM);
        simpleItem(ModItems.RAW_TITANIUM);
        simpleItem(ModItems.TITANIUM);

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
