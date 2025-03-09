package com.teamsimplyrs.prismaarcanum.client.vfx;

import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AquaVFX {

    @OnlyIn(Dist.CLIENT)
    public static void BasicWaterSurroundFX() {
        Minecraft mc = Minecraft.getInstance();
        ResourceLocation waterShader = new ResourceLocation(PrismaArcanum.MODID, "shaders/post/water_aura.json");
        mc.gameRenderer.loadEffect(waterShader);
        MinecraftForge.EVENT_BUS.register(new Object() {
            int ticks = 100;

            @SubscribeEvent
            public void onClientTick(TickEvent.ClientTickEvent event) {
                if (ticks-- <= 0) {
                    mc.gameRenderer.shutdownEffect();
                    MinecraftForge.EVENT_BUS.unregister(this);
                }
            }
        });
    }

}
