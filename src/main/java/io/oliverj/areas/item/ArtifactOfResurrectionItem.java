package io.oliverj.areas.item;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import io.oliverj.areas.registry.ArtifactRegister;
import io.oliverj.areas.registry.CardinalComponentRegister;
import io.wispforest.owo.nbt.NbtKey;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtInt;
import net.minecraft.nbt.NbtTypes;
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
            tooltip.add(Text.literal("Uses Left: " + stack.getNbt().get("uses").toString()));
        }
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack itemStack = new ItemStack(ArtifactRegister.RESURRECT_ARTIFACT);
        itemStack.setSubNbt("uses", NbtInt.of(10));
        return itemStack;
    }
}
