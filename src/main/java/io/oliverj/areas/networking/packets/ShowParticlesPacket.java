package io.oliverj.areas.networking.packets;

import io.wispforest.owo.particles.systems.ParticleSystem;
import net.minecraft.util.math.BlockPos;

public record ShowParticlesPacket(BlockPos pos) {}
