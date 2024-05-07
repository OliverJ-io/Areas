package io.oliverj.areas.components;

import dev.onyxstudios.cca.api.v3.component.Component;

import java.util.List;

public interface ListComponent<T> extends Component {
    List<T> getList();

    void setList(List<T> value);

    void add(T value);

    void add(int index, T value);

    T get(int index);

    boolean contains(String name);

    void push(T value);

    T pop();
}
