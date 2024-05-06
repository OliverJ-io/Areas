package io.oliverj.areas.registry;

import io.oliverj.areas.Areas;
import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import net.minecraft.util.Identifier;

public class ItemGroupRegister {
    public static final OwoItemGroup AREAS = OwoItemGroup
            .builder(new Identifier(Areas.MOD_ID, "areas_group"), () -> Icon.of(BlockRegister.AREA_CORE.asItem()))
            .build();
}
