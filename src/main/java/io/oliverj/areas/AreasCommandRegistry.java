package io.oliverj.areas;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import io.oliverj.areas.commands.BoostGameCommand;
import io.oliverj.areas.commands.ModInfoCommand;
import io.oliverj.areas.commands.TestStatusCommand;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.TextArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.util.IConsumer;

public class AreasCommandRegistry {
    private AreasCommandRegistry() {}

    public static void register(
            CommandDispatcher<ServerCommandSource> dispatcher,
            CommandRegistryAccess commandRegistryAccess,
            CommandManager.RegistrationEnvironment registrationEnvironment
    ) {
        RootCommandNode<ServerCommandSource> rootNode = dispatcher.getRoot();

        LiteralCommandNode<ServerCommandSource> areasCommandsRootNode;
        {
            LiteralCommandNode<ServerCommandSource> enInfoNode = CommandManager.literal("info")
                    .executes(new ModInfoCommand())
                    .build();

            areasCommandsRootNode = CommandManager.literal("areas")
                    .executes(enInfoNode.getCommand())
                    .build();

            areasCommandsRootNode.addChild(enInfoNode);
        }

        IConsumer<LiteralCommandNode<ServerCommandSource>> registerNode = (node) -> {
            rootNode.addChild(node);
            areasCommandsRootNode.addChild(node);
        };

        registerNode.accept(CommandManager.literal("testStatus")
                        .then(RequiredArgumentBuilder.argument("name", TextArgumentType.text()))
                                .executes(new TestStatusCommand())
                .build());

        registerNode.accept(CommandManager.literal("boostServer")
                .executes(new BoostGameCommand())
                .build());

        rootNode.addChild(areasCommandsRootNode);
    }
}
