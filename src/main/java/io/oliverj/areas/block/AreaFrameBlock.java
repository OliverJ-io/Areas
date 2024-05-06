package io.oliverj.areas.block;

import io.oliverj.areas.block.entity.AreaFrameBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class AreaFrameBlock extends HorizontalConnectingBlock implements BlockEntityProvider {

    private final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.9, 16.0);

    public AreaFrameBlock(Settings settings) {
        super(8.0f, 8.0f, 16.0f, 16.0f, 16.0f, settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(WATERLOGGED, false));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AreaFrameBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getOutlineShape(state, world, pos, context);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    public boolean canConnect(BlockState state) {
        Block block = state.getBlock();
        boolean bl2 = block instanceof AreaFrameBlock;
        boolean bl1 = block instanceof AreaCoreBlock;
        return !cannotConnect(state) && bl2 || bl1;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockView blockView = ctx.getWorld();
        BlockPos bp1 = ctx.getBlockPos();
        BlockPos bp2 = bp1.north();
        BlockPos bp3 = bp1.east();
        BlockPos bp4 = bp1.south();
        BlockPos bp5 = bp1.west();
        BlockState bs1 = blockView.getBlockState(bp2);
        BlockState bs2 = blockView.getBlockState(bp3);
        BlockState bs3 = blockView.getBlockState(bp4);
        BlockState bs4 = blockView.getBlockState(bp5);
        return super.getPlacementState(ctx).with(NORTH, this.canConnect(bs1)).with(EAST, this.canConnect(bs2)).with(SOUTH, this.canConnect(bs3)).with(WEST, this.canConnect(bs4));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction.getAxis().getType() == Direction.Type.HORIZONTAL ? state.with(FACING_PROPERTIES.get(direction), this.canConnect(neighborState)) : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{NORTH, EAST, WEST, SOUTH, WATERLOGGED});
    }
}
