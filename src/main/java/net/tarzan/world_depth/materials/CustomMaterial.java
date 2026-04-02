package net.tarzan.world_depth.materials;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.world_depth.block.ModBlocks;
import net.tarzan.world_depth.item.ModArmorMaterials;
import net.tarzan.world_depth.item.ModItems;
import net.tarzan.world_depth.item.custom.ModArmorItem;

import javax.annotation.Nullable;

public class CustomMaterial {
    private final int strength;
    private int attackSpeed;
    private int attackDamage;
    private MobEffectInstance[] mobEffects;
    public RegistryObject<Item> item;
    private ModArmorMaterials ArmorMaterial;
    public RegistryObject<Block> block;
    public Tier toolTier= Tiers.IRON;

    public CustomMaterial(String name, int protection, int durabilityMultiplier, int strength, int attackSpeed, int attackDamage, MobEffectInstance[] mobEffects, int[] amplifiers, float knockbackResistance, float toughness, int enchantmentvalue,@Nullable Tier toolTier) {
        this.strength = strength;
        this.attackSpeed = attackSpeed;
        this.attackDamage = attackDamage;
        this.mobEffects = mobEffects;
        item = ModItems.ITEMS.register(name, () -> new Item(new Item.Properties()));
        block= ModBlocks.BLOCKS.register(name+"_block",()->new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).strength(this.strength)));
        int[] protectionAmounts = new int[]{protection, protection * 3 / 4, protection * 3 / 4, protection * 3 / 8};
        ArmorMaterial=new ModArmorMaterials(name, durabilityMultiplier, protectionAmounts, enchantmentvalue,
                SoundEvents.ARMOR_EQUIP_IRON, toughness, knockbackResistance, () -> Ingredient.of(item.get()));
        for (MobEffectInstance mobEffect:mobEffects){
            ModArmorItem.addMaterialWithEffect(ArmorMaterial,mobEffect);
        }
        if (toolTier!=null){this.toolTier=toolTier;}
        ModItems.registerArmorSet(name,ArmorMaterial);
        ModItems.registerToolSet(name,this.toolTier,attackDamage,attackSpeed);
    }
}
