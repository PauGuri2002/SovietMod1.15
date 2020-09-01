package com.pauix.soviet.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.pauix.soviet.SovietMod;
import com.pauix.soviet.entities.KamikazeDog;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.MathHelper;

public class KamikazeDogModel<T extends KamikazeDog> extends EntityModel<T> {

    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer legFrontLeft;
    private final ModelRenderer legFrontRight;
    private final ModelRenderer legBackRight;
    private final ModelRenderer legBackLeft;
    private final ModelRenderer tail;
    private final ModelRenderer shead;
    private final ModelRenderer sbody;
    private final ModelRenderer supperBody;
    private final ModelRenderer slegFrontLeft;
    private final ModelRenderer slegFrontRight;
    private final ModelRenderer slegBackRight;
    private final ModelRenderer slegBackLeft;
    private final ModelRenderer stail;

    public boolean isSitting = false;

    public KamikazeDogModel() {

            textureWidth = 64;
            textureHeight = 32;

            head = new ModelRenderer(this);
            head.setRotationPoint(0.0F, 13.0F, -6.0F);
            head.setTextureOffset(0, 0).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 4.0F, 0.0F, false);
            head.setTextureOffset(0, 10).addBox(-1.5F, 0.0F, -7.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);
            head.setTextureOffset(16, 14).addBox(-3.0F, -5.0F, -2.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
            head.setTextureOffset(16, 14).addBox(1.0F, -5.0F, -2.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

            body = new ModelRenderer(this);
            body.setRotationPoint(0.0F, 11.3333F, 1.0F);
            body.setTextureOffset(18, 14).addBox(-3.0F, -1.3333F, -2.0F, 6.0F, 6.0F, 9.0F, 0.0F, false);
            body.setTextureOffset(40, 14).addBox(-3.0F, -4.3333F, 0.0F, 6.0F, 3.0F, 5.0F, 0.0F, false);
            body.setTextureOffset(22, 0).addBox(-4.0F, -2.3333F, -8.0F, 8.0F, 7.0F, 6.0F, 0.0F, false);

            legFrontLeft = new ModelRenderer(this);
            legFrontLeft.setRotationPoint(-2.0F, 16.0F, -6.0F);
            legFrontLeft.setTextureOffset(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

            legFrontRight = new ModelRenderer(this);
            legFrontRight.setRotationPoint(2.0F, 16.0F, -6.0F);
            legFrontRight.setTextureOffset(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

            legBackRight = new ModelRenderer(this);
            legBackRight.setRotationPoint(2.0F, 16.0F, 7.0F);
            legBackRight.setTextureOffset(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

            legBackLeft = new ModelRenderer(this);
            legBackLeft.setRotationPoint(-2.0F, 16.0F, 7.0F);
            legBackLeft.setTextureOffset(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

            tail = new ModelRenderer(this);
            tail.setRotationPoint(0.0F, 15.0F, 8.0F);
            tail.setTextureOffset(9, 18).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);


            shead = new ModelRenderer(this);
            shead.setRotationPoint(0.0F, 13.0F, -6.0F);
            shead.setTextureOffset(0, 0).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 4.0F, 0.0F, false);
            shead.setTextureOffset(0, 10).addBox(-1.5F, 0.0F, -7.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);
            shead.setTextureOffset(16, 14).addBox(-3.0F, -5.0F, -2.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
            shead.setTextureOffset(16, 14).addBox(1.0F, -5.0F, -2.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

            supperBody = new ModelRenderer(this);
            supperBody.setRotationPoint(0.0F, 14.0F, -7.0F);
            setRotationAngle(supperBody, -0.3491F, 0.0F, 0.0F);
            supperBody.setTextureOffset(22, 0).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 7.0F, 6.0F, 0.0F, false);

            sbody = new ModelRenderer(this);
            sbody.setRotationPoint(0.0F, 13.0F, -1.0F);
            setRotationAngle(sbody, -0.7854F, 0.0F, 0.0F);
            sbody.setTextureOffset(18, 14).addBox(-3.0F, 0.5355F, 0.7071F, 6.0F, 6.0F, 9.0F, 0.0F, false);
            sbody.setTextureOffset(40, 14).addBox(-3.0F, -2.4645F, 2.7071F, 6.0F, 3.0F, 5.0F, 0.0F, false);

            slegFrontLeft = new ModelRenderer(this);
            slegFrontLeft.setRotationPoint(-2.0F, 16.0F, -6.0F);
            setRotationAngle(slegFrontLeft, -0.3491F, 0.0F, 0.0F);
            slegFrontLeft.setTextureOffset(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

            slegFrontRight = new ModelRenderer(this);
            slegFrontRight.setRotationPoint(2.0F, 16.0F, -6.0F);
            setRotationAngle(slegFrontRight, -0.3491F, 0.0F, 0.0F);
            slegFrontRight.setTextureOffset(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

            slegBackRight = new ModelRenderer(this);
            slegBackRight.setRotationPoint(2.0F, 23.0F, 1.0F);
            setRotationAngle(slegBackRight, -1.5708F, 0.0F, 0.0F);
            slegBackRight.setTextureOffset(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

            slegBackLeft = new ModelRenderer(this);
            slegBackLeft.setRotationPoint(-9.0F, 16.0F, 7.0F);
            setRotationAngle(slegBackLeft, -1.5708F, 0.0F, 0.0F);
            slegBackLeft.setTextureOffset(0, 18).addBox(6.0F, 6.0F, 6.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

            stail = new ModelRenderer(this);
            stail.setRotationPoint(0.0F, 22.0F, 5.0F);
            setRotationAngle(stail, 1.5708F, 0.0F, 0.0F);
            stail.setTextureOffset(9, 18).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
    }


    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        if(!isSitting) {
            head.render(matrixStack, buffer, packedLight, packedOverlay);
            body.render(matrixStack, buffer, packedLight, packedOverlay);
            legFrontLeft.render(matrixStack, buffer, packedLight, packedOverlay);
            legFrontRight.render(matrixStack, buffer, packedLight, packedOverlay);
            legBackRight.render(matrixStack, buffer, packedLight, packedOverlay);
            legBackLeft.render(matrixStack, buffer, packedLight, packedOverlay);
            tail.render(matrixStack, buffer, packedLight, packedOverlay);
        } else {
            shead.render(matrixStack, buffer, packedLight, packedOverlay);
            sbody.render(matrixStack, buffer, packedLight, packedOverlay);
            supperBody.render(matrixStack, buffer, packedLight, packedOverlay);
            slegFrontLeft.render(matrixStack, buffer, packedLight, packedOverlay);
            slegFrontRight.render(matrixStack, buffer, packedLight, packedOverlay);
            slegBackRight.render(matrixStack, buffer, packedLight, packedOverlay);
            slegBackLeft.render(matrixStack, buffer, packedLight, packedOverlay);
            stail.render(matrixStack, buffer, packedLight, packedOverlay);
        }

    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if(!isSitting) {
            this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
            this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
            this.legBackRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.legBackLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
            this.legFrontRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
            this.legFrontLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.tail.rotateAngleX = ((entityIn.getHealth() / 20.0F) + 1.0F);
            //SovietMod.LOGGER.info("Dog tail angle: " + this.tail.rotateAngleX);
        }
    }
}
