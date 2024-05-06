package io.oliverj.areas.mixin;

import com.mojang.logging.LogUtils;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import dev.onyxstudios.cca.api.v3.component.Component;
import io.oliverj.areas.Areas;
import io.oliverj.areas.item.ArtifactOfResurrectionItem;
import io.oliverj.areas.registry.ArtifactRegister;
import io.oliverj.areas.registry.CardinalComponentRegister;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtInt;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerEntity.class)
public class PlayerDeathMixin {
    @Inject(method = "onDeath", at = @At(value = "HEAD"), cancellable = true)
    private void onDeath(DamageSource damageSource, CallbackInfo ci) {
        LogUtils.getLogger().info("OnDeath: AREAS");
        Areas.LOGGER.info("Ran Death Mixin");
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
