package io.oliverj.areas.gui;

import io.oliverj.areas.Areas;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.util.Identifier;

public class TestScreen extends BaseUIModelScreen<FlowLayout> {
    public TestScreen() {
        super(FlowLayout.class, DataSource.asset(new Identifier(Areas.MOD_ID, "test_ui")));
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        // TODO
    }
}
