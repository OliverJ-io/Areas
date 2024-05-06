package io.oliverj.areas.config;

import io.oliverj.areas.Areas;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RestartRequired;

@SuppressWarnings("unused")
@Modmenu(modId = Areas.MOD_ID)
@Config(name = "areas-config", wrapperName = "AreasConfig")
public class AreasConfigModel {
    @RestartRequired
    public boolean enableArtifacts = true;
}
