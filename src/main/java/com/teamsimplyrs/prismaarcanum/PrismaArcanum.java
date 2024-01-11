package com.teamsimplyrs.prismaarcanum;

import com.mojang.logging.LogUtils;
import com.teamsimplyrs.prismaarcanum.entity.client.renderer.FireballProjectileRenderer;
import com.teamsimplyrs.prismaarcanum.events.WandColorHandler;
import com.teamsimplyrs.prismaarcanum.particle.PAParticles;
import com.teamsimplyrs.prismaarcanum.registry.*;
import com.teamsimplyrs.prismaarcanum.screen.PAMenuTypes;
import com.teamsimplyrs.prismaarcanum.screen.SpellNexusScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PrismaArcanum.MODID)
public class PrismaArcanum
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "prismaarcanum";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public PrismaArcanum()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        PACreativeTabsRegistry.register(modEventBus);
        PAItemRegistry.register(modEventBus);
        PABlockRegistry.register(modEventBus);
        PABlockEntityRegistry.register(modEventBus);
        PAMenuTypes.register(modEventBus);
        PAParticles.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.register(WandColorHandler.class);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the teamsimplyrs block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
        {
            event.accept(PAItemRegistry.RAW_IGNIS);
            event.accept(PAItemRegistry.IGNIS_WAND);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(PAMenuTypes.SPELL_NEXUS_MENU.get(), SpellNexusScreen::new);
            EntityRenderers.register(PAEntities.FIREBALL_PROJECTILE.get(), FireballProjectileRenderer::new);
        }
    }
}
