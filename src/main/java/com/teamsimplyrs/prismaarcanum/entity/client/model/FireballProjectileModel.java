package com.teamsimplyrs.prismaarcanum.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class FireballProjectileModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "fireball_projectile"), "main");
	private final ModelPart all;

	public FireballProjectileModel(ModelPart root) {
		this.all = root.getChild("all");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition inner = all.addOrReplaceChild("inner", CubeListBuilder.create().texOffs(0, 0).addBox(6.0F, 3.75F, -10.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -8.0F, 8.0F));

		PartDefinition outer = all.addOrReplaceChild("outer", CubeListBuilder.create().texOffs(3, 9).addBox(8.25F, 3.25F, -7.75F, 2.25F, 2.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(3, 9).addBox(8.25F, 3.25F, -10.5F, 2.25F, 2.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(3, 9).addBox(5.5F, 3.25F, -10.5F, 2.25F, 2.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(3, 9).addBox(5.5F, 3.25F, -7.75F, 2.25F, 2.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(3, 9).addBox(5.5F, 5.75F, -7.75F, 2.25F, 2.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(3, 9).addBox(5.5F, 5.75F, -10.5F, 2.25F, 2.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(3, 9).addBox(8.25F, 5.75F, -7.75F, 2.25F, 2.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(3, 9).addBox(8.25F, 5.75F, -10.5F, 2.25F, 2.25F, 2.25F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -8.0F, 8.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}