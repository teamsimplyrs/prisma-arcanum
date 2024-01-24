package com.teamsimplyrs.prismaarcanum.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import com.teamsimplyrs.prismaarcanum.entity.client.PAModelLayers;
import com.teamsimplyrs.prismaarcanum.entity.client.model.FireballProjectileModel;
import com.teamsimplyrs.prismaarcanum.entity.projectile.FireballProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class FireballProjectileRenderer extends EntityRenderer<FireballProjectile> {

    public static final ResourceLocation FIREBALL_MODEL_TEXTURE = new ResourceLocation(PrismaArcanum.MODID, "textures/entity/fireball_projectile.png");
    private int fireballRot = 0;
    protected FireballProjectileModel model;
    public FireballProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new FireballProjectileModel<>(pContext.bakeLayer(PAModelLayers.FIREBALL_PROJECTILE_LAYER));
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(FireballProjectile fireballProjectile) {
        return FIREBALL_MODEL_TEXTURE;
    }

    @Override
    public void render(FireballProjectile pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        pPoseStack.pushPose();
        pPoseStack.scale((float) (pEntity.scale.x), (float) (pEntity.scale.y), (float) (pEntity.scale.z));
//        pPoseStack.rotateAround(new Quaternionf(1f, 1f, 1f, 1f), pEntity.getXRot()+1, pEntity.getYRot()+1, 1f);
//        pPoseStack.mulPose(Axis.XP.rotationDegrees(pEntity.getXRot()+1));
//        pPoseStack.mulPose(Axis.YP.rotationDegrees(pEntity.getYRot()+1));

        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(pBuffer, this.model.renderType(this.getTextureLocation(pEntity)), false, false);
        this.model.setupAnim(pEntity,0, 0,pEntity.tickCount,0,0);
        this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1f, 0f, 0f, 1f);
        pPoseStack.popPose();

        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }
}


