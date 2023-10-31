package twilightforest.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.neoforged.neoforge.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent;
import net.neoforged.neoforge.client.gui.overlay.VanillaGuiOverlay;
import net.neoforged.neoforge.eventbus.api.SubscribeEvent;
import net.neoforged.neoforge.fml.common.Mod;
import twilightforest.TFConfig;
import twilightforest.TwilightForestMod;
import twilightforest.entity.passive.QuestRam;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = TwilightForestMod.ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TFOverlays {
	//for some reason we need a 256x256 texture to actually render anything so i'll just make this a generic icons sheet
	//if we want to add any more overlay things in the future, we can simply add more icons!
	private static final ResourceLocation TF_ICONS_SHEET = TwilightForestMod.prefix("textures/gui/tf_icons.png");

	@SubscribeEvent
	public static void registerOverlays(RegisterGuiOverlaysEvent event) {
		event.registerAbove(VanillaGuiOverlay.CROSSHAIR.id(), "quest_ram_indicator", (gui, graphics, partialTick, screenWidth, screenHeight) -> {
			Minecraft minecraft = Minecraft.getInstance();
			LocalPlayer player = minecraft.player;
			if (player != null && !minecraft.options.hideGui && TFConfig.CLIENT_CONFIG.showQuestRamCrosshairIndicator.get()) {
				RenderSystem.enableBlend();
				renderIndicator(minecraft, graphics, gui, player, screenWidth, screenHeight);
				RenderSystem.disableBlend();
			}
		});
	}

	public static void renderIndicator(Minecraft minecraft, GuiGraphics graphics, Gui gui, Player player, int screenWidth, int screenHeight) {
		Options options = minecraft.options;
		if (options.getCameraType().isFirstPerson()) {
			if (minecraft.gameMode.getPlayerMode() != GameType.SPECTATOR || gui.canRenderCrosshairForSpectator(minecraft.hitResult)) {
				RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
				int j = ((screenHeight - 1) / 2) - 11;
				int k = ((screenWidth - 1) / 2) - 3;
				if (minecraft.crosshairPickEntity instanceof QuestRam ram) {
					ItemStack stack = player.getInventory().getItem(player.getInventory().selected);
					if (!stack.isEmpty() && stack.is(ItemTags.WOOL)) {
						if (ram.guessColor(stack) != null && !ram.isColorPresent(Objects.requireNonNull(ram.guessColor(stack)))) {
							graphics.blit(TF_ICONS_SHEET, k, j, 0, 0, 7, 7);
						} else {
							graphics.blit(TF_ICONS_SHEET, k, j, 7, 0, 7, 7);
						}
					}
				}
			}
		}
	}
}
