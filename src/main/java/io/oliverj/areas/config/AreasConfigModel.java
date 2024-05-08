package io.oliverj.areas.config;

import io.oliverj.areas.Areas;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;

@Modmenu(modId = Areas.MOD_ID)
@Config(name = "areas-config", wrapperName = "AreasConfig")
public class AreasConfigModel {
    public MagicLevel magicLevel = MagicLevel.MAGIC;
}
