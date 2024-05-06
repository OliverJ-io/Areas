package io.oliverj.areas.registry;

import io.oliverj.areas.effects.DebtStatusEffect;
import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class StatusEffectRegister implements AutoRegistryContainer<StatusEffect> {

    public static final StatusEffect DEBT = new DebtStatusEffect();

    @Override
    public Registry<StatusEffect> getRegistry() {
        return Registries.STATUS_EFFECT;
    }

    @Override
    public Class<StatusEffect> getTargetFieldType() {
        return StatusEffect.class;
    }
}
