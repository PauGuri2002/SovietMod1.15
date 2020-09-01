package com.pauix.soviet.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.pauix.soviet.SovietMod;
import com.pauix.soviet.client.model.KamikazeDogModel;
import com.pauix.soviet.entities.KamikazeDog;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class KamikazeDogRenderer extends MobRenderer<KamikazeDog, KamikazeDogModel<KamikazeDog>> {

    protected final ResourceLocation TEXTURE = new ResourceLocation(SovietMod.MOD_ID, "textures/entity/kamikaze_dog.png");
    protected final ResourceLocation ANGRY_TEXTURE = new ResourceLocation(SovietMod.MOD_ID, "textures/entity/kamikaze_dog_angry.png");


    public KamikazeDogRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KamikazeDogModel<>(), 0.5f);
    }

    @Override
    public void render(KamikazeDog entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        boolean state = entityIn.isSitting();
        KamikazeDogModel<KamikazeDog> model = this.getEntityModel();
        model.isSitting = state;

        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(KamikazeDog entity) {
        return entity.isAngry() ? ANGRY_TEXTURE : TEXTURE;
    }
}
