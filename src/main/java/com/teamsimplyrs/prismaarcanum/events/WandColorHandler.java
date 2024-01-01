package com.teamsimplyrs.prismaarcanum.events;

import com.teamsimplyrs.prismaarcanum.registry.PAItemRegistry;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WandColorHandler {

    @SubscribeEvent
    public static void registerWandColors(RegisterColorHandlersEvent.Item event)
    {
        event.getItemColors().register(new WandColors(),
                PAItemRegistry.IGNIS_WAND.get(), PAItemRegistry.AQUA_WAND.get(), PAItemRegistry.FULGUR_WAND.get(),
                PAItemRegistry.TERRA_WAND.get(), PAItemRegistry.GELUM_WAND.get(), PAItemRegistry.NATURA_WAND.get(),
                PAItemRegistry.VENTUS_WAND.get(), PAItemRegistry.LUX_WAND.get(), PAItemRegistry.NOX_WAND.get()
                );
    }

}
