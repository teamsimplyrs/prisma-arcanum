package com.teamsimplyrs.prismaarcanum.particle;

import com.mojang.serialization.Codec;
import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import com.teamsimplyrs.prismaarcanum.particle.particleOptions.IgnisParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class PAParticles {

    public static DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, PrismaArcanum.MODID);

    public static final RegistryObject<ParticleType<IgnisParticleOptions>> IGNIS_PARTICLES = registerParticles("ignis_particles",true, IgnisParticleOptions.DESERIALIZER,(instance)-> IgnisParticleOptions.CODEC);
    public static void register(IEventBus eventBus)
    {
        PARTICLE_TYPES.register(eventBus);
    }

    private static <T extends ParticleOptions> RegistryObject<ParticleType<T>> registerParticles(String pKey, boolean pOverrideLimiter, ParticleOptions.Deserializer<T> pDeserializer, final Function<ParticleType<T>, Codec<T>> pCodecFactory) {
        return PARTICLE_TYPES.register(pKey,() -> new ParticleType<T>(pOverrideLimiter, pDeserializer) {
            public Codec<T> codec() {
                return pCodecFactory.apply(this);
            }
        });
    }
}
