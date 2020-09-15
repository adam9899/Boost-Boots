package com.fhannenheim.boostboots.compat;

import com.fhannenheim.boostboots.BoostBoots;
import com.fhannenheim.boostboots.model.BootModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CuriosCompat {
    private static final ResourceLocation BOOT_TEXTURE = new ResourceLocation(BoostBoots.MOD_ID,
            "textures/armor/boots.png");

    public static ICapabilityProvider initCapabilities() {
        ICurio curio = new ICurio() {
            private Object model;

            @Override
            public void playRightClickEquipSound(LivingEntity livingEntity) {
                livingEntity.world.playSound(null, new BlockPos(livingEntity.getPositionVec()),
                        SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, SoundCategory.NEUTRAL, 1.0f, 1.0f);
            }

            @Nonnull
            @Override
            public DropRule getDropRule(LivingEntity livingEntity) {
                return DropRule.ALWAYS_DROP;
            }

            @Override
            public boolean canRightClickEquip() {
                return true;
            }

            @Override
            public boolean canRender(String identifier, int index, LivingEntity livingEntity) {
                return true;
            }

            @Override
            public void render(String identifier, int index, MatrixStack matrixStack,
                               IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing,
                               float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
                               float headPitch) {

                if (!(this.model instanceof BootModel)) {
                    model = new BootModel();
                }
                BootModel boots = (BootModel) this.model;
                CuriosCompat.followLegs(livingEntity, boots.bipedLeftLeg, boots.bipedRightLeg);
                IVertexBuilder vertexBuilder = ItemRenderer
                        .getBuffer(renderTypeBuffer, boots.getRenderType(BOOT_TEXTURE), false,
                                false);
                boots.render(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            }
        };
        return new ICapabilityProvider() {
            private final LazyOptional<ICurio> curioOpt = LazyOptional.of(() -> curio);

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap,
                                                     @Nullable Direction side) {

                return CuriosCapability.ITEM.orEmpty(cap, curioOpt);
            }
        };
    }

    public static void followLegs(LivingEntity livingEntity, ModelRenderer leftLeg, ModelRenderer rightLeg) {
        EntityRenderer<? super LivingEntity> render = Minecraft.getInstance().getRenderManager().getRenderer(livingEntity);
        if (render instanceof LivingRenderer) {
            @SuppressWarnings("unchecked") LivingRenderer<LivingEntity, EntityModel<LivingEntity>> livingRenderer = (LivingRenderer<LivingEntity, EntityModel<LivingEntity>>) render;
            EntityModel<LivingEntity> entityModel = livingRenderer.getEntityModel();
            if (entityModel instanceof BipedModel) {
                leftLeg.copyModelAngles(((BipedModel<LivingEntity>) entityModel).bipedLeftLeg);
                rightLeg.copyModelAngles(((BipedModel<LivingEntity>) entityModel).bipedRightLeg);
            }
        }

    }
}
