package net.tarzan.testmod.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ChargedFoods {
    public static final FoodProperties CHARGEDREDSTONE=new FoodProperties.Builder().fast().effect(()->new MobEffectInstance(MobEffects.JUMP, 1200),0.5f).alwaysEat().build();

}


