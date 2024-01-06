package com.teamsimplyrs.prismaarcanum.particle;

import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PAParticles {

    public static DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, PrismaArcanum.MODID);

    public static final RegistryObject<SimpleParticleType> IGNIS_PARTICLES = PARTICLE_TYPES.register("ignis_particles", () -> new SimpleParticleType(true));
    public static void register(IEventBus eventBus)
    {
        PARTICLE_TYPES.register(eventBus);
    }
}
