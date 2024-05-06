package io.oliverj.areas.mixin;

import dev.emi.trinkets.api.TrinketsApi;
import io.oliverj.areas.Areas;
import io.oliverj.areas.registry.ArtifactRegister;
import io.oliverj.areas.registry.CardinalComponentRegister;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class PlayerDeathMixin {
    @Inject(method = "onDeath", at = @At(value = "HEAD"), cancellable = true)
    private void onDeath(DamageSource damageSource, CallbackInfo ci) {
        ci.cancel();
        Areas.LOGGER.info("Ran Death Mixin");
        if (damageSource.getSource() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) damageSource.getSource();
            player.sendMessage(Text.literal("Hello World!"));
            if (player.getComponent(CardinalComponentRegister.RESURRECTION).getUses() > 0 && player.getComponent(TrinketsApi.TRINKET_COMPONENT).isEquipped(ArtifactRegister.RESURRECT_ARTIFACT)) {
                player.heal(player.getMaxHealth());
                player.getComponent(CardinalComponentRegister.RESURRECTION).decrementUses();
                ci.cancel();
            }
        }
    }
}
