package net.tarzan.world_depth.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class DamageModifier {

    @Inject(method="getMaxDamage",at=@At("HEAD"), cancellable = true)
    public void getMaxDamage(CallbackInfoReturnable<Integer> cir){
        CompoundTag nbt=((ItemStack)(Object)this).getTag();
        if (nbt!=null&&nbt.contains("durability")) {
            cir.setReturnValue(nbt.getInt("durability"));
            cir.cancel();
        }
    }

}
