package io.oliverj.areas.registry;

import io.oliverj.areas.block.AreaCoreBlock;
import io.oliverj.areas.block.AreaFrameBlock;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;

public class BlockRegister implements BlockRegistryContainer {

    public static final Block AREA_FRAME = new AreaFrameBlock(FabricBlockSettings.create().strength(10.0f, 10.0f).requiresTool().nonOpaque());
    public static final Block AREA_CORE = new AreaCoreBlock(FabricBlockSettings.create().strength(10.0f, 10.0f).requiresTool().nonOpaque());
}
