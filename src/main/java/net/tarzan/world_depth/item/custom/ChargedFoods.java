package net.tarzan.world_depth.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Random;

public class ChargedFoods extends Item {
    public ChargedFoods(Properties pProperties) {
        super(pProperties.food(Charged_Foods.CHARGED_REDSTONE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack=pPlayer.getItemInHand(pUsedHand);
        pPlayer.eat(pLevel, stack);
        Random random=new Random();
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.JUMP,1200,8));
        }
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,1200,6));
        }
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE,1200,23));
        }
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,1200,23));
        }
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.SATURATION,1200,12));
        }
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.GLOWING,1200,23));
        }
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING,1200,23));
        }
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,1200,100));
        }
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,20,3));
        }

        pPlayer.resetFallDistance();

        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
    }
}
