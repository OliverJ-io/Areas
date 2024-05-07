package io.oliverj.areas.registry;

import io.oliverj.areas.networking.Channels;
import io.oliverj.areas.networking.packets.TestPacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;

public class PacketRegister {
    public static void registerClientDeferredServerSide() {
        Channels.TEST_CHANNEL.registerClientboundDeferred(TestPacket.class);
    }

    @Environment(EnvType.CLIENT)
    public static void registerClientDeferredClientSide() {
        Channels.TEST_CHANNEL.registerClientbound(TestPacket.class, ((message, access) -> {
            access.player().sendMessage(Text.literal("Received Test: " + message));
        }));
    }
}
