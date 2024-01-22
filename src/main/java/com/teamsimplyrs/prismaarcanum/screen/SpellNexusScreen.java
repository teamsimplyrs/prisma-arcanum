package com.teamsimplyrs.prismaarcanum.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.logging.LogUtils;
import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import com.teamsimplyrs.prismaarcanum.item.wands.AbstractWand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.player.Inventory;
import org.slf4j.Logger;

public class SpellNexusScreen extends AbstractContainerScreen<SpellNexusMenu> {

    private static final Logger LOGGER = LogUtils.getLogger();

    private static final ResourceLocation MENU_TEXTURE = new ResourceLocation(PrismaArcanum.MODID, "textures/gui/spell_nexus_gui.png");
    private static final ResourceLocation MENU_SPELL_SLOT_TEXTURE = new ResourceLocation(PrismaArcanum.MODID, "textures/gui/spell_nexus_spell_slot.png");
    private static SpellNexusMenu menu;

    private Font font;
    public SpellNexusScreen(SpellNexusMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.menu = pMenu;
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 30000;
//        this.titleLabelX = (width - this.imageWidth)/2;
        this.titleLabelY = 90;
        this.font = Minecraft.getInstance().font;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, MENU_TEXTURE);
//        this.imageHeight = 256;
//        this.imageWidth = 256;
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight - 50) / 2;

        guiGraphics.blit(MENU_TEXTURE, x, y, 0, 0, imageWidth, imageHeight+60);

        if (menu.getWandInSlot().getItem() instanceof AbstractWand)
        {
            // Menu Spell Hologram slots
            guiGraphics.blit(MENU_SPELL_SLOT_TEXTURE, width/2-16, height/2-121, 0, 0, 32, 32,32,32);
            guiGraphics.blit(MENU_SPELL_SLOT_TEXTURE, width/2-16, height/2-25, 0, 0, 32, 32,32,32);
            guiGraphics.blit(MENU_SPELL_SLOT_TEXTURE, width/2-80+16, height/2-73, 0, 0, 32, 32,32,32);
            guiGraphics.blit(MENU_SPELL_SLOT_TEXTURE, width/2+48-16, height/2-73, 0, 0, 32, 32,32,32);

            // Menu Infuse button
            guiGraphics.blit(MENU_TEXTURE, x+126, y+108, 208, 0, 48, 16);
            guiGraphics.drawString(font, "Infuse", x+132, y+213, 1298129);
        }
    }

    private void renderProgressBar(GuiGraphics guiGraphics, int x, int y)
    {

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
