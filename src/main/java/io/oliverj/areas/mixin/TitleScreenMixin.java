package io.oliverj.areas.mixin;

import io.oliverj.areas.gui.TestScreen;
import io.oliverj.areas.networking.Channels;
import io.oliverj.areas.networking.packets.OpenScreenPacket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Inject(method = "render", at = @At("HEAD"))
    private void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        Text CREDIT = Text.literal("Areas was created by Oliver Johnson");
        Text HOV = Text.literal("Modified by Areas").styled((style) -> {
            return style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, CREDIT));
        });
        context.drawText(MinecraftClient.getInstance().textRenderer, HOV, 10, 10, 0xffffff, false);
    }
}
