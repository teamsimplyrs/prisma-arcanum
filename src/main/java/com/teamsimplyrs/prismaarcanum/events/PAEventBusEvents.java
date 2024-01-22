package com.teamsimplyrs.prismaarcanum.events;

import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import com.teamsimplyrs.prismaarcanum.block.entity.renderer.SpellNexusBlockEntityRenderer;
import com.teamsimplyrs.prismaarcanum.client.gui.GuiWandHUD;
import com.teamsimplyrs.prismaarcanum.entity.client.PAModelLayers;
import com.teamsimplyrs.prismaarcanum.entity.client.model.FireballProjectileModel;
import com.teamsimplyrs.prismaarcanum.particle.PAParticles;
import com.teamsimplyrs.prismaarcanum.particle.custom.IgnisParticles;
import com.teamsimplyrs.prismaarcanum.registry.PABlockEntityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PrismaArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(PAModelLayers.FIREBALL_PROJECTILE_LAYER, FireballProjectileModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiOverlaysEvent event)
    {
        event.registerAboveAll("wand_spellview_hud", GuiWandHUD.WandHUDOverlay);
    }
}
