package com.fhannenheim.boostboots.model;// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class BootModel extends BipedModel<LivingEntity> {
	private final ModelRenderer RightRocket;
	private final ModelRenderer LeftRocket;

	public BootModel() {
        super(1.0F, 0, 64, 64);
        textureWidth = 64;
		textureHeight = 64;

        this.bipedBody.showModel = false;
        this.bipedRightArm.showModel = false;
        this.bipedLeftArm.showModel = false;
        this.bipedHead.showModel = false;
        this.bipedHeadwear.showModel = false;
        this.bipedRightLeg.showModel = true;
        this.bipedLeftLeg.showModel = true;

		RightRocket = new ModelRenderer(this);
		RightRocket.setRotationPoint(-4.1F, 7.5F, 0.0F);
		RightRocket.setTextureOffset(0, 0).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		RightRocket.setTextureOffset(12, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		RightRocket.setTextureOffset(12, 3).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		RightRocket.setTextureOffset(12, 5).addBox(1.5F, 2F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		LeftRocket = new ModelRenderer(this);
		LeftRocket.setRotationPoint(4.1F, 7.5F, 0.0F);
		LeftRocket.setTextureOffset(0, 0).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		LeftRocket.setTextureOffset(12, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		LeftRocket.setTextureOffset(12, 3).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		LeftRocket.setTextureOffset(12, 5).addBox(-3.2F, 2F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		this.bipedLeftLeg.addChild(LeftRocket);
		this.bipedRightLeg.addChild(RightRocket);
	}


	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}