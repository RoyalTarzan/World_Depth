package net.tarzan.world_depth.materials;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.world_depth.block.ModBlocks;
import net.tarzan.world_depth.datagen.ModRecipeProvider;
import net.tarzan.world_depth.item.ModArmorMaterials;
import net.tarzan.world_depth.item.ModItems;
import net.tarzan.world_depth.item.custom.ModArmorItem;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;

public class CustomMaterial {
    private final int strength;
    private final int protection;
    private final int durabilityMultiplier;
    private final Integer[] amplifiers;
    private final float knockbackResistance;
    private final float toughness;
    private final int enchantmentvalue;
    private CustomMaterial[] materialParents = new CustomMaterial[2];
    public RegistryObject<Item>[] Tools;
    private final String name;
    private final int attackSpeed;
    private final int attackDamage;
    private final MobEffect[] mobEffects;
    public  RegistryObject<Item> Item;
    private ModArmorMaterials ArmorMaterial;
    public  RegistryObject<Block> Block;
    public Tier toolTier= Tiers.IRON;
    public RegistryObject<Item>[] Armour;
    private final Ingredient[] parents=new Ingredient[2];
    private final Color color;
    private final String material;
    private boolean create=true;

    public CustomMaterial(String name, int protection, int durabilityMultiplier, int strength, int attackSpeed, int attackDamage, MobEffect[] mobEffects, Integer[] amplifiers, float knockbackResistance, float toughness, int enchantmentvalue, @Nullable Tier toolTier, Item[] parents, Color color,boolean create) {
        this.strength = strength;
        this.attackSpeed = attackSpeed;
        this.attackDamage = attackDamage;
        this.mobEffects = mobEffects;
        this.name=name.toLowerCase().replace(" ","_");
        this.material=name;
        this.color = color;
        this.protection=protection;
        this.durabilityMultiplier=durabilityMultiplier;
        this.amplifiers=amplifiers;
        this.knockbackResistance=knockbackResistance;
        this.toughness=toughness;
        this.enchantmentvalue=enchantmentvalue;
        int[] protectionAmounts = new int[]{this.protection, this.protection * 3 / 4, this.protection * 3 / 4, this.protection * 3 / 8};
        this.create=create;
        if (this.create) {
            ArmorMaterial = new ModArmorMaterials(this.name, this.durabilityMultiplier, protectionAmounts, this.enchantmentvalue,
                    SoundEvents.ARMOR_EQUIP_IRON, this.toughness, this.knockbackResistance, () -> Ingredient.of(Item.get()));
            for (MobEffect mobEffect : this.mobEffects) {
                if (mobEffect == MobEffects.HEALTH_BOOST) {
                    continue;
                }
                ModArmorItem.addMaterialWithEffect(ArmorMaterial, new MobEffectInstance(mobEffect, 0, this.amplifiers[Arrays.stream(this.mobEffects).toList().indexOf(mobEffect)], false, false, false));
            }
            if (toolTier != null) {
                this.toolTier = toolTier;
            }
            for (int i = 0; i < parents.length; i++) {
                this.parents[i] = Ingredient.of(parents[i]);
            }
            Item = ModItems.register(this.name, () -> new Item(new Item.Properties()));
            Block = ModBlocks.registerBlock(this.name + "_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).strength(this.strength*5,this.strength*120)));
            this.Armour = ModItems.registerArmorSet(this.name, this.ArmorMaterial);
            this.Tools = ModItems.registerToolSet(this.name, this.toolTier, this.attackDamage, this.attackSpeed);
        }
    }
    public CustomMaterial(String name, int protection, int durabilityMultiplier, int strength, int attackSpeed, int attackDamage, MobEffect[] mobEffects, Integer[] amplifiers, float knockbackResistance, float toughness, int enchantmentvalue, @Nullable Tier toolTier, CustomMaterial[] parents, Color color,boolean create) {
        this.strength = strength;
        this.attackSpeed = attackSpeed;
        this.attackDamage = attackDamage;
        this.mobEffects = mobEffects;
        this.name=name.toLowerCase().replace(" ","_");
        this.material=name;
        this.color = color;
        this.protection=protection;
        this.durabilityMultiplier=durabilityMultiplier;
        this.amplifiers=amplifiers;
        this.knockbackResistance=knockbackResistance;
        this.toughness=toughness;
        this.enchantmentvalue=enchantmentvalue;
        int[] protectionAmounts = new int[]{this.protection, this.protection * 3 / 4, this.protection * 3 / 4, this.protection * 3 / 8};
        this.create=create;
        if (create) {
            ArmorMaterial = new ModArmorMaterials(this.name, this.durabilityMultiplier, protectionAmounts, this.enchantmentvalue,
                    SoundEvents.ARMOR_EQUIP_IRON, this.toughness, this.knockbackResistance, () -> Ingredient.of(Item.get()));
            for (MobEffect mobEffect : this.mobEffects) {
                if (mobEffect == MobEffects.HEALTH_BOOST) {
                    continue;
                }
                ModArmorItem.addMaterialWithEffect(ArmorMaterial, new MobEffectInstance(mobEffect, 0, this.amplifiers[Arrays.stream(this.mobEffects).toList().indexOf(mobEffect)], false, false, false));
            }
            if (toolTier != null) {
                this.toolTier = toolTier;
            }
            this.materialParents = parents;
            Item = ModItems.ITEMS.register(this.name, () -> new Item(new Item.Properties()));
            Block = ModBlocks.registerBlock(this.name + "_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).strength(this.strength)));
            this.Armour = ModItems.registerArmorSet(this.name, this.ArmorMaterial);
            this.Tools = ModItems.registerToolSet(this.name, this.toolTier, this.attackDamage, this.attackSpeed);
        }
    }

    public CustomMaterial(CustomMaterial parent1, CustomMaterial parent2){
        ArrayList<MobEffect> effects=new ArrayList<>();
        ArrayList<Integer> amplifiers=new ArrayList<>();
        for (MobEffect effect:parent1.getMobEffects()){
            if (Arrays.stream(parent2.getMobEffects()).toList().contains(effect)){
                int index=Arrays.stream(parent2.getMobEffects()).toList().indexOf(effect);
                effects.add(effect);
                int amp=(parent1.getAmplifiers()[Arrays.stream(parent1.getMobEffects()).toList().indexOf(effect)]+parent2.getAmplifiers()[index])*7/8;
                amplifiers.add(Math.max(amp, 1));
            } else {
                effects.add(effect);
                int amp=parent1.getAmplifiers()[Arrays.stream(parent1.getMobEffects()).toList().indexOf(effect)]*5/4;
                amplifiers.add(Math.max(amp, 1));
            }
        }
        for (MobEffect effect:parent2.getMobEffects()){
            if (!Arrays.stream(parent1.getMobEffects()).toList().contains(effect)){continue;}
            effects.add(effect);
            int amp=Arrays.stream(parent2.getMobEffects()).toList().indexOf(effect)*5/4;
            amplifiers.add(Math.max(amp, 1));
        }
        String fromParent1=parent1.getMaterial().replace("ium","").replace("Energized ","").replace(" ","");
        String fromParent2=parent2.getMaterial().replace("ium","").replace("Energized ","").replace(" ","");
        fromParent1=fromParent1.substring(0,fromParent1.length()/2);
        fromParent2=fromParent2.substring(0,fromParent2.length()/2+1);
        this.material=fromParent1+fromParent2.toLowerCase()+effects.get(0).getDescriptionId().charAt(2)+"ium";
        this.name=this.material.toLowerCase().replace(" ","_");
        this.protection=(parent1.getProtection()+parent2.getProtection())*4/5;
        this.durabilityMultiplier= (parent1.getDurabilityMultiplier()+parent2.getDurabilityMultiplier())*5/6;
        this.strength= (parent1.getStrength()+parent2.getStrength())*8/9;
        this.attackSpeed= (parent1.getAttackSpeed()+parent2.getAttackSpeed())*9/14;
        this.attackDamage=(parent1.getAttackDamage()+parent2.getAttackDamage())*7/13;
        this.mobEffects= effects.toArray(new MobEffect[0]);
        this.amplifiers= amplifiers.toArray(new Integer[0]);
        this.knockbackResistance= (parent1.getKnockbackResistance()+parent2.getKnockbackResistance())*15/29;
        this.toughness=(parent1.getToughness()+parent2.getToughness())*2/3;
        this.enchantmentvalue= (parent1.getEnchantmentvalue()+parent2.getEnchantmentvalue())*3/2;
        this.materialParents=new CustomMaterial[]{parent1,parent2};
        this.color=new Color((parent1.getColor().getRed()+parent2.getColor().getRed())/2,
                (parent1.getColor().getGreen()+parent2.getColor().getGreen())/2,
                (parent1.getColor().getBlue()+parent2.getColor().getBlue())/2);

        int[] protectionAmounts = new int[]{this.protection, this.protection * 3 / 4, this.protection * 3 / 4, this.protection * 3 / 8};
        ArmorMaterial=new ModArmorMaterials(this.name, this.durabilityMultiplier, protectionAmounts, this.enchantmentvalue,
                SoundEvents.ARMOR_EQUIP_IRON, this.toughness, this.knockbackResistance, () -> Ingredient.of(Item.get()));
        Item=ModItems.ITEMS.register(this.name,()->new Item(new Item.Properties()));
        Block=ModBlocks.registerBlock(this.name+"_block",()->new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).strength(this.strength)));
        this.Armour=ModItems.registerArmorSet(this.name,this.ArmorMaterial);
        this.Tools =ModItems.registerToolSet(this.name,this.toolTier,this.attackDamage,this.attackSpeed);
        for (MobEffect mobEffect:this.mobEffects){
            if (mobEffect== MobEffects.HEALTH_BOOST){continue;}
            ModArmorItem.addMaterialWithEffect(ArmorMaterial, new MobEffectInstance(mobEffect, 0, this.amplifiers[Arrays.stream(this.mobEffects).toList().indexOf(mobEffect)], false, false, false));
        }
    }

    public void registerRecipes(@NotNull Consumer<FinishedRecipe> consumer){
        if (!this.create){return;}
        ModRecipeProvider.fullArmor(this.Item.get(),this.Armour[0].get(),this.Armour[1].get(),this.Armour[2].get(),this.Armour[3].get(),consumer );
        ModRecipeProvider.toolSet(this.Item.get(),this.Tools[3].get(),this.Tools[4].get(),this.Tools[1].get(),this.Tools[2].get(),this.Tools[0].get(),consumer);
        if (!Arrays.equals(this.materialParents, new CustomMaterial[2])){
            ModRecipeProvider.energizing(this, this.materialParents, 100,200,this.name,consumer,Items.NETHERITE_INGOT);
        }
        if (!Arrays.equals(this.parents, new Ingredient[2])){
            ArrayList<Ingredient> ingr = new ArrayList<>();
            Collections.addAll(ingr, this.parents);
            ingr.add(Ingredient.of(ModItems.WORLD_GEM.get()));
            ModRecipeProvider.energizing(this, ingr.toArray(new Ingredient[0]), 100,200,this.name,consumer, Items.NETHERITE_INGOT);
        }
        ModRecipeProvider.blockToSingleAndReverse(this.Item.get(), this.Block.get(), consumer);
    }

    public void registerBlockAndItem(){
    }

    public String getName() {
        return this.name;
    }

    public int getProtection() {
        return protection;
    }

    public int getDurabilityMultiplier() {
        return durabilityMultiplier;
    }

    public Color getColor() {
        return color;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public MobEffect[] getMobEffects() {
        return mobEffects;
    }

    public int getStrength() {
        return strength;
    }

    public Integer[] getAmplifiers() {
        return amplifiers;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public Tier getToolTier() {
        return toolTier;
    }

    public String getMaterial() {
        return material;
    }

    public float getKnockbackResistance() {
        return knockbackResistance;
    }

    public float getToughness() {
        return toughness;
    }

    public int getEnchantmentvalue() {
        return enchantmentvalue;
    }
}
