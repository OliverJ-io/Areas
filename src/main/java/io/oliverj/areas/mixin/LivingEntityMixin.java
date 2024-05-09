package io.oliverj.areas.mixin;

import io.oliverj.areas.Areas;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract void heal(float amount);

    @Shadow public abstract float getMaxHealth();

    @Shadow public abstract float getHealth();

    @Shadow protected boolean dead;

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isDead()Z", ordinal = 1), cancellable = true)
    private void confirmDeath(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (this.getHealth() <= 1) {
            this.heal(this.getMaxHealth());
            this.dead = false;
            cir.cancel();
            cir.setReturnValue(false);
        }
    }
}
