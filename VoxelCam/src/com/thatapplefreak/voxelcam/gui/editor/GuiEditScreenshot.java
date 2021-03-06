package com.thatapplefreak.voxelcam.gui.editor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import com.thatapplefreak.voxelcam.lang.Translator;
import com.thevoxelbox.common.util.gui.AdvancedDrawGui;

public class GuiEditScreenshot extends AdvancedDrawGui {
	
	private GuiScreen parentScreen;
	
	private GuiButton btnDone;

	/**
	 * File refrence of the Screenshot
	 */
	private final File screenshotFile;
	
	/**
	 * This is the raw data for the Screenshot before undergoing changes
	 */
	private BufferedImage uneditedScreenshot;
	
	/**
	 * This is the image created by adding the users edits
	 */
	private BufferedImage editedScreenshot;
	
	private boolean loading = true;
	
	public GuiEditScreenshot(GuiScreen parent, final File screenshot) {
		this.parentScreen = parent;
		this.screenshotFile = screenshot;
	}
	
	@Override
	public void initGui() {
		new Thread("Editor Loading Thread") {
			@Override
			public void run() {
				try {
					uneditedScreenshot = ImageIO.read(screenshotFile);
					loading = false;
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
		buttonList.add(btnDone = new GuiButton(0, 10, height - 30, 70, 20, Translator.translate("done")));
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		if (loading) {
			drawCenteredString(fontRendererObj, Translator.translate("loading") + "...", width / 2, height / 2, 0xffffff);
			return;
		}
		
		super.drawScreen(par1, par2, par3);
	}
	
	@Override
	protected void actionPerformed(GuiButton guibtn) {
		if (guibtn == btnDone) {
			Minecraft.getMinecraft().displayGuiScreen(parentScreen);
		}
	}
}
