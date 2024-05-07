package io.oliverj.areas;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import io.oliverj.areas.commands.TestStatusCommand;
import io.oliverj.areas.registry.*;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Areas implements ModInitializer {

    public static final String MOD_ID = "areas";
    public static final Logger LOGGER = LoggerFactory.getLogger("areas");
    @Override
    public void onInitialize() {
        registries();
        special_registration();
        networking_registration();
        command_registration();
    }

    public void registries() {
        FieldRegistrationHandler.register(ItemRegister.class, MOD_ID, false);
        FieldRegistrationHandler.register(BlockRegister.class, MOD_ID, false);
        FieldRegistrationHandler.register(BlockEntityRegister.class, MOD_ID, false);
        FieldRegistrationHandler.register(StatusEffectRegister.class, MOD_ID, false);
        LOGGER.info("Finished Registration - NORMAL");
    }

    public void networking_registration() {
        PacketRegister.registerClientDeferredServerSide();
    }

    public void command_registration() {
        CommandRegistrationCallback.EVENT.register(AreasCommandRegistry::register);
    }

    public void special_registration() {
        FieldRegistrationHandler.register(ArtifactRegister.class, MOD_ID, false);
        LOGGER.info("Finished Registration - SPECIAL");
    }
}
