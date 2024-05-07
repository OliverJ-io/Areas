package io.oliverj.areas.client;

import io.oliverj.areas.client.rendering.AreaCoreBlockEntityRenderer;
import io.oliverj.areas.networking.packets.ShowParticlesPacket;
import io.oliverj.areas.particles.Particles;
import io.oliverj.areas.registry.BlockEntityRegister;
import io.oliverj.areas.registry.BlockRegister;
import io.oliverj.areas.registry.PacketRegister;
import io.wispforest.owo.particles.ClientParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.particle.ParticleTypes;


@Environment(EnvType.CLIENT)
public class AreasClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registries();
        networking_registration();
        particle_magic();
    }

    public void registries() {
        BlockEntityRendererFactories.register(BlockEntityRegister.AREA_CORE_BLOCK_ENTITY, AreaCoreBlockEntityRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.AREA_CORE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.AREA_FRAME, RenderLayer.getCutout());
    }

    public void networking_registration() {
        PacketRegister.registerClientDeferredClientSide();
    }

    public void particle_magic() {
        Particles.CUBE.setHandler(((world, pos, data) -> {
            ClientParticles.setParticleCount(5);
            ClientParticles.spawnCubeOutline(ParticleTypes.END_ROD, world, pos, 1, .01f);
        }));
        Particles.PARTICLE_CONTROLLER.register(ShowParticlesPacket.class, ((world, pos, data) -> {
            Particles.CUBE.spawn(world, pos);
            Particles.BREAK_BLOCK_PARTICLES.spawn(world, pos);
        }));
    }
}
