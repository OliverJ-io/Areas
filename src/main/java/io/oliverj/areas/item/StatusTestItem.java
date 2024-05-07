package io.oliverj.areas.item;

import io.oliverj.areas.registry.CardinalComponentRegister;
import io.oliverj.areas.types.Status;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class StatusTestItem extends Item {
    public StatusTestItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            user.sendMessage(Text.literal("Current Statuses: " + user.getComponent(CardinalComponentRegister.STATUSES).getList()));
            return TypedActionResult.success(user.getStackInHand(hand));
        }
        user.getComponent(CardinalComponentRegister.STATUSES).add(new Status("test_status"));
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
