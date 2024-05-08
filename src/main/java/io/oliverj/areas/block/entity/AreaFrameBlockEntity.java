package io.oliverj.areas.block.entity;

import com.ibm.icu.impl.CollectionSet;
import io.oliverj.areas.block.AreaFrameBlock;
import io.oliverj.areas.networking.Channels;
import io.oliverj.areas.networking.packets.ShowParticlesPacket;
import io.oliverj.areas.particles.Particles;
import io.oliverj.areas.registry.BlockEntityRegister;
import io.oliverj.areas.utils.CrystalType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AreaFrameBlockEntity extends BlockEntity implements Visitable {

    private boolean visited = false;
    @SuppressWarnings("unused")
    public AreaFrameBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegister.AREA_FRAME_BLOCK_ENTITY, pos, state);
    }

    @Override
    public boolean visited() {
        return this.visited;
    }

    @Override
    public void setVisited(boolean value) {
        this.visited = value;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.visited = nbt.getBoolean("visited");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putBoolean("visited", this.visited);
        super.writeNbt(nbt);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public void chain(CrystalType activated) {
        if (!this.visited()) {
            Channels.RENDER_CHANNEL.serverHandle(world.getBlockEntity(pos)).send(new ShowParticlesPacket(pos));
            this.setVisited(true);
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
            for (Direction direction : directions) {
                AreaFrameBlockEntity beN = (AreaFrameBlockEntity) world.getBlockEntity(pos.offset(direction));
                if (beN != null) {
                    beN.chain(activated);
                }
            }
        }
    }
}
