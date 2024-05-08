package io.oliverj.areas.block.entity;

public interface Activatable<T> {
    T getActivated();
    void setActivated(T state);
}
