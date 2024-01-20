package com.teamsimplyrs.prismaarcanum.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import com.teamsimplyrs.prismaarcanum.item.wands.AbstractWand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiWandHUD implements IGuiOverlay{

    public static GuiWandHUD WandHUDOverlay = new GuiWandHUD();
    private static final Minecraft minecraft = Minecraft.getInstance();
    private static final Logger LOG = LogManager.getLogger();

    private static final ResourceLocation WAND_HUD = new ResourceLocation(PrismaArcanum.MODID, "textures/gui/wand_spellview_gui.png");
    private static final ResourceLocation WAND_AFFINITY = new ResourceLocation(PrismaArcanum.MODID, "textures/gui/wand_affinity_overlay.png");

    public static boolean shouldDisplayWandHUD()
    {
        ItemStack mainHand = minecraft.player.getMainHandItem();
        return mainHand.getItem() instanceof AbstractWand;
    }


    public static void renderHUD(ForgeGui gui, GuiGraphics guiGraphics, float x, int width, int height)
    {
        if (shouldDisplayWandHUD())
        {
            PoseStack pose = guiGraphics.pose();
            AbstractWand wand = (AbstractWand) minecraft.player.getMainHandItem().getItem();
            int maxAffinity = wand.maxAffinity;

            int offsetX = 10;
            int offsetY = minecraft.getWindow().getGuiScaledHeight() - 5;
            int affinityDisplayWidth = wand.currentAffinity;

            guiGraphics.blit(WAND_HUD, offsetX, offsetY-50, 0, 0, 64, 64, 64, 64);

        }
    }

    @Override
    public void render(ForgeGui forgeGui, GuiGraphics guiGraphics, float v, int i, int i1) {
        renderHUD(forgeGui, guiGraphics, v, i, i1);
    }
}
