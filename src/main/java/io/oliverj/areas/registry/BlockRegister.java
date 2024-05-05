package io.oliverj.areas.registry;

import io.oliverj.areas.block.AreaCoreBlock;
import io.oliverj.areas.block.AreaFrameBlock;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BlockRegister implements BlockRegistryContainer {

    public static final Block AREA_FRAME = new AreaFrameBlock(FabricBlockSettings.create().strength(10.0f, 10.0f).requiresTool());
    public static final Block AREA_CORE = new AreaCoreBlock(FabricBlockSettings.create().strength(10.0f, 10.0f).requiresTool());

    @Override
    public BlockItem createBlockItem(Block block, String identifier) {
        return new BlockItem(block, new Item.Settings());
    }
}
