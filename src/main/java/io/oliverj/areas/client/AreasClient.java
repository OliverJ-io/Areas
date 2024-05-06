package io.oliverj.areas.client;

import io.oliverj.areas.client.rendering.AreaCoreBlockEntityRenderer;
import io.oliverj.areas.registry.BlockEntityRegister;
import io.oliverj.areas.registry.PacketRegister;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;


@Environment(EnvType.CLIENT)
public class AreasClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registries();
        networking_registration();
    }

    public void registries() {
        BlockEntityRendererFactories.register(BlockEntityRegister.AREA_CORE_BLOCK_ENTITY, AreaCoreBlockEntityRenderer::new);
    }

    public void networking_registration() {
        PacketRegister.registerClientDeferredClientSide();
    }
}
