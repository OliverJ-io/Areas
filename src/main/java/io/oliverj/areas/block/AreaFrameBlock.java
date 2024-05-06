package io.oliverj.areas.block;

import io.oliverj.areas.block.entity.AreaFrameBlockEntity;
import io.oliverj.areas.registry.BlockEntityRegister;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AreaFrameBlock extends BlockWithEntity {
    public AreaFrameBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AreaFrameBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, BlockEntityRegister.AREA_FRAME_BLOCK_ENTITY, (world1, pos, state1, be) -> AreaFrameBlockEntity.tick(world1, pos, state1, be));
    }
}
