package com.teamsimplyrs.prismaarcanum.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import com.teamsimplyrs.prismaarcanum.entity.client.model.FireballProjectileModel;
import com.teamsimplyrs.prismaarcanum.entity.projectile.FireballProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FireballProjectileRenderer extends EntityRenderer<FireballProjectile> {


    public FireballProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(FireballProjectile fireballProjectile) {
        return new ResourceLocation(PrismaArcanum.MODID, "textures/entity/fireball_projectile.png");
    }

    @Override
    public void render(FireballProjectile pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }
}
