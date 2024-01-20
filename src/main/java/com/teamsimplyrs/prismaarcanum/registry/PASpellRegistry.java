package com.teamsimplyrs.prismaarcanum.registry;

import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import com.teamsimplyrs.prismaarcanum.item.spells.interitus.IgnisFireball;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class PASpellRegistry {
    public static final DeferredRegister<SpellBase> PA_SPELL = DeferredRegister.create(new ResourceLocation(PrismaArcanum.MODID,"spell_registry"), PrismaArcanum.MODID);

    public static final RegistryObject<SpellBase> IGNIS_FIREBALL = PA_SPELL.register("ignis_fireball",()->new IgnisFireball());


    public static void register(IEventBus eventBus)
    {
        PA_SPELL.register(eventBus);
    }
}
