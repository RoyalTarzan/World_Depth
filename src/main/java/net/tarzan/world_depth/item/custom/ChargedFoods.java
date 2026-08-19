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
    private static final int duration=3600;
    public ChargedFoods(Properties pProperties) {
        super(pProperties.food(Charged_Foods.CHARGED_REDSTONE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pPlayer.level().isClientSide()){return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));}
        ItemStack stack=pPlayer.getItemInHand(pUsedHand);
        pPlayer.eat(pLevel, stack);
        Random random=new Random();
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.JUMP,duration,8));
        }
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,duration,6));
        }
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE,duration,23));
        }
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,duration,23));
        }
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.SATURATION,duration,12));
        }
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.GLOWING,duration,23));
        }
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING,duration,23));
        }
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,duration,100));
        }
        if(random.nextInt(100)<20){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,20,3));
        }

        pPlayer.resetFallDistance();

        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
    }
}
