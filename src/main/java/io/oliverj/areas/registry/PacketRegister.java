package io.oliverj.areas.registry;

import io.oliverj.areas.gui.TestScreen;
import io.oliverj.areas.networking.Channels;
import io.oliverj.areas.networking.packets.OpenScreenPacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.CreditsScreen;

public class PacketRegister {
    public static void registerClientDeferredServerSide() {
        Channels.OPEN_SCREEN.registerClientboundDeferred(OpenScreenPacket.class);
    }

    @Environment(EnvType.CLIENT)
    public static void registerClientDeferredClientSide() {
        Channels.OPEN_SCREEN.registerClientbound(OpenScreenPacket.class, ((message, access) -> {
            MinecraftClient.getInstance().setScreen(new TestScreen());
        }));
    }
}
