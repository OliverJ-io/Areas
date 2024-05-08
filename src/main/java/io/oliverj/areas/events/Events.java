package io.oliverj.areas.events;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import io.oliverj.areas.item.ArtifactOfResurrectionItem;
import io.oliverj.areas.registry.ArtifactRegister;
import io.oliverj.areas.registry.ItemRegister;
import io.wispforest.owo.nbt.NbtKey;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtInt;
import net.minecraft.util.Pair;

import java.util.List;

@SuppressWarnings("unused")
public class Events {
    public static void registerEvents() {
        ServerLivingEntityEvents.ALLOW_DEATH.register(((entity, damageSource, damageAmount) -> {
            if (entity instanceof PlayerEntity player) {
                if (player.getComponent(TrinketsApi.TRINKET_COMPONENT).isEquipped(ArtifactRegister.RESURRECT_ARTIFACT)) {
                    Pair<SlotReference, ItemStack> equippedArtifact = player.getComponent(TrinketsApi.TRINKET_COMPONENT).getEquipped(ArtifactRegister.RESURRECT_ARTIFACT).get(0);
                    ItemStack artifact = equippedArtifact.getRight();
                    int uses = artifact.get(new NbtKey<>("uses", NbtKey.Type.INT));
                    artifact.setSubNbt("uses", NbtInt.of(uses - 1));
                    player.heal(player.getMaxHealth());
                    return false;
                }
            }
            return false;
        }));
    }
}
