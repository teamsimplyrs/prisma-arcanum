package com.teamsimplyrs.prismaarcanum.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.logging.LogUtils;
import com.teamsimplyrs.prismaarcanum.entity.animations.PAAnimationDefinitions;
import com.teamsimplyrs.prismaarcanum.entity.projectile.FireballProjectile;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import org.slf4j.Logger;

public class FireballProjectileModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart all;
	private static final Logger LOGGER = LogUtils.getLogger();

	public FireballProjectileModel(ModelPart root) {
		this.all = root.getChild("all");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = all.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition inner = body.addOrReplaceChild("inner", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition outer = body.addOrReplaceChild("outer", CubeListBuilder.create().texOffs(3, 9).addBox(0.25F, -2.375F, 0.25F, 2.25F, 2.25F, 2.25F, new CubeDeformation(0.0F))
				.texOffs(3, 9).addBox(0.25F, -2.375F, -2.5F, 2.25F, 2.25F, 2.25F, new CubeDeformation(0.0F))
				.texOffs(3, 9).addBox(-2.5F, -2.375F, -2.5F, 2.25F, 2.25F, 2.25F, new CubeDeformation(0.0F))
				.texOffs(3, 9).addBox(-2.5F, -2.375F, 0.25F, 2.25F, 2.25F, 2.25F, new CubeDeformation(0.0F))
				.texOffs(3, 9).addBox(-2.5F, 0.125F, 0.25F, 2.25F, 2.25F, 2.25F, new CubeDeformation(0.0F))
				.texOffs(3, 9).addBox(-2.5F, 0.125F, -2.5F, 2.25F, 2.25F, 2.25F, new CubeDeformation(0.0F))
				.texOffs(3, 9).addBox(0.25F, 0.125F, 0.25F, 2.25F, 2.25F, 2.25F, new CubeDeformation(0.0F))
				.texOffs(3, 9).addBox(0.25F, 0.125F, -2.5F, 2.25F, 2.25F, 2.25F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return all;
	}

	@Override
	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(((FireballProjectile)pEntity).FIREBALL_ANIM_STATE, PAAnimationDefinitions.FIREBALL_PROJECTILE_ANIMATION, pAgeInTicks, 1.0F);
	}
}