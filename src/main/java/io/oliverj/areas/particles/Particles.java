package io.oliverj.areas.particles;

import io.oliverj.areas.Areas;
import io.wispforest.owo.particles.ClientParticles;
import io.wispforest.owo.particles.systems.ParticleSystem;
import io.wispforest.owo.particles.systems.ParticleSystemController;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.joml.Vector3f;

public class Particles {
    public static final ParticleSystemController PARTICLE_CONTROLLER = new ParticleSystemController(new Identifier(Areas.MOD_ID, "particles"));
    public static final ParticleSystem<Void> CUBE = PARTICLE_CONTROLLER.registerDeferred(Void.class);
    public static final ParticleSystem<Void> BREAK_BLOCK_PARTICLES = PARTICLE_CONTROLLER.register(Void.class, (world, pos, data) -> {
        ClientParticles.persist();

        ClientParticles.setParticleCount(30);
        ClientParticles.spawnLine(ParticleTypes.DRAGON_BREATH, world, pos.add(.5, .5, .5), pos.add(.5, 2.5, .5), .015f);

        ClientParticles.randomizeVelocityOnAxis(.1, Direction.Axis.X);
        ClientParticles.spawn(ParticleTypes.CLOUD, world, pos.add(.5, 2.5, .5), 0);

        ClientParticles.reset();
    });

    public static final ParticleSystem<Void> CORE_BUILD_ERROR = PARTICLE_CONTROLLER.register(Void.class, ((world, pos, data) -> {
        ClientParticles.persist();

        ClientParticles.setParticleCount(10);
        ClientParticles.randomizeVelocity(.05);
        ClientParticles.spawn(ParticleTypes.EXPLOSION, world, pos.add(0, .5, 0), 0.015f);

        ClientParticles.reset();
    }));

}