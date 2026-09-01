package net.tarzan.world_depth.item.custom;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import net.tarzan.world_depth.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.UUID;

public class Material extends Item {
    public Material(Properties pProperties) {
        super(pProperties);
    }

    public static int getColor(ItemStack stack,int index){
        if (index!=0) return -1;
        CompoundTag nbt= stack.getTag();
        if(nbt!=null && stack.getTag().contains("color")){
            return stack.getTag().getInt("color");
        }
        int r=100;
        int g=50;
        int b=255;
        return (r<<8|g)<<8|b;
    }

    public static class PickaxeMaterial extends PickaxeItem {
        public PickaxeMaterial(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
            super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        }

        @Override
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
            Multimap<Attribute,AttributeModifier> result= ArrayListMultimap.create();
            result.putAll(super.getAttributeModifiers(slot, stack));
            if (slot.getName().equals(EquipmentSlot.MAINHAND.getName())){
                assert stack.getTag() != null;
                result.put(Attributes.ATTACK_DAMAGE,new AttributeModifier(BASE_ATTACK_DAMAGE_UUID,"attack_damage",stack.getTag().getDouble("damage"), AttributeModifier.Operation.ADDITION));
                result.put(Attributes.ATTACK_SPEED,new AttributeModifier(BASE_ATTACK_SPEED_UUID,"attack_speed",stack.getTag().getDouble("speed"), AttributeModifier.Operation.ADDITION));
            }
            return result;
        }

        @Override
        public float getDestroySpeed(@NotNull ItemStack pStack, BlockState pState) {
            return pState.is(this.blocks)&&pStack.getTag()!=null ? ((float) (this.speed * pStack.getTag().getDouble("dig_speed"))) : 1.0F;
        }

        public static int getColor(ItemStack stack,int index){
            if (index!=0) return -1;
            CompoundTag nbt= stack.getTag();
            if(nbt!=null && stack.getTag().contains("color")){
                return stack.getTag().getInt("color");
            }
            int r=100;
            int g=50;
            int b=255;
            return (r<<8|g)<<8|b;
        }
    }
    public static class AxeMaterial extends AxeItem {
        public AxeMaterial(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
            super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        }

        @Override
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
            Multimap<Attribute,AttributeModifier> result= ArrayListMultimap.create();
            result.putAll(super.getAttributeModifiers(slot, stack));
            if (slot.getName().equals(EquipmentSlot.MAINHAND.getName())){
                assert stack.getTag() != null;
                result.put(Attributes.ATTACK_DAMAGE,new AttributeModifier(BASE_ATTACK_DAMAGE_UUID,"attack_damage",stack.getTag().getDouble("damage"), AttributeModifier.Operation.ADDITION));
                result.put(Attributes.ATTACK_SPEED,new AttributeModifier(BASE_ATTACK_SPEED_UUID,"attack_speed",stack.getTag().getDouble("speed"), AttributeModifier.Operation.ADDITION));
            }
            return result;
        }

        @Override
        public float getDestroySpeed(@NotNull ItemStack pStack, BlockState pState) {
            return pState.is(this.blocks)&&pStack.getTag()!=null ? ((float) (this.speed * pStack.getTag().getDouble("dig_speed"))) : 1.0F;
        }

        public static int getColor(ItemStack stack, int index){
            if (index!=0) return -1;
            CompoundTag nbt= stack.getTag();
            if(nbt!=null && stack.getTag().contains("color")){
                return stack.getTag().getInt("color");
            }
            int r=255;
            int g=255;
            int b=255;
            return (r<<8|g)<<8|b;
        }
    }
    public static class SwordMaterial extends SwordItem {
        public SwordMaterial(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
            super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        }


        @Override
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
            Multimap<Attribute,AttributeModifier> result= ArrayListMultimap.create();
            result.putAll(super.getAttributeModifiers(slot, stack));
            if (slot.getName().equals(EquipmentSlot.MAINHAND.getName())){
                assert stack.getTag() != null;
                result.put(Attributes.ATTACK_DAMAGE,new AttributeModifier(BASE_ATTACK_DAMAGE_UUID,"attack_damage",stack.getTag().getDouble("damage"), AttributeModifier.Operation.ADDITION));
                result.put(Attributes.ATTACK_SPEED,new AttributeModifier(BASE_ATTACK_SPEED_UUID,"attack_speed",stack.getTag().getDouble("speed"), AttributeModifier.Operation.ADDITION));
            }
            return result;
        }

        @Override
        public float getDestroySpeed(@NotNull ItemStack pStack, BlockState pState) {
            if (pState.is(Blocks.COBWEB)&&pStack.getTag()!=null) {
                return ((float) (15.0F * pStack.getTag().getDouble("dig_speed")));
            } else {
                return pState.is(BlockTags.SWORD_EFFICIENT) ? 1.5F : 1.0F;
            }
        }

        public static int getColor(ItemStack stack,int index){
            if (index!=0) return -1;
            CompoundTag nbt= stack.getTag();
            if(nbt!=null && stack.getTag().contains("color")){
                return stack.getTag().getInt("color");
            }
            int r=100;
            int g=50;
            int b=255;
            return (r<<8|g)<<8|b;
        }
    }
    public static class ShovelMaterial extends ShovelItem {
        public ShovelMaterial(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
            super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        }


        @Override
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
            Multimap<Attribute,AttributeModifier> result= ArrayListMultimap.create();
            result.putAll(super.getAttributeModifiers(slot, stack));
            if (slot.getName().equals(EquipmentSlot.MAINHAND.getName())){
                assert stack.getTag() != null;
                result.put(Attributes.ATTACK_DAMAGE,new AttributeModifier(BASE_ATTACK_DAMAGE_UUID,"attack_damage",stack.getTag().getDouble("damage"), AttributeModifier.Operation.ADDITION));
                result.put(Attributes.ATTACK_SPEED,new AttributeModifier(BASE_ATTACK_SPEED_UUID,"attack_speed",stack.getTag().getDouble("speed"), AttributeModifier.Operation.ADDITION));
            }
            return result;
        }

        @Override
        public float getDestroySpeed(@NotNull ItemStack pStack, BlockState pState) {
            return pState.is(this.blocks)&&pStack.getTag()!=null ? ((float) (this.speed * pStack.getTag().getDouble("dig_speed"))) : 1.0F;
        }

        public static int getColor(ItemStack stack,int index){
            if (index!=0) return -1;
            CompoundTag nbt= stack.getTag();
            if(nbt!=null && stack.getTag().contains("color")){
                return stack.getTag().getInt("color");
            }
            int r=100;
            int g=50;
            int b=255;
            return (r<<8|g)<<8|b;
        }
    }
    public static class HoeMaterial extends HoeItem {

        public HoeMaterial(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
            super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        }


        @Override
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
            Multimap<Attribute,AttributeModifier> result= ArrayListMultimap.create();
            result.putAll(super.getAttributeModifiers(slot, stack));
            if (slot.getName().equals(EquipmentSlot.MAINHAND.getName())){
                assert stack.getTag() != null;
                result.put(Attributes.ATTACK_DAMAGE,new AttributeModifier(BASE_ATTACK_DAMAGE_UUID,"attack_damage",stack.getTag().getDouble("damage"), AttributeModifier.Operation.ADDITION));
                result.put(Attributes.ATTACK_SPEED,new AttributeModifier(BASE_ATTACK_SPEED_UUID,"attack_speed",stack.getTag().getDouble("speed"), AttributeModifier.Operation.ADDITION));
            }
            return result;
        }

        @Override
        public float getDestroySpeed(@NotNull ItemStack pStack, BlockState pState) {
            return pState.is(this.blocks)&&pStack.getTag()!=null ? ((float) (this.speed * pStack.getTag().getDouble("dig_speed"))) : 1.0F;
        }

        public static int getColor(ItemStack stack, int index){
            if (index!=0) return -1;
            CompoundTag nbt= stack.getTag();
            if(nbt!=null && stack.getTag().contains("color")){
                return stack.getTag().getInt("color");
            }
            int r=100;
            int g=50;
            int b=255;
            return (r<<8|g)<<8|b;
        }
    }

    public static class ArmorMaterial extends ArmorItem{
        private static final EnumMap<Type, UUID> ARMOR_MODIFIER_UUID_PER_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266744_) -> {
            p_266744_.put(ArmorItem.Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
            p_266744_.put(ArmorItem.Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
            p_266744_.put(ArmorItem.Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
            p_266744_.put(ArmorItem.Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
        });
        private final UUID uuid;

        public ArmorMaterial(net.minecraft.world.item.ArmorMaterial pMaterial, Type pType, Properties pProperties) {
            super(pMaterial, pType, pProperties);
            uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(pType);
        }


        @Override
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
            Multimap<Attribute,AttributeModifier> result= ArrayListMultimap.create();
            result.putAll(super.getAttributeModifiers(slot, stack));
            assert stack.getTag() != null;
            if (slot.getName().equals(EquipmentSlot.HEAD.getName())&& stack.is(ModItems.CUSTOM_MATERIAL_HELMET.get())){
                result.put(Attributes.ARMOR,new AttributeModifier(uuid,"armor",stack.getTag().getDouble("armor"), AttributeModifier.Operation.ADDITION));
                result.put(Attributes.KNOCKBACK_RESISTANCE,new AttributeModifier(uuid,"knockback_res",stack.getTag().getDouble("knockback_res"), AttributeModifier.Operation.ADDITION));
                result.put(ForgeMod.SWIM_SPEED.get(),new AttributeModifier(uuid,"swim_speed",stack.getTag().getDouble("swim_speed"), AttributeModifier.Operation.ADDITION));
            } else if (slot.getName().equals(EquipmentSlot.CHEST.getName())&& stack.is(ModItems.CUSTOM_MATERIAL_CHESTPLATE.get())) {
                result.put(Attributes.MAX_HEALTH,new AttributeModifier(uuid,"max_health",stack.getTag().getDouble("health"), AttributeModifier.Operation.ADDITION));
                result.put(Attributes.ARMOR,new AttributeModifier(uuid,"armor",stack.getTag().getDouble("armor"), AttributeModifier.Operation.ADDITION));
                result.put(Attributes.KNOCKBACK_RESISTANCE,new AttributeModifier(uuid,"knockback_res",stack.getTag().getDouble("knockback_res"), AttributeModifier.Operation.ADDITION));
            } else if (slot.getName().equals(EquipmentSlot.LEGS.getName())&& stack.is(ModItems.CUSTOM_MATERIAL_LEGGINGS.get())) {
                result.put(Attributes.ATTACK_DAMAGE,new AttributeModifier(uuid,"attack_damage",stack.getTag().getDouble("damage"), AttributeModifier.Operation.ADDITION));
                result.put(Attributes.ATTACK_SPEED,new AttributeModifier(uuid,"attack_speed",stack.getTag().getDouble("speed"), AttributeModifier.Operation.ADDITION));
                result.put(Attributes.ARMOR,new AttributeModifier(uuid,"armor",stack.getTag().getDouble("armor"), AttributeModifier.Operation.ADDITION));
                result.put(Attributes.KNOCKBACK_RESISTANCE,new AttributeModifier(uuid,"knockback_res",stack.getTag().getDouble("knockback_res"), AttributeModifier.Operation.ADDITION));
            } else if (slot.getName().equals(EquipmentSlot.FEET.getName())&& stack.is(ModItems.CUSTOM_MATERIAL_BOOTS.get())) {
                result.put(Attributes.ARMOR,new AttributeModifier(uuid,"armor",stack.getTag().getDouble("armor"), AttributeModifier.Operation.ADDITION));
                result.put(Attributes.MOVEMENT_SPEED,new AttributeModifier(uuid,"move_speed",stack.getTag().getDouble("move_speed"), AttributeModifier.Operation.MULTIPLY_BASE));
                result.put(Attributes.KNOCKBACK_RESISTANCE,new AttributeModifier(uuid,"knockback_res",stack.getTag().getDouble("knockback_res"), AttributeModifier.Operation.ADDITION));
            }
            return result;
        }

        public static int getColor(ItemStack stack,int index){
            if (index!=0) return -1;
            CompoundTag nbt= stack.getTag();
            if(nbt!=null && stack.getTag().contains("color")){
                return stack.getTag().getInt("color");
            }
            int r=100;
            int g=50;
            int b=255;
            return (r<<8|g)<<8|b;
        }
    }
}
