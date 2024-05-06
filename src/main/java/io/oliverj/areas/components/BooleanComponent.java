package io.oliverj.areas.components;

import dev.onyxstudios.cca.api.v3.component.Component;

@SuppressWarnings("unused")
public interface BooleanComponent extends Component {
    boolean getValue();
    void setValue(boolean value);
}

