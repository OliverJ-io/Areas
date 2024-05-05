package io.oliverj.areas;

import io.oliverj.areas.config.AreasConfig;
import io.oliverj.areas.registry.ArtifactRegister;
import io.oliverj.areas.registry.BlockEntityRegister;
import io.oliverj.areas.registry.BlockRegister;
import io.oliverj.areas.registry.ItemRegister;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;

import java.lang.reflect.Field;

public class Areas implements ModInitializer {

    public static final String MOD_ID = "areas";
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
    }

    public void special_registration() {
        FieldRegistrationHandler.register(ArtifactRegister.class, MOD_ID, false);
    }
}
