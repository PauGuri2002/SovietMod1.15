package com.pauix.soviet.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.pauix.soviet.entities.KamikazeDog;
import com.pauix.soviet.entities.Sturgeon;
import net.minecraft.client.renderer.entity.model.*;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class SturgeonModel<T extends Sturgeon> extends SegmentedModel<T> {

    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer finLeft;
    private final ModelRenderer finRight;
    private final ModelRenderer tail;

	public SturgeonModel() {
        textureWidth = 32;
        textureHeight = 32;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-1.0F, -3.0F, 1.0F, 2.0F, 3.0F, 7.0F, 0.0F, false);
        body.setTextureOffset(0, 8).addBox(0.0F, -4.0F, 1.0F, 0.0F, 1.0F, 6.0F, 0.0F, false);
        body.setTextureOffset(0, 2).addBox(0.0F, 0.0F, 5.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 22.0F, 0.0F);
        head.setTextureOffset(0, 0).addBox(-1.0F, 1.0F, -3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(11, 0).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);

        finLeft = new ModelRenderer(this);
        finLeft.setRotationPoint(-1.0F, 23.0F, 0.0F);
        setRotationAngle(finLeft, 0.0F, 0.0F, 0.6109F);
        finLeft.setTextureOffset(0, 1).addBox(0.5736F, 0.8192F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);

        finRight = new ModelRenderer(this);
        finRight.setRotationPoint(1.0F, 23.0F, 0.0F);
        setRotationAngle(finRight, 0.0F, 0.0F, -0.6109F);
        finRight.setTextureOffset(0, 0).addBox(-0.5736F, 0.8192F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setRotationPoint(0.0F, 24.0F, 8.0F);
        tail.setTextureOffset(0, 4).addBox(0.0F, -4.0F, 0.0F, 0.0F, 4.0F, 6.0F, 0.0F, false);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.body, this.head, this.finRight, this.finLeft, this.tail);
    }
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = 1.0F;
        if (!entityIn.isInWater()) {
            f = 1.5F;
        }

        this.tail.rotateAngleY = -f * 0.45F * MathHelper.sin(0.6F * ageInTicks);
    }

}
