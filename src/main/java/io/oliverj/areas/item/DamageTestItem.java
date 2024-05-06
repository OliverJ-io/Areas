package io.oliverj.areas.item;

import io.oliverj.areas.Areas;
import io.oliverj.areas.AreasDamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DamageTestItem extends Item {
    public DamageTestItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.damage(AreasDamageTypes.of(world, AreasDamageTypes.INVERSION_DAMAGE_TYPE), 1.0f);
        Areas.LOGGER.info("Used Test Item - DAMAGE");
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
