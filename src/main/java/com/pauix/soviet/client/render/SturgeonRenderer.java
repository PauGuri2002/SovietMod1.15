package com.pauix.soviet.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.pauix.soviet.SovietMod;
import com.pauix.soviet.client.model.KamikazeDogModel;
import com.pauix.soviet.client.model.SturgeonModel;
import com.pauix.soviet.entities.KamikazeDog;
import com.pauix.soviet.entities.Sturgeon;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class SturgeonRenderer extends MobRenderer<Sturgeon, SturgeonModel<Sturgeon>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(SovietMod.MOD_ID,
            "textures/entity/sturgeon.png");

    public SturgeonRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SturgeonModel<>(), 0.2f);
    }

    @Override
    public ResourceLocation getEntityTexture(Sturgeon entity) {
        return TEXTURE;
    }
}
