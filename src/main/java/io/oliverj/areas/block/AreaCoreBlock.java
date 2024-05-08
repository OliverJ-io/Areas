package io.oliverj.areas.block;

import com.ibm.icu.impl.CollectionSet;
import io.oliverj.areas.block.entity.AreaCoreBlockEntity;
import io.oliverj.areas.block.entity.AreaFrameBlockEntity;
import io.oliverj.areas.item.InversionCrystalItem;
import io.oliverj.areas.item.NullCrystalItem;
import io.oliverj.areas.registry.BlockEntityRegister;
import io.oliverj.areas.utils.CrystalType;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.include.com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class AreaCoreBlock extends HorizontalConnectingBlock implements BlockEntityProvider {
    private final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.9, 16.0);
    public AreaCoreBlock(Settings settings) {
        super(8.0f, 8.0f, 16.0f, 16.0f, 10.9f, settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(WATERLOGGED, false));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AreaCoreBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        Inventory blockEntity = (Inventory) world.getBlockEntity(pos);

        if (!player.getStackInHand(hand).isEmpty()) {
            if (blockEntity.getStack(0).isEmpty()) {
                blockEntity.setStack(0, player.getStackInHand(hand).copy());
                player.getStackInHand(hand).setCount(0);
                ItemStack stack = blockEntity.getStack(0);
                if (stack.getItem() instanceof NullCrystalItem || stack.getItem() instanceof InversionCrystalItem) {
                    List<Direction> directions = new ArrayList<>();
                    if (world.getBlockState(pos.north()).getBlock() instanceof AreaFrameBlock) {
                        directions.add(Direction.NORTH);
                    }
                    if (world.getBlockState(pos.east()).getBlock() instanceof AreaFrameBlock) {
                        directions.add(Direction.EAST);
                    }
                    if (world.getBlockState(pos.south()).getBlock() instanceof AreaFrameBlock) {
                        directions.add(Direction.SOUTH);
                    }
                    if (world.getBlockState(pos.south()).getBlock() instanceof AreaFrameBlock) {
                        directions.add(Direction.WEST);
                    }
                    Direction direction = directions.stream().skip((int) (directions.size() * Math.random())).findFirst().get();
                    AreaCoreBlockEntity be = (AreaCoreBlockEntity) world.getBlockEntity(pos);
                    if (stack.getItem() instanceof NullCrystalItem) {
                        be.setActivated(CrystalType.NULL);
                    } else if (stack.getItem() instanceof InversionCrystalItem) {
                        be.setActivated(CrystalType.INVERSION);
                    } else {
                        be.setActivated(CrystalType.NONE);
                    }
                    AreaFrameBlockEntity beN = (AreaFrameBlockEntity) world.getBlockEntity(pos.offset(direction));
                    if (beN != null) {
                        beN.chain(be.getActivated());
                    }
                }
            }
        } else {
            if (!blockEntity.getStack(0).isEmpty()) {
                player.getInventory().offerOrDrop(blockEntity.getStack(0));
                blockEntity.removeStack(0);
                AreaCoreBlockEntity be = (AreaCoreBlockEntity) blockEntity;
                be.setActivated(CrystalType.NONE);
            }
        }
        return ActionResult.SUCCESS;
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
        return !cannotConnect(state) && bl2;
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

    /**
     * {@return the ticker if the given type and expected type are the same, or {@code null} if they are different}
     */
    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> checkType(BlockEntityType<A> givenType, BlockEntityType<E> expectedType, BlockEntityTicker<? super E> ticker) {
        return expectedType == givenType ? (BlockEntityTicker<A>) ticker : null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, BlockEntityRegister.AREA_CORE_BLOCK_ENTITY, (world1, pos, state1, be) -> AreaCoreBlockEntity.tick(world1, pos, state1, be));
    }
}
