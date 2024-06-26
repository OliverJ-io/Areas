package io.oliverj.areas.types;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

import java.util.LinkedList;
import java.util.List;

public class Status {

    @Getter
    @Setter
    private String name;

    public static LinkedList<Status> unpackStatus(NbtList nbtL) {
        LinkedList<Status> statuses = new LinkedList<>();
        for (int i=0;i<nbtL.size();i++) {
            NbtCompound nbt = nbtL.getCompound(i);
            Status status = new Status(nbt.getString("name"));
            statuses.add(status);
        }
        return statuses;
    }

    public static NbtList packStatus(LinkedList<Status> statuses) {
        NbtList statusesNBT = new NbtList();
        for (Status status : statuses) {
            NbtCompound nbt = new NbtCompound();
            nbt.putString("name", status.getName());
            statusesNBT.add(nbt);
        }
        return statusesNBT;
    }

    public Status(String name) {
        setName(name);
    }
}
