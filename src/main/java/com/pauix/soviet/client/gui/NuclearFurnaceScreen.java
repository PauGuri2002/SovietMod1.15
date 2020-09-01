package com.pauix.soviet.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.pauix.soviet.SovietMod;
import com.pauix.soviet.containers.NuclearFurnaceContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class NuclearFurnaceScreen extends ContainerScreen<NuclearFurnaceContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SovietMod.MOD_ID, "textures/gui/nuclear_furnace.png");

    public NuclearFurnaceScreen(NuclearFurnaceContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);

        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 175;
        this.ySize = 165;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        this.blit(this.guiLeft + 79, this.guiTop + 35, 176, 14, this.container.getSmeltProgressionScaled(), 17);
        if (this.container.isBurning()) {
            int k = (this.container.getBurnLeftScaled());
            this.blit(this.guiLeft + 56, guiTop + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }
        this.blit(this.guiLeft + 31, this.guiTop + 52, 176, 31, this.container.getWaterProgressionScaled(22), 11);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        this.font.drawString(this.title.getFormattedText(), 8.0F, 6.0F, 4210752);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}
