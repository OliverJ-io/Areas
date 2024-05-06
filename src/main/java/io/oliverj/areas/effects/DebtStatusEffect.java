package io.oliverj.areas.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

@SuppressWarnings("empty")
public class DebtStatusEffect extends StatusEffect {
    public DebtStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0x98D982);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            // TODO: Complete Effect Processing
        }
    }
}