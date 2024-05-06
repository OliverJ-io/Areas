package io.oliverj.areas.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import io.oliverj.areas.Areas;
import io.oliverj.areas.components.ResurrectionComponent;
import io.oliverj.areas.components.ResurrectionUsesComponent;
import net.minecraft.util.Identifier;

public class CardinalComponentRegister implements EntityComponentInitializer {
    public static ComponentKey<ResurrectionUsesComponent> RESURRECTION = ComponentRegistry.getOrCreate(new Identifier(Areas.MOD_ID, "resurrection"), ResurrectionUsesComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(RESURRECTION, player -> new ResurrectionComponent(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
