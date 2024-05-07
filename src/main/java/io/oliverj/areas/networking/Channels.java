package io.oliverj.areas.networking;

import io.oliverj.areas.Areas;
import io.wispforest.owo.Owo;
import io.wispforest.owo.network.OwoNetChannel;
import net.minecraft.util.Identifier;

public class Channels {
    public static final OwoNetChannel OPEN_SCREEN = OwoNetChannel.create(new Identifier(Areas.MOD_ID, "open_screen"));
}
