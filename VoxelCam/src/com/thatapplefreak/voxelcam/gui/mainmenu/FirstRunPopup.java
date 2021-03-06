package com.thatapplefreak.voxelcam.gui.mainmenu;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import com.mumfrey.liteloader.core.LiteLoader;
import com.thatapplefreak.voxelcam.VoxelCamCore;
import com.thatapplefreak.voxelcam.VoxelCamConfig;
import com.thatapplefreak.voxelcam.lang.Translator;
import com.thevoxelbox.common.util.BrowserOpener;
import com.thevoxelbox.common.util.gui.GuiDialogBox;

public class FirstRunPopup extends GuiDialogBox {

	private static final ResourceLocation avatarPNG = new ResourceLocation("voxelcam", "textures/avatar.png");
	
	private GuiButton forumLink;

	public FirstRunPopup(GuiScreen parentScreen) {
		super(parentScreen, 320, 150, "VoxelCam");
	}

	@Override
	protected void onInitDialog() {
		btnCancel.visible = false;
		btnOk.displayString = Translator.translate("ok");
		forumLink = new GuiButton(-111195, btnCancel.xPosition, btnCancel.yPosition, 60, 20, Translator.translate("moreinfo"));
		buttonList.add(forumLink);
	}

	@Override
	protected void drawDialog(int mouseX, int mouseY, float f) {
		try {
			drawString(fontRendererObj, Translator.translate("welcomeline1") + " " + LiteLoader.getInstance().getMod("VoxelCam").getVersion() + " ", dialogX + 5, dialogY + 5, 0xFFFFFF);
		} catch (Exception e) {
		}
		drawString(fontRendererObj, Translator.translate("keybindings") + ":", dialogX + 5, dialogY + 20, 0x990000);
		drawString(fontRendererObj, "H - " + Translator.translate("welcomeline2"), dialogX + 10, dialogY + 30, 0x990000);
		drawString(fontRendererObj, "Shift + F2 - " + Translator.translate("welcomeline3"), dialogX + 10, dialogY + 40, 0x990000);
		drawString(fontRendererObj, "F7 - " + Translator.translate("welcomeline4"), dialogX + 10, dialogY + 50, 0x990000);

		drawString(fontRendererObj, Translator.translate("developer") + ":", dialogX + 5, dialogY + 70, 0x00FFFF);
		drawTexturedModalRect(avatarPNG, dialogX + 10, dialogY + 80, dialogX + 75, dialogY + 140, 0, 0, 259, 256);
		drawString(fontRendererObj, "thatapplefreak", dialogX + 6, dialogY + 141, 0xFFFF00);

		drawString(fontRendererObj, "Twitter: @xApplefreak", dialogX + 80, dialogY + 80, 0x4099FF);
		drawString(fontRendererObj, "Reddit: thatapplefreak", dialogX + 80, dialogY + 90, 0xff4500);
		drawString(fontRendererObj, "MinecraftForum: thatapplefreak", dialogX + 80, dialogY + 100, 0x80ba59);
	}
	
	@Override
	protected void actionPerformed(GuiButton guibutton) {
		if (guibutton.equals(forumLink)) {
			BrowserOpener.openURLstringInBrowser("http://bit.ly/16LXtjV");
		}
		super.actionPerformed(guibutton);
	}

	@Override
	public void onSubmit() {
		VoxelCamCore.getConfig().setProperty(VoxelCamConfig.FIRSTRUN, false);
	}

	@Override
	public boolean validateDialog() {
		return true;
	}

}
