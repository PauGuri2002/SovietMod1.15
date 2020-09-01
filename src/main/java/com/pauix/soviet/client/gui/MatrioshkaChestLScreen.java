package com.pauix.soviet.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.pauix.soviet.SovietMod;
import com.pauix.soviet.containers.MatrioshkaChestLContainer;
import com.pauix.soviet.containers.MatrioshkaChestSContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MatrioshkaChestLScreen extends ContainerScreen<MatrioshkaChestLContainer> {

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(SovietMod.MOD_ID, "textures/gui/matrioshka_chest_l.png");

    public MatrioshkaChestLScreen(MatrioshkaChestLContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 175;
        this.ySize = 171;
    }

    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        this.font.drawString(this.title.getFormattedText(), 8.0F, 6.0F, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        int x = (this.width - this.xSize) /2;
        int y = (this.height - this.ySize) /2;
        this.blit(x, y, 0, 0, this.xSize, this.ySize);
    }
}
