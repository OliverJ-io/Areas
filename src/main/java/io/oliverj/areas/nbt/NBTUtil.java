package io.oliverj.areas.nbt;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtInt;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.util.math.BlockPos;

public class NBTUtil {
    public static NbtList writeBlockPos(BlockPos pos) {
        NbtList list = new NbtList();
        list.add(NbtInt.of(pos.getX()));
        list.add(NbtInt.of(pos.getY()));
        list.add(NbtInt.of(pos.getZ()));
        return list;
    }

    public static BlockPos readBlockPos(NbtList data) {
        int x = data.getInt(0);
        int y = data.getInt(1);
        int z = data.getInt(2);
        return new BlockPos(x, y, z);
    }

    public static String writeEnum(Enum val) {
        return val.name();
    }

    public static Enum readEnum(String val, Class enumClass) {
        return Enum.valueOf(enumClass, val);
    }
}
