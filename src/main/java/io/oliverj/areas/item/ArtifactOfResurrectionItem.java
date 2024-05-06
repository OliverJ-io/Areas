package io.oliverj.areas.item;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import io.oliverj.areas.registry.CardinalComponentRegister;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class ArtifactOfResurrectionItem extends TrinketItem {
    public ArtifactOfResurrectionItem(Settings settings) {
        super(settings);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        return super.getModifiers(stack, slot, entity, uuid);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.getHolder() instanceof PlayerEntity) {
            tooltip.add(Text.literal("Uses Left: " + stack.getHolder().getComponent(CardinalComponentRegister.RESURRECTION).getUses()));
        }
    }
}
