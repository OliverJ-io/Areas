package io.oliverj.areas.components;

import dev.onyxstudios.cca.api.v3.component.Component;

public interface ResurrectionUsesComponent extends Component {
    int getUses();
    void setUses(int uses);
    void decrementUses();
}
