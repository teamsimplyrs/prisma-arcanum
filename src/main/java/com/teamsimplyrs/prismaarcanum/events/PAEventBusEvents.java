package com.teamsimplyrs.prismaarcanum.events;

import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import com.teamsimplyrs.prismaarcanum.particle.PAParticles;
import com.teamsimplyrs.prismaarcanum.particle.custom.IgnisParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PrismaArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PAEventBusEvents {
    @SubscribeEvent
    public static void registerParticleProviders(final RegisterParticleProvidersEvent particleProvidersEvent)
    {
        Minecraft.getInstance().particleEngine.register(PAParticles.IGNIS_PARTICLES.get(), IgnisParticles.Provider::new);
    }

}
