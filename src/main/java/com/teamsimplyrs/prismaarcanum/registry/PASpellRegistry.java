package com.teamsimplyrs.prismaarcanum.registry;

import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import com.teamsimplyrs.prismaarcanum.item.spells.NullSpell;
import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import com.teamsimplyrs.prismaarcanum.item.spells.interitus.IgnisFireball;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class PASpellRegistry {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final DeferredRegister<SpellBase> PA_SPELL = DeferredRegister.create(new ResourceLocation(PrismaArcanum.MODID,"spell_registry"), PrismaArcanum.MODID);
    public static final Supplier<IForgeRegistry<SpellBase>> PA_SPELL_REGISTRY_SUPPLIER = PA_SPELL.makeRegistry(RegistryBuilder::new);
    public static final RegistryObject<SpellBase> IGNIS_FIREBALL = PA_SPELL.register("ignis_fireball",()->new IgnisFireball());

    public static final RegistryObject<SpellBase> NULL_SPELL = PA_SPELL.register("null_spell", NullSpell::new);

    public static void register(IEventBus eventBus)
    {
        PA_SPELL.register(eventBus);
        LOGGER.info("Spell registry registered ");
    }

    public static SpellBase getSpell(String spellName){
        ResourceLocation spellLocation = new ResourceLocation(PrismaArcanum.MODID,spellName);
        SpellBase toReturn = PA_SPELL_REGISTRY_SUPPLIER.get().getValue(spellLocation);
        return toReturn;
    }
}
