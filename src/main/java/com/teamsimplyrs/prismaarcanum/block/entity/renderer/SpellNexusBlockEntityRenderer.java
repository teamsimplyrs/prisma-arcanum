package com.teamsimplyrs.prismaarcanum.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamsimplyrs.prismaarcanum.block.entity.SpellNexusBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpellNexusBlockEntityRenderer implements BlockEntityRenderer<SpellNexusBlockEntity> {

    //    private static int renderStackRotationDegrees = 0;
    private static final Logger LOGGER = LogManager.getLogger();
    private static float countTracker = 0;
    private static float previousPartialTick = 0;

    public SpellNexusBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(SpellNexusBlockEntity blockEntity, float pPartialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int pPackedLight, int pPackedOverlay) {
        countTracker += pPartialTick/2f;

        if (countTracker > 72) {
            countTracker = 0;
        }

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack wandItemStack = blockEntity.getWandRenderStack();
        ItemStack spellItemStack1 = blockEntity.getSpellHologramRenderStack(1),
                spellItemStack2 = blockEntity.getSpellHologramRenderStack(2),
                spellItemStack3 = blockEntity.getSpellHologramRenderStack(3),
                spellItemStack4 = blockEntity.getSpellHologramRenderStack(4);


        // Manipulate renderStack transform values here
        // PushPose pushes to the poseStack to let the program know that the renderStack transform values are going to be manipulated
        // Wand Rendering:
        poseStack.pushPose();

        // WandRenderStack Translate values
        poseStack.translate(0.5f, 0.65f, 0.5f);

        // RenderStack Scale values
        poseStack.scale(0.6f, 0.6f, 0.6f);

        // RenderStack Rotation values
        poseStack.mulPose(Axis.XP.rotationDegrees(90));

        // Render the renderStack above the block
        itemRenderer.renderStatic(wandItemStack, ItemDisplayContext.FIXED, getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, blockEntity.getLevel(), 1);

        // Pop the pose stack
        poseStack.popPose();


        // Spell Hologram 1 Rendering:
        poseStack.pushPose();
        float orbitRadius1 = 0.5f;
        float orbitAngle1 = (float) (Math.toRadians(5 * countTracker));
        float xPos1 = (float) (Math.cos(orbitAngle1) * orbitRadius1);
        float zPos1 = (float) (Math.sin(orbitAngle1) * orbitRadius1);
        poseStack.translate(xPos1 + 0.5f, 0.8f, zPos1 + 0.5f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (-5*countTracker)));
        itemRenderer.renderStatic(spellItemStack1, ItemDisplayContext.GROUND, getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, blockEntity.getLevel(), 1);
        poseStack.popPose();

        // Spell Hologram 2 Rendering:
        poseStack.pushPose();
        float orbitRadius2 = 0.5f;
        float orbitAngle2 = (float) (Math.toRadians(5 * countTracker + 90)); // 90-degree phase difference
        float xPos2 = (float) (Math.cos(orbitAngle2) * orbitRadius2);
        float zPos2 = (float) (Math.sin(orbitAngle2) * orbitRadius2);
        poseStack.translate(xPos2 + 0.5f, 0.8f, zPos2 + 0.5f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (-5*countTracker)+90));
        itemRenderer.renderStatic(spellItemStack2, ItemDisplayContext.GROUND, getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, blockEntity.getLevel(), 1);
        poseStack.popPose();
//
        // Spell Hologram 3 Rendering:
        poseStack.pushPose();
        float orbitRadius3 = 0.5f;
        float orbitAngle3 = (float) (Math.toRadians(5 * countTracker + 180)); // 180-degree phase difference
        float xPos3 = (float) (Math.cos(orbitAngle3) * orbitRadius3);
        float zPos3 = (float) (Math.sin(orbitAngle3) * orbitRadius3);
        poseStack.translate(xPos3+0.5f, 0.8f, zPos3 + 0.5f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (-5*countTracker)+180));
        itemRenderer.renderStatic(spellItemStack3, ItemDisplayContext.GROUND, getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, blockEntity.getLevel(), 1);
        poseStack.popPose();
//
//        // Spell Hologram 4 Rendering:
        poseStack.pushPose();
        float orbitRadius4 = 0.5f;
        float orbitAngle4 = (float) (Math.toRadians(5 * countTracker + 270)); // 270-degree phase difference
        float xPos4 = (float) (Math.cos(orbitAngle4) * orbitRadius4);
        float zPos4 = (float) (Math.sin(orbitAngle4) * orbitRadius4);
        poseStack.translate(xPos4 + 0.5f, 0.8f, zPos4 + 0.5f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (-5*countTracker)+270));
        itemRenderer.renderStatic(spellItemStack4, ItemDisplayContext.GROUND, getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, blockEntity.getLevel(), 1);
        poseStack.popPose();
    }

    // A helper method
    private int getLightLevel(Level level, BlockPos pos) {
        int blockLight = level.getBrightness(LightLayer.BLOCK, pos);
        int skyLight = level.getBrightness(LightLayer.SKY, pos);

        return LightTexture.pack(blockLight, skyLight);
    }
}
