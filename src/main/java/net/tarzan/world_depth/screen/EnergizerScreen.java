package net.tarzan.world_depth.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.tarzan.world_depth.World_Depth;

public class EnergizerScreen extends AbstractContainerScreen<EnergizerMenu> {
    private static final ResourceLocation TEXTURE=new ResourceLocation(World_Depth.MODID,"textures/gui/energizer_gui.png");
    private static final ResourceLocation ARROW_TEXTURE=new ResourceLocation(World_Depth.MODID,"textures/gui/progress_arrows.png");
    private static final ResourceLocation REDSTONE_TEXTURE=new ResourceLocation(World_Depth.MODID,"textures/gui/redstone_bar.png");
    private static final ResourceLocation CHARGED_REDSTONE_TEXTURE=new ResourceLocation(World_Depth.MODID,"textures/gui/charged_redstone_bar.png");

    public EnergizerScreen(EnergizerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY=100000000;
        this.titleLabelY=100000000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);
        renderRedstoneBar(guiGraphics, x, y);
        renderChargedRedstoneBar(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(ARROW_TEXTURE, x + 43, y + 30, 0, 0, 92, menu.getScaledProgress());
        }
    }

    private void renderRedstoneBar(GuiGraphics guiGraphics, int x, int y){
        guiGraphics.blit(REDSTONE_TEXTURE, x + 8, y + 26, 0, 0, 16, menu.getScaledRedstone());
    }

    private void renderChargedRedstoneBar(GuiGraphics guiGraphics, int x, int y){
        guiGraphics.blit(CHARGED_REDSTONE_TEXTURE, x + 145, y + 26, 0, 0, 16, menu.getScaledChargedRedstone());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
