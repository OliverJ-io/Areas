package io.oliverj.areas.components;

import io.oliverj.areas.types.Status;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

import java.util.LinkedList;
import java.util.List;

public class StatusComponent implements ListComponent<Status> {

    private LinkedList<Status> statuses = new LinkedList<>();
    @Override
    public LinkedList<Status> getList() {
        return this.statuses;
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
    public Status get(int index) {
        return statuses.get(index);
    }

    @Override
    public boolean contains(String name) {
        return statuses.contains(new Status(name));
    }

    @Override
    public void push(Status value) {
        statuses.add(value);
    }

    @Override
    public void setList(LinkedList value) {
        this.statuses = value;
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
