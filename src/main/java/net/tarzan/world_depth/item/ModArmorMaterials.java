package net.tarzan.world_depth.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.tarzan.world_depth.World_Depth;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    TITANIUM("titanium",30,new int[]{6,8,8,5},10,
            SoundEvents.ARMOR_EQUIP_IRON, 2f, 1f,()->Ingredient.of(ModItems.TITANIUM.get())),
    ENERGIZED_TITANIUM("titanium",30,new int[]{6,8,8,5},10,
            SoundEvents.ARMOR_EQUIP_IRON, 2f, 1f,()->Ingredient.of(ModItems.TITANIUM.get())),
    ALUMINIUM("aluminium",20,new int[]{3,4,4,2},20,
            SoundEvents.ARMOR_EQUIP_GOLD, 0f, 0f,()->Ingredient.of(ModItems.ALUMINIUM.get())),
    ENERGIZED_ALUMINIUM("aluminium",20,new int[]{3,4,4,2},20,
            SoundEvents.ARMOR_EQUIP_GOLD, 0f, 0f,()->Ingredient.of(ModItems.ALUMINIUM.get())),
    TALIUM("talium",25,new int[]{4,6,6,3},15,
            SoundEvents.ARMOR_EQUIP_DIAMOND,1f,0.5f,()->Ingredient.of(ModItems.TALIUM.get()));

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY={11,16,15,13};

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()]*this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return World_Depth.MODID+":"+this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
