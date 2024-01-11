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
        if (event.player.getUseItem().getItem() instanceof IgnisWand) {
            IgnisWand wand = (IgnisWand) event.player.getUseItem().getItem();
            if (wand.isBeingUsed()) {
                Vec3 lookVector = event.player.getLookAngle();

                // Calculate a perpendicular vector to the look vector
                Vec3 perpendicularVector = new Vec3(lookVector.z, 0, -lookVector.x).normalize();

                // Calculate the spawn location at a radius of 2f
                Vec3 spawnLocation = event.player.getEyePosition().add(lookVector.multiply(2f,2f,2f));

                // Add particles with velocity towards the origin spawnLocation
                for (int i = 0; i < 200; i++) {
                    double angle = 2 * Math.PI * i / 200;
                    double x = spawnLocation.x + 2f * Math.cos(angle) * perpendicularVector.x;
                    double y = spawnLocation.y + 2f * Math.sin(angle);
                    double z = spawnLocation.z + 2f * Math.cos(angle) * perpendicularVector.z;

                    double velocityX = (spawnLocation.x - x) * 0.1;
                    double velocityY = (spawnLocation.y - y) * 0.1;
                    double velocityZ = (spawnLocation.z - z) * 0.1;

                    event.player.level().addParticle(PAParticles.IGNIS_PARTICLES.get(), x, y, z, velocityX, velocityY, velocityZ);
                }

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
