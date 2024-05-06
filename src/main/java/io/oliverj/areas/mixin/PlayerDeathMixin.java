package io.oliverj.areas.mixin;

import dev.emi.trinkets.api.TrinketsApi;
import io.oliverj.areas.Areas;
import io.oliverj.areas.registry.ArtifactRegister;
import io.oliverj.areas.registry.CardinalComponentRegister;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerDeathMixin {
    @Inject(at = @At(value = "HEAD"), method = "onDeath", cancellable = true)
    private void onDeath(DamageSource damageSource, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) damageSource.getSource();
        if (player.getComponent(CardinalComponentRegister.RESURRECTION).getUses() > 0 && player.getComponent(TrinketsApi.TRINKET_COMPONENT).isEquipped(ArtifactRegister.RESURRECT_ARTIFACT)) {
            player.heal(player.getMaxHealth());
            player.getComponent(CardinalComponentRegister.RESURRECTION).decrementUses();
        }
        Areas.LOGGER.debug("MIXIN RAN");
    }
}
