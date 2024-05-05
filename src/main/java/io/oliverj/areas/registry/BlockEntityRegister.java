package io.oliverj.areas.registry;

import io.oliverj.areas.block.entity.AreaCoreBlockEntity;
import io.oliverj.areas.block.entity.AreaFrameBlockEntity;
import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.awt.geom.Area;

public class BlockEntityRegister implements AutoRegistryContainer<BlockEntityType<?>> {

    public static final BlockEntityType<AreaFrameBlockEntity> AREA_FRAME_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(AreaFrameBlockEntity::new, BlockRegister.AREA_FRAME).build();
    public static final BlockEntityType<AreaCoreBlockEntity> AREA_CORE_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(AreaCoreBlockEntity::new, BlockRegister.AREA_CORE).build();
    @Override
    public Registry getRegistry() {
        return Registries.BLOCK_ENTITY_TYPE;
    }

    @Override
    public Class getTargetFieldType() {
        return (Class<BlockEntityType<?>>) (Object) BlockEntityType.class;
    }
}
