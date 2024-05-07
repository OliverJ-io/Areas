package io.oliverj.areas.components;

import io.oliverj.areas.types.Status;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

import java.util.ArrayList;
import java.util.List;

public class StatusComponent implements ListComponent<Status> {

    List<Status> statuses = List.of();
    @Override
    public List getList() {
        return statuses;
    }

    @Override
    public void add(Status value) {
        statuses.add(value);
    }

    @Override
    public void add(int index, Status value) {
        statuses.add(index, value);
    }

    @Override
    public void push(Status value) {
        statuses.add(value);
    }

    @Override
    public void setList(List value) {

    }

    @Override
    public Status pop() {
        Status status = statuses.get(-1);
        statuses.remove(-1);
        return status;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.statuses = Status.unpackStatus(tag.getList("statuses", NbtList.COMPOUND_TYPE));
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.put("statuses", Status.packStatus(statuses));
    }
}
