package com.teamsimplyrs.prismaarcanum.events;

import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import com.teamsimplyrs.prismaarcanum.block.entity.renderer.SpellNexusBlockEntityRenderer;
import com.teamsimplyrs.prismaarcanum.particle.PAParticles;
import com.teamsimplyrs.prismaarcanum.particle.custom.IgnisParticles;
import com.teamsimplyrs.prismaarcanum.registry.PABlockEntityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.EntityRenderersEvent;
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

    @SubscribeEvent
    public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerBlockEntityRenderer(PABlockEntityRegistry.SPELL_NEXUS_BE.get(), SpellNexusBlockEntityRenderer::new);
    }
}
