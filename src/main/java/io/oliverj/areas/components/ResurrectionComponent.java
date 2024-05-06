package io.oliverj.areas.components;

import dev.onyxstudios.cca.api.v3.item.ItemComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class ResurrectionComponent implements ResurrectionUsesComponent {

    private int uses = 10;

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public void setUses(int uses) {
        this.uses = uses;
    }

    @Override
    public void decrementUses() {
        this.uses--;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.uses = tag.getInt("uses");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putInt("uses", this.uses);
    }
}
