package io.oliverj.areas.mixin;

import com.mojang.logging.LogUtils;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import io.oliverj.areas.Areas;
import io.oliverj.areas.item.ArtifactOfResurrectionItem;
import io.oliverj.areas.registry.ArtifactRegister;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "onDeath", at = @At(value = "HEAD"), cancellable = true)
    private void onDeath(DamageSource damageSource, CallbackInfo ci) {
        LogUtils.getLogger().info("OnDeath: AREAS");
        Areas.LOGGER.info("Ran Death Mixin");
        ci.cancel();
        if (damageSource.getSource() instanceof PlayerEntity player) {
            TrinketComponent trinkets = player.getComponent(TrinketsApi.TRINKET_COMPONENT);
            List<Pair<SlotReference, ItemStack>> trinketsEquipped = trinkets.getEquipped(ArtifactRegister.RESURRECT_ARTIFACT);
            for (Pair<SlotReference, ItemStack> trinket: trinketsEquipped) {
                ItemStack stack = trinket.getRight();
                if (stack.getItem() instanceof ArtifactOfResurrectionItem && stack.getNbt().getInt("uses") > 0) {
                    player.heal(player.getMaxHealth());
                    ci.cancel();
                }
            }
        }
    }
}
