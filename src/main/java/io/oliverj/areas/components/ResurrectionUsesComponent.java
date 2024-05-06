package io.oliverj.areas.components;

import dev.onyxstudios.cca.api.v3.component.Component;

@SuppressWarnings("unused")
public interface ResurrectionUsesComponent extends Component {
    int getUses();
    void setUses(int uses);
    void decrementUses();
}
