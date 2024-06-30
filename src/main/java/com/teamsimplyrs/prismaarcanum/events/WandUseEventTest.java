package com.teamsimplyrs.prismaarcanum.events;

import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import com.teamsimplyrs.prismaarcanum.item.wands.AbstractWand;
import com.teamsimplyrs.prismaarcanum.item.wands.IgnisWand;
import com.teamsimplyrs.prismaarcanum.registry.PASpellRegistry;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = PrismaArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WandUseEventTest {

    private static final Logger LOGGER = LogManager.getLogger();
    @SubscribeEvent
    public static void playerTickTest(TickEvent.PlayerTickEvent event)
    {
        if (event.player.getUseItem().getItem() instanceof IgnisWand) {
            IgnisWand wand = (IgnisWand) event.player.getUseItem().getItem();
            if (wand.isBeingUsed()) {
               SpellBase currentSpell = PASpellRegistry.getSpell(wand.current_spell_name);
               currentSpell.chargeSpell(event.player);
            }
        }
        if (event.player.getMainHandItem().getItem() instanceof AbstractWand wand)
        {
            if (wand.currentAffinity < wand.maxAffinity)
            {
                wand.currentAffinity++;
            }
//            LOGGER.info(wand.currentAffinity);
        }
    }

    @SubscribeEvent
    public static void wandUseStart(LivingEntityUseItemEvent.Start event)
    {
        LOGGER.info(PASpellRegistry.IGNIS_FIREBALL.get().spellName);
    }

    @SubscribeEvent
    public static void wandUseStop(LivingEntityUseItemEvent.Stop event)
    {

    }
}
