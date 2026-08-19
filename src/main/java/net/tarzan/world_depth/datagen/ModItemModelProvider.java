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
import net.tarzan.world_depth.materials.CustomMaterial;
import net.tarzan.world_depth.materials.CustomMaterials;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        simpleItem(ModItems.TALIUM);
        simpleItem(ModItems.WORLD_GEM);
        simpleItem(ModItems.LIGHT_GEM);

        evenSimplerBlockItem(ModBlocks.FERYL_STAIRS);
        evenSimplerBlockItem(ModBlocks.FERYL_SLAB);
        evenSimplerBlockItem(ModBlocks.FERYL_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.COBBLED_FERYL_STAIRS);
        evenSimplerBlockItem(ModBlocks.COBBLED_FERYL_SLAB);
        evenSimplerBlockItem(ModBlocks.COBBLED_FERYL_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.SOAP_STONE_STAIRS);
        evenSimplerBlockItem(ModBlocks.SOAP_STONE_SLAB);
        evenSimplerBlockItem(ModBlocks.SOAP_STONE_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.COBBLED_SOAP_STONE_STAIRS);
        evenSimplerBlockItem(ModBlocks.COBBLED_SOAP_STONE_SLAB);
        evenSimplerBlockItem(ModBlocks.COBBLED_SOAP_STONE_PRESSURE_PLATE);

        buttonItem(ModBlocks.FERYL_BUTTON, ModBlocks.FERYL_STONE);
        wallItem(ModBlocks.FERYL_WALL, ModBlocks.FERYL_STONE);
        buttonItem(ModBlocks.COBBLED_FERYL_BUTTON, ModBlocks.COBBLED_FERYL_STONE);
        wallItem(ModBlocks.COBBLED_FERYL_WALL, ModBlocks.COBBLED_FERYL_STONE);
        buttonItem(ModBlocks.SOAP_STONE_BUTTON, ModBlocks.SOAP_STONE);
        wallItem(ModBlocks.SOAP_STONE_WALL, ModBlocks.SOAP_STONE);
        buttonItem(ModBlocks.COBBLED_SOAP_STONE_BUTTON, ModBlocks.COBBLED_SOAP_STONE);
        wallItem(ModBlocks.COBBLED_SOAP_STONE_WALL, ModBlocks.COBBLED_SOAP_STONE);

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

        trimmedArmorItem(ModItems.TALIUM_HELMET);
        trimmedArmorItem(ModItems.TALIUM_CHESTPLATE);
        trimmedArmorItem(ModItems.TALIUM_LEGGINGS);
        trimmedArmorItem(ModItems.TALIUM_BOOTS);

        handheldItem(ModItems.ALUMINIUM_AXE);
        handheldItem(ModItems.ALUMINIUM_HOE);
        handheldItem(ModItems.ALUMINIUM_SWORD);
        handheldItem(ModItems.ALUMINIUM_SHOVEL);
        handheldItem(ModItems.ALUMINIUM_PICKAXE);

        handheldItem(ModItems.TITANIUM_AXE);
        handheldItem(ModItems.TITANIUM_HOE);
        handheldItem(ModItems.TITANIUM_SWORD);
        handheldItem(ModItems.TITANIUM_SHOVEL);
        handheldItem(ModItems.TITANIUM_PICKAXE);

        saplingItem(ModBlocks.STOOK_SAPLING);

        for (CustomMaterial material: CustomMaterials.getAddedMaterials()) {
            if (!material.create){continue;}
            customMaterialTextureGeneration(material.getName(),material.getColor());
            simpleItem(material.Item);
            evenSimplerBlockItem(material.Block);
            for (RegistryObject<Item> item:material.Tools){
                handheldItem(item);
            }
            for (RegistryObject<Item> item: material.Armour){
                trimmedArmorItem(item);
            }
        }
    }

    public void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = World_Depth.MODID;

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {

                float trimValue = value;

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

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(World_Depth.MODID,"block/" + item.getId().getPath()));
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

    public ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(World_Depth.MODID,"item/" + item.getId().getPath()));
    }

    private void customMaterialTextureGeneration(String name, Color colour){
        try {
            String srcItemDir = "C:\\Users\\royal\\zelf gemaakte mods\\forge-test mod\\src\\main\\resources\\assets\\world_depth\\textures\\item", srcBlockDir = "C:\\Users\\royal\\zelf gemaakte mods\\forge-test mod\\src\\main\\resources\\assets\\world_depth\\textures\\block",
                    srcArmourDir="C:\\Users\\royal\\zelf gemaakte mods\\forge-test mod\\src\\main\\resources\\assets\\world_depth\\textures\\models\\armor";

            BufferedImage ingot =ImageIO.read(new File(srcItemDir,"aluminium.png")),
                    block =ImageIO.read(new File(srcBlockDir, "aluminium_block.png")),
                    sword =ImageIO.read(new File(srcItemDir, "aluminium_sword.png")),
                    axe =ImageIO.read(new File(srcItemDir, "aluminium_axe.png")),
                    pickaxe =ImageIO.read(new File(srcItemDir, "aluminium_pickaxe.png")),
                    hoe = ImageIO.read(new File(srcItemDir, "aluminium_hoe.png")),
                    shovel =ImageIO.read(new File(srcItemDir, "aluminium_shovel.png")),
                    helmet = ImageIO.read(new File(srcItemDir, "aluminium_helmet.png")),
                    chestplate = ImageIO.read(new File(srcItemDir, "aluminium_chestplate.png")),
                    leggings = ImageIO.read(new File(srcItemDir, "aluminium_leggings.png")),
                    boots = ImageIO.read(new File(srcItemDir, "aluminium_boots.png")),
                    armourLayer1=ImageIO.read(new File(srcArmourDir, "aluminium_layer_1.png")),
                    armourLayer2=ImageIO.read(new File(srcArmourDir, "aluminium_layer_2.png"));
            ArrayList< BufferedImage> imga=new ArrayList<>();
            if (!new File(srcItemDir,name+".png").exists()){imga.add(ingot);}else {imga.add(null);}
            if (!new File(srcBlockDir,name+"_block.png").exists()){imga.add(block);}else {imga.add(null);}
            if (!new File(srcItemDir,name+"_sword.png").exists()){imga.add(sword);}else {imga.add(null);}
            if (!new File(srcItemDir,name+"_axe.png").exists()){imga.add(axe);}else {imga.add(null);}
            if (!new File(srcItemDir,name+"_pickaxe.png").exists()){imga.add(pickaxe);}else {imga.add(null);}
            if (!new File(srcItemDir,name+"_hoe.png").exists()){imga.add(hoe);}else {imga.add(null);}
            if (!new File(srcItemDir,name+"_shovel.png").exists()){imga.add(shovel);}else {imga.add(null);}
            if (!new File(srcItemDir,name+"_helmet.png").exists()){imga.add(helmet);}else {imga.add(null);}
            if (!new File(srcItemDir,name+"_chestplate.png").exists()){imga.add(chestplate);}else {imga.add(null);}
            if (!new File(srcItemDir,name+"_leggings.png").exists()){imga.add(leggings);}else {imga.add(null);}
            if (!new File(srcItemDir,name+"_boots.png").exists()){imga.add(boots);}else {imga.add(null);}
            if (!new File(srcArmourDir,name+"_layer_1.png").exists()){imga.add(armourLayer1);}else {imga.add(null);}
            if (!new File(srcArmourDir,name+"_layer_2.png").exists()){imga.add(armourLayer2);}else {imga.add(null);}

            ColorConvertOp rgb=new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_sRGB),null);
            for (int i=0;i<imga.size();i++){
                if (imga.get(i)==null){continue;}
                int[] pixels= imga.get(i).getRGB(0,0, imga.get(i).getWidth(), imga.get(i).getHeight(), (int[]) null,0, imga.get(i).getWidth());
                imga.set(i, rgb.filter(imga.get(i), null));
                for (int j=0;j<pixels.length;j++){
                    if (pixels[j]==0){continue;}
                    Color pixelrgb=new Color(pixels[j]);
                    int red=pixelrgb.getRed()*colour.getRed()/255;
                    int green=pixelrgb.getGreen()*colour.getGreen()/255;
                    int blue=pixelrgb.getBlue()*colour.getBlue()/255;
                    pixelrgb=new Color(red,green,blue);
                    pixels[j]=pixelrgb.getRGB();
                }
                imga.get(i).setRGB(0,0, imga.get(i).getWidth(), imga.get(i).getHeight(),pixels,0, imga.get(i).getWidth());
            }

            if(imga.get(0)!=null){ImageIO.write(imga.get(0), "png", new File(srcItemDir, name+".png"));}
            if(imga.get(1)!=null){ImageIO.write(imga.get(1), "png", new File(srcBlockDir, name + "_block.png"));}
            if(imga.get(2)!=null){ImageIO.write(imga.get(2), "png", new File(srcItemDir, name + "_sword.png"));}
            if(imga.get(3)!=null){ImageIO.write(imga.get(3), "png", new File(srcItemDir, name + "_axe.png"));}
            if(imga.get(4)!=null){ImageIO.write(imga.get(4), "png", new File(srcItemDir, name + "_pickaxe.png"));}
            if(imga.get(5)!=null){ImageIO.write(imga.get(5), "png", new File(srcItemDir, name + "_hoe.png"));}
            if(imga.get(6)!=null){ImageIO.write(imga.get(6), "png", new File(srcItemDir, name + "_shovel.png"));}
            if(imga.get(7)!=null){ImageIO.write(imga.get(7), "png", new File(srcItemDir, name + "_helmet.png"));}
            if(imga.get(8)!=null){ImageIO.write(imga.get(8), "png", new File(srcItemDir, name + "_chestplate.png"));}
            if(imga.get(9)!=null){ImageIO.write(imga.get(9), "png", new File(srcItemDir, name + "_leggings.png"));}
            if(imga.get(10)!=null){ImageIO.write(imga.get(10), "png", new File(srcItemDir, name + "_boots.png"));}
            if(imga.get(11)!=null){ImageIO.write(imga.get(11), "png", new File(srcArmourDir, name + "_layer_1.png"));}
            if(imga.get(12)!=null){ImageIO.write(imga.get(12), "png", new File(srcArmourDir, name + "_layer_2.png"));}
            }catch (IOException ignored){}
    }
}
