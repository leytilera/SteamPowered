package com.teammoeg.steampowered.client;

import com.teammoeg.steampowered.SteamPowered;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.particles.ParticleType;

public class Particles {
    public static final DeferredRegister<ParticleType<?>> REGISTER = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, SteamPowered.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> STEAM = REGISTER.register("steam", () -> new SimpleParticleType(false));
}
