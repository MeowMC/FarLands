package site.geni.farlands.mixins.client.render.entity;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.EnchantingTableBlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.farlands.FarLands;

@Mixin(EnchantingTableBlockEntityRenderer.class)
abstract class EnchantingTableBlockEntityRendererMixin extends BlockEntityRenderer<EnchantingTableBlockEntity> {
	private static double x;
	private static double y;
	private static double z;

	/**
	 * Sets X/Y/Z to {@link EnchantingTableBlockEntity}'s X/Y/Z (relative to the enchanting table) (?)
	 *
	 * @param enchantingTableBlockEntity {@link EnchantingTableBlockEntity} to render
	 * @param x                          {@link EnchantingTableBlockEntity}'s X (relative to the enchanting table) (?)
	 * @param y                          {@link EnchantingTableBlockEntity}'s Y (relative to the enchanting table) (?)
	 * @param z                          {@link EnchantingTableBlockEntity}'s Z (relative to the enchanting table) (?)
	 * @param float_1                    ?
	 * @param int_1                      ?
	 * @param ci                         {@link CallbackInfo} required by {@link Inject}
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "HEAD"
		),
		method = "method_3571"
	)
	private void setXYZ(EnchantingTableBlockEntity enchantingTableBlockEntity, double x, double y, double z, float float_1, int int_1, CallbackInfo ci) {
		if (FarLands.getConfig().fixParticlesEntities.getValue()) {
			EnchantingTableBlockEntityRendererMixin.x = x;
			EnchantingTableBlockEntityRendererMixin.y = y;
			EnchantingTableBlockEntityRendererMixin.z = z;
		}
	}

	/**
	 * Renders the book on the enchanting table properly
	 *
	 * @param origX Original X passed to {@link GlStateManager#translatef}
	 * @param origY Original Y passed to {@link GlStateManager#translatef}
	 * @param origZ Original Z passed to {@link GlStateManager#translatef}
	 * @author geni
	 */
	@Redirect(
		at = @At(
			value = "INVOKE",
			target = "Lcom/mojang/blaze3d/systems/RenderSystem;translatef(FFF)V",
			ordinal = 0
		),
		method = "method_3571"
	)
	private void renderBookProperly(float origX, float origY, float origZ) {
		if (FarLands.getConfig().fixParticlesEntities.getValue()) {
			GlStateManager.translated(x + 0.5D, y + 0.75D, z + 0.5D);
		} else {
			GlStateManager.translatef(origX, origY, origZ);
		}
	}
}