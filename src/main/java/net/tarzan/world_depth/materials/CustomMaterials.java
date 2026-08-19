package net.tarzan.world_depth.materials;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tiers;
import net.tarzan.world_depth.item.ModToolTiers;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomMaterials {
    private static final ArrayList<CustomMaterial> addedMaterials=new ArrayList<>();
    private static final ArrayList<CustomMaterial[]> materialCombinations=new ArrayList<>();
    private static final ArrayList<String> names=new ArrayList<>();

    public static ArrayList<CustomMaterial> getAddedMaterials() {
        return addedMaterials;
    }

    public static void addMaterial(CustomMaterial material){
        addedMaterials.add(material);
    }

    public static void addMaterial(CustomMaterial parent1,CustomMaterial parent2){
        addedMaterials.add(new CustomMaterial(parent1,parent2));
        materialCombinations.add(new CustomMaterial[]{parent1,parent2});
    }

    public static void createTranslations(){
        try {
            Gson gson=new Gson();
            JsonReader reader=new JsonReader(new FileReader("C:\\Users\\royal\\zelf gemaakte mods\\forge-test mod\\src\\main\\resources\\assets\\world_depth\\lang\\en_us.json"));
            JsonObject translations=gson.fromJson(reader,JsonObject.class);
            for (CustomMaterial material:addedMaterials){
                translations.remove("item.world_depth."+material.getName());
                translations.remove("block.world_depth."+material.getName()+"_block");
                translations.remove("item.world_depth."+material.getName()+"_boots");
                translations.remove("item.world_depth."+material.getName()+"_leggings");
                translations.remove("item.world_depth."+material.getName()+"_chestplate");
                translations.remove("item.world_depth."+material.getName()+"_helmet");
                translations.remove("item.world_depth."+material.getName()+"_sword");
                translations.remove("item.world_depth."+material.getName()+"_pickaxe");
                translations.remove("item.world_depth."+material.getName()+"_axe");
                translations.remove("item.world_depth."+material.getName()+"_hoe");
                translations.remove("item.world_depth."+material.getName()+"_shovel");
                translations.addProperty("item.world_depth."+material.getName(),material.getMaterial()+" Ingot");
                translations.addProperty("block.world_depth."+material.getName()+"_block",material.getMaterial()+" Block");
                translations.addProperty("item.world_depth."+material.getName()+"_boots",material.getMaterial()+" Boots");
                translations.addProperty("item.world_depth."+material.getName()+"_leggings",material.getMaterial()+" Leggings");
                translations.addProperty("item.world_depth."+material.getName()+"_chestplate",material.getMaterial()+" Chestplate");
                translations.addProperty("item.world_depth."+material.getName()+"_helmet",material.getMaterial()+" Helmet");
                translations.addProperty("item.world_depth."+material.getName()+"_sword",material.getMaterial()+" Sword");
                translations.addProperty("item.world_depth."+material.getName()+"_pickaxe",material.getMaterial()+" Pickaxe");
                translations.addProperty("item.world_depth."+material.getName()+"_axe",material.getMaterial()+" Axe");
                translations.addProperty("item.world_depth."+material.getName()+"_hoe",material.getMaterial()+" Hoe");
                translations.addProperty("item.world_depth."+material.getName()+"_shovel",material.getMaterial()+" Shovel");
            }
            String toWrite= translations.toString().replace(",",",\n\t").replace("{","{\n\t").replace("}","\n}");
            try (FileWriter fileWriter = new FileWriter("C:\\Users\\royal\\zelf gemaakte mods\\forge-test mod\\src\\main\\resources\\assets\\world_depth\\lang\\en_us.json")) {
                fileWriter.write(toWrite);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerAll(){
        addedMaterials.add( new CustomMaterial("Energized Iron",6,28,5,8,8,new ArrayList<>(List.of(new MobEffect[]{MobEffects.DIG_SPEED,MobEffects.DAMAGE_RESISTANCE})),new Integer[]{2,4},1f,1f,1,Tiers.IRON,new Item[]{Items.IRON_INGOT,Items.IRON_INGOT},new Color(155,155,155),true));
        addedMaterials.add( new CustomMaterial("Energized Gold",5,15,6,20,6,new ArrayList<>(List.of(new MobEffect[]{MobEffects.LUCK,MobEffects.SATURATION,MobEffects.REGENERATION,MobEffects.GLOWING})),new Integer[]{10,4,4,2},1f,1f,1,Tiers.GOLD,new Item[]{Items.GOLD_INGOT,Items.GOLD_INGOT},new Color(237, 244, 134),true));
        addedMaterials.add(new CustomMaterial("Energized Copper",4,20,4,10,7,new ArrayList<>(List.of(new MobEffect[]{MobEffects.NIGHT_VISION,MobEffects.FIRE_RESISTANCE,MobEffects.CONFUSION})),new Integer[]{10,10,10},1f,1f,1,Tiers.IRON,new Item[]{Items.COPPER_INGOT, Items.COPPER_INGOT},new Color(234, 122, 77),true));
        addedMaterials.add(new CustomMaterial("Energized Netherite",8,35,11,10,10,new ArrayList<>(List.of(new MobEffect[]{MobEffects.FIRE_RESISTANCE,MobEffects.DAMAGE_RESISTANCE,MobEffects.ABSORPTION})),new Integer[]{4,3,15},1f,1f,1, Tiers.NETHERITE,new Item[]{Items.NETHERITE_INGOT, Items.NETHERITE_INGOT},new Color(82, 67, 82),true));
        addedMaterials.add(new CustomMaterial("Energized Aluminium",3,20,1,10,10,new ArrayList<>(List.of(new MobEffect[]{MobEffects.MOVEMENT_SPEED,MobEffects.JUMP})),new Integer[]{3,4},1f,1f,1, ModToolTiers.ALUMINIUM,new Item[]{null, null},new Color(194, 186, 186),false));
        addedMaterials.add(new CustomMaterial("Energized Titanium",6,30,10,10,10,new ArrayList<>(List.of(new MobEffect[]{MobEffects.DAMAGE_BOOST,MobEffects.DAMAGE_RESISTANCE})),new Integer[]{3,2},1f,1f,1,ModToolTiers.TITANIUM,new Item[]{Items.NETHERITE_INGOT, Items.NETHERITE_INGOT},new Color(107, 101, 101),false));
        int combinedIndex=0;
        for (int i=0;i<2;i++){
            ArrayList<CustomMaterial[]> newlyAddedMaterialCombinations=new ArrayList<>();
            for (int k=0;k<addedMaterials.size()-1;k++){
                CustomMaterial material1=addedMaterials.get(k);
                int startIndex=k+1;
                if (startIndex<combinedIndex){
                    startIndex=combinedIndex;
                }
                for (int j=startIndex;j<addedMaterials.size();j++) {
                    CustomMaterial material2 = addedMaterials.get(j);
                    if (!materialCombinations.contains(new CustomMaterial[]{material1, material2})) {
                        materialCombinations.add(new CustomMaterial[]{material1, material2});
                        newlyAddedMaterialCombinations.add(new CustomMaterial[]{material1, material2});
                    }
                }
            }
            combinedIndex=addedMaterials.size();
            for (CustomMaterial[] materials:newlyAddedMaterialCombinations){
                addedMaterials.add(new CustomMaterial(materials[0],materials[1]));
                names.add(addedMaterials.get(addedMaterials.size()-1).getName());
            }
            newlyAddedMaterialCombinations.clear();
        }
    }

    public static ArrayList<String> getNames() {
        return names;
    }
}
