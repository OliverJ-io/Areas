package io.oliverj.areas.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.oliverj.areas.registry.CardinalComponentRegister;
import io.oliverj.areas.types.Status;
import net.minecraft.command.argument.TextArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.LinkedList;

public class TestStatusCommand implements Command<ServerCommandSource> {

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        return exec(context, TextArgumentType.getTextArgument(context, "name"));
    }

    public static int exec(CommandContext<ServerCommandSource> context, Text rawNameText) throws CommandSyntaxException {
        ServerPlayerEntity targetPlayer = context.getSource().getPlayerOrThrow().getServer().getPlayerManager().getPlayer(context.getSource().getPlayerOrThrow().getUuid());
        if (rawNameText == null) {
            targetPlayer.getComponent(CardinalComponentRegister.STATUSES).setList(new LinkedList());
        } else {
            targetPlayer.getComponent(CardinalComponentRegister.STATUSES).add(new Status(rawNameText.toString()));
        }

        return Command.SINGLE_SUCCESS;
    }
}
