package io.oliverj.areas.block.entity;

import io.oliverj.areas.Areas;
import io.oliverj.areas.inventory.ImplementedInventory;
import io.oliverj.areas.item.InversionCrystalItem;
import io.oliverj.areas.item.NullCrystalItem;
import io.oliverj.areas.nbt.NBTUtil;
import io.oliverj.areas.registry.BlockEntityRegister;
import io.oliverj.areas.utils.CrystalType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AreaCoreBlockEntity extends BlockEntity implements ImplementedInventory {

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private List<BlockPos> corners = List.of(new BlockPos(0, 0, 0));

    private CrystalType activated = CrystalType.NONE;
    public AreaCoreBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegister.AREA_CORE_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        List<BlockPos> poslist = new ArrayList<>();
        Inventories.readNbt(nbt, items);
        NbtList nbtL = (NbtList) nbt.get("corners");
        for (int i = 0; i < nbtL.size(); i++) {
            poslist.add(NBTUtil.readBlockPos(nbtL.getList(i)));
        }
        this.corners = poslist;
        this.activated = (CrystalType) NBTUtil.readEnum(nbt.getString("activated"), CrystalType.class);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, items);
        NbtList nbtL = new NbtList();
        for (BlockPos pos : corners) {
            nbtL.add(NBTUtil.writeBlockPos(pos));
        }
        nbt.put("corners", nbtL);
        nbt.putString("activated", NBTUtil.writeEnum(this.activated));
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

    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }

    public ItemStack getRenderStack() {
        return this.items.get(0);
    }

    public static void tick(World world, BlockPos pos, BlockState state, AreaCoreBlockEntity be) {
        world.updateListeners(pos, state, state, 3);
        if (!be.getStack(0).isEmpty() && be.activated == CrystalType.NONE) {
            ItemStack stack = be.getStack(0);
            if (stack.getItem() instanceof NullCrystalItem && be.activated == CrystalType.NONE) {
                be.activated = CrystalType.NULL;
                Areas.LOGGER.info("Null Type");
            } else if (stack.getItem() instanceof InversionCrystalItem && be.activated == CrystalType.NONE) {
                be.activated = CrystalType.INVERSION;
                Areas.LOGGER.info("Inversion Type");
            }
        } else if (be.getStack(0).isEmpty() && be.activated != CrystalType.NONE) {
            be.activated = CrystalType.NONE;
            Areas.LOGGER.info("None Type");
        }
    }
}
