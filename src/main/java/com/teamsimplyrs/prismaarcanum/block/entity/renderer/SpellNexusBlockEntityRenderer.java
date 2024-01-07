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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.joml.Quaternionf;

import java.util.ArrayList;
import java.util.List;

public class SpellNexusBlockEntityRenderer implements BlockEntityRenderer<SpellNexusBlockEntity> {

//    private static int renderStackRotationDegrees = 0;

    public SpellNexusBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {

    }
    @Override
    public void render(SpellNexusBlockEntity blockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {

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
        poseStack.translate(0.5f, 0.8f, 0f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(90));
        itemRenderer.renderStatic(spellItemStack1, ItemDisplayContext.GROUND, getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, blockEntity.getLevel(), 1);
        poseStack.popPose();

        // Spell Hologram 2 Rendering:
        poseStack.pushPose();
        poseStack.translate(1f, 0.8f, 0.5f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(90));
        itemRenderer.renderStatic(spellItemStack2, ItemDisplayContext.GROUND, getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, blockEntity.getLevel(), 1);
        poseStack.popPose();
//
        // Spell Hologram 3 Rendering:
        poseStack.pushPose();
        poseStack.translate(0f, 0.8f, 0.5f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(90));
        itemRenderer.renderStatic(spellItemStack3, ItemDisplayContext.GROUND, getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, blockEntity.getLevel(), 1);
        poseStack.popPose();
//
//        // Spell Hologram 4 Rendering:
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.8f, 1f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(90));
        itemRenderer.renderStatic(spellItemStack4, ItemDisplayContext.GROUND, getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, blockEntity.getLevel(), 1);
        poseStack.popPose();
    }

    // A helper method
    private int getLightLevel(Level level, BlockPos pos)
    {
        int blockLight = level.getBrightness(LightLayer.BLOCK, pos);
        int skyLight = level.getBrightness(LightLayer.SKY, pos);

        return LightTexture.pack(blockLight, skyLight);
    }
}
