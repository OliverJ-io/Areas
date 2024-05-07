package io.oliverj.areas.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import io.oliverj.areas.Areas;
import io.oliverj.areas.components.StatusComponent;
import net.minecraft.util.Identifier;

public class CardinalComponentRegister implements EntityComponentInitializer {
    public static ComponentKey<StatusComponent> STATUSES = ComponentRegistry.getOrCreate(new Identifier(Areas.MOD_ID, "statuses"), StatusComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(STATUSES, player -> new StatusComponent(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
