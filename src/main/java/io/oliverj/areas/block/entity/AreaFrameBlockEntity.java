package io.oliverj.areas.block.entity;

import io.oliverj.areas.registry.BlockEntityRegister;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AreaFrameBlockEntity extends BlockEntity {
    public AreaFrameBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegister.AREA_FRAME_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, AreaFrameBlockEntity be) {}
}
