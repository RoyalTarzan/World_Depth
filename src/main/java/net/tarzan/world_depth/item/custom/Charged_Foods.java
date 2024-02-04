package net.tarzan.world_depth.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class Charged_Foods {
    public static final FoodProperties CHARGED_REDSTONE=new FoodProperties.Builder().effect(()->new MobEffectInstance(MobEffects.JUMP, 1200),0.5f).alwaysEat().build();

}


