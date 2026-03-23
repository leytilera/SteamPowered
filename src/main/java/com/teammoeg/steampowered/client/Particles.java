package com.teammoeg.steampowered.client;

import com.teammoeg.steampowered.SteamPowered;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraftforge.registries.RegistryObject;

public class Particles {
    public static final DeferredRegister<ParticleType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SteamPowered.MODID);

    public static final RegistryObject<SimpleParticleType> STEAM = REGISTER.register("steam", () -> new SimpleParticleType(false));
}
