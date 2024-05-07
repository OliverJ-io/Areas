package io.oliverj.areas.networking;

import io.oliverj.areas.Areas;
import io.wispforest.owo.network.OwoNetChannel;
import net.minecraft.util.Identifier;

public class Channels {
    public static final OwoNetChannel TEST_CHANNEL = OwoNetChannel.create(new Identifier(Areas.MOD_ID, "test_channel"));
    public static final OwoNetChannel RENDER_CHANNEL = OwoNetChannel.create(new Identifier(Areas.MOD_ID, "render_channel"));
}
