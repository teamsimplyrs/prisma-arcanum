package com.teamsimplyrs.prismaarcanum.registry;

import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class PACreativeTabsRegistry {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PrismaArcanum.MODID);

    public static final RegistryObject<CreativeModeTab> PA_CREATIVE_TAB =
            CREATIVE_TABS.register("prismaarcanum_creative_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(PAItemRegistry.RAW_IGNIS.get()))
                    .title(Component.translatable("creativetab.prismaarcanum_creative_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(PAItemRegistry.RAW_IGNIS.get());
                        output.accept(PAItemRegistry.RAW_VENTUS.get());
                        output.accept(PAItemRegistry.IGNIS_WAND.get());
                        output.accept((PAItemRegistry.AQUA_WAND.get()));
                        output.accept((PAItemRegistry.TERRA_WAND.get()));
                        output.accept((PAItemRegistry.FULGUR_WAND.get()));
                        output.accept((PAItemRegistry.GELUM_WAND.get()));
                        output.accept((PAItemRegistry.VENTUS_WAND.get()));
                        output.accept((PAItemRegistry.NATURA_WAND.get()));
                        output.accept((PAItemRegistry.LUX_WAND.get()));
                        output.accept((PAItemRegistry.NOX_WAND.get()));
                        output.accept(PABlockRegistry.SPELL_NEXUS.get());

                        output.accept(PAItemRegistry.IGNIS_SPELL_HOLOGRAM.get());
                        output.accept(PAItemRegistry.AQUA_SPELL_HOLOGRAM.get());
                        output.accept(PAItemRegistry.TERRA_SPELL_HOLOGRAM.get());
                        output.accept(PAItemRegistry.FULGUR_SPELL_HOLOGRAM.get());
                        output.accept(PAItemRegistry.GELUM_SPELL_HOLOGRAM.get());
                        output.accept(PAItemRegistry.VENTUS_SPELL_HOLOGRAM.get());
                        output.accept(PAItemRegistry.NATURA_SPELL_HOLOGRAM.get());
                        output.accept(PAItemRegistry.LUX_SPELL_HOLOGRAM.get());
                        output.accept(PAItemRegistry.NOX_SPELL_HOLOGRAM.get());
                    })
                    .build());



    public static void register(IEventBus eventBus)
    {
        CREATIVE_TABS.register(eventBus);
    }

}
