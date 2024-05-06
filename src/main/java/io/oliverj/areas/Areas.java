package io.oliverj.areas;

import io.oliverj.areas.config.AreasConfig;
import io.oliverj.areas.registry.*;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Areas implements ModInitializer {

    public static final String MOD_ID = "areas";
    public static final Logger LOGGER = LoggerFactory.getLogger("areas");
    public static final AreasConfig CONFIG = AreasConfig.createAndLoad();
    @Override
    public void onInitialize() {
        registries();
        if (CONFIG.enableArtifacts()) {
            special_registration();
        }
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

    public void special_registration() {
        FieldRegistrationHandler.register(ArtifactRegister.class, MOD_ID, false);
        LOGGER.info("Finished Registration - SPECIAL");
    }
}
