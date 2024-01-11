package com.teamsimplyrs.prismaarcanum.events;

import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import com.teamsimplyrs.prismaarcanum.item.wands.IgnisWand;
import com.teamsimplyrs.prismaarcanum.particle.PAParticles;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.Vec3;
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
        if(event.player.getUseItem().getItem() instanceof IgnisWand){
            IgnisWand wand = (IgnisWand) event.player.getUseItem().getItem();
            if(wand.isBeingUsed()){
                Vec3 spawnlocation = event.player.getEyePosition().add(event.player.getLookAngle().multiply(1, 1, 1));
                event.player.level().addParticle(PAParticles.IGNIS_PARTICLES.get(),
                        spawnlocation.x, spawnlocation.y, spawnlocation.z,
                        event.player.getLookAngle().x*1, event.player.getLookAngle().y*1, event.player.getLookAngle().z*1
                );
                event.player.playSound(SoundEvents.FIRECHARGE_USE);
            }
        }
        //LOGGER.info(event.player.getItemInHand(InteractionHand.MAIN_HAND).getItem());
    }

    @SubscribeEvent
    public static void wandUseStart(LivingEntityUseItemEvent.Start event)
    {
        Vec3 spawnlocation = event.getEntity().getEyePosition().add(event.getEntity().getLookAngle().multiply(1, 1, 1));
        LOGGER.info("Using item "+event.getItem()+" at "+event.getEntity().getLookAngle().x + " and " + event.getEntity().getLookAngle().z);

//        event.getEntity().level().addParticle(PAParticles.IGNIS_PARTICLES.get(),
//                spawnlocation.x, spawnlocation.y, spawnlocation.z,
//                event.getEntity().getLookAngle().x*1, event.getEntity().getLookAngle().y*1, event.getEntity().getLookAngle().z*1
//        );
    }

    @SubscribeEvent
    public static void wandUseStop(LivingEntityUseItemEvent.Stop event)
    {
        Vec3 spawnlocation = event.getEntity().getEyePosition().add(event.getEntity().getLookAngle().multiply(1, 1, 1));
        event.getEntity().level().addParticle(PAParticles.IGNIS_PARTICLES.get(),
                spawnlocation.x, spawnlocation.y, spawnlocation.z,
                event.getEntity().getLookAngle().x*1, event.getEntity().getLookAngle().y*1, event.getEntity().getLookAngle().z*1
        );
    }
}
