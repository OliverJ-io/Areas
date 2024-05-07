package io.oliverj.areas.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.oliverj.areas.networking.Channels;
import io.oliverj.areas.networking.packets.TestPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

public class ModInfoCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        PlayerEntity player = source.getPlayerOrThrow();
        Channels.TEST_CHANNEL.serverHandle(player).send(new TestPacket("Hello Client"));
        return 0;
    }
}
