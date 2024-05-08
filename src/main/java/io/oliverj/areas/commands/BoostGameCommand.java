package io.oliverj.areas.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.oliverj.areas.Areas;
import io.oliverj.areas.config.MagicLevel;
import io.oliverj.areas.magic.MagicalError;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.crash.CrashReport;

public class BoostGameCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = source.getPlayerOrThrow();
        if (Areas.CONFIG.magicLevel() == MagicLevel.MAGIC) {
            player.sendMessage(Text.literal("You do not have enough magic. Try changing the magic level."));
        } else if (Areas.CONFIG.magicLevel() == MagicLevel.MORE_MAGIC) {
            player.sendMessage(Text.literal("That's a lot of magic."));
            source.getServer().setCrashReport(new CrashReport("The server was overloaded by the magic.", new MagicalError()));
            source.getServer().stop(false);
        }
        return Command.SINGLE_SUCCESS;
    }
}
