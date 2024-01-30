package twilightforest.compat.emi;

import com.mojang.datafixers.util.Pair;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiPatternCraftingRecipe;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.recipe.EmiGrindstoneRecipe;
import dev.emi.emi.recipe.special.EmiAnvilEnchantRecipe;
import dev.emi.emi.recipe.special.EmiGrindstoneDisenchantingRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Block;
import twilightforest.TFConfig;
import twilightforest.compat.RecipeViewerConstants;
import twilightforest.compat.emi.recipes.EmiCrumbleHornRecipe;
import twilightforest.compat.emi.recipes.EmiMoonwormQueenRecipe;
import twilightforest.compat.emi.recipes.EmiTransformationPowderRecipe;
import twilightforest.compat.emi.recipes.EmiUncraftingRecipe;
import twilightforest.init.TFBlocks;
import twilightforest.init.TFItems;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@EmiEntrypoint
public class TFEmiCompat implements EmiPlugin {
	public static final TFEmiRecipeCategory UNCRAFTING = new TFEmiRecipeCategory("uncrafting", TFBlocks.UNCRAFTING_TABLE);
	public static final TFEmiRecipeCategory CRUMBLE_HORN = new TFEmiRecipeCategory("crumble_horn", TFItems.CRUMBLE_HORN);
	public static final TFEmiRecipeCategory TRANSFORMATION = new TFEmiRecipeCategory("transformation", TFItems.TRANSFORMATION_POWDER);
	public static final TFEmiRecipeCategory MOONWORM_QUEEN = new TFEmiRecipeCategory("moonworm_queen", TFItems.MOONWORM_QUEEN);

	private static final Function<List<EmiIngredient>, Boolean> CANT_USE_ENCHANTS = stack ->
			stack.contains(EmiStack.of(TFItems.MOONWORM_QUEEN)) || stack.contains(EmiStack.of(TFItems.LAMP_OF_CINDERS)) || stack.contains(EmiStack.of(TFItems.ORE_MAGNET)) ||
					stack.contains(EmiStack.of(TFItems.TWILIGHT_SCEPTER)) || stack.contains(EmiStack.of(TFItems.LIFEDRAIN_SCEPTER)) ||
					stack.contains(EmiStack.of(TFItems.ZOMBIE_SCEPTER)) || stack.contains(EmiStack.of(TFItems.FORTIFICATION_SCEPTER));

	@Override
	public void register(EmiRegistry registry) {
		registry.addCategory(UNCRAFTING);
		registry.addCategory(CRUMBLE_HORN);
		registry.addCategory(TRANSFORMATION);
		registry.addCategory(MOONWORM_QUEEN);

		registry.addWorkstation(VanillaEmiRecipeCategories.CRAFTING, EmiStack.of(TFBlocks.UNCRAFTING_TABLE));
		registry.addWorkstation(UNCRAFTING, EmiStack.of(TFBlocks.UNCRAFTING_TABLE));
		registry.addWorkstation(CRUMBLE_HORN, EmiStack.of(TFItems.CRUMBLE_HORN));
		registry.addWorkstation(TRANSFORMATION, EmiStack.of(TFItems.TRANSFORMATION_POWDER));
		registry.addWorkstation(MOONWORM_QUEEN, EmiStack.of(TFItems.MOONWORM_QUEEN));

		RecipeManager manager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
		if (!TFConfig.COMMON_CONFIG.UNCRAFTING_STUFFS.disableEntireTable.get()) {
			List<RecipeHolder<? extends CraftingRecipe>> recipes = RecipeViewerConstants.getAllUncraftingRecipes(manager);
			recipes.forEach(recipe -> registry.addRecipe(new EmiUncraftingRecipe<>(recipe)));
		}
		for (RecipeViewerConstants.TransformationPowderInfo info : RecipeViewerConstants.getTransformationPowderRecipes()) {
			registry.addRecipe(new EmiTransformationPowderRecipe(info.input(), info.output(), info.reversible()));
		}

		for (Pair<Block, Block> info : RecipeViewerConstants.getCrumbleHornRecipes()) {
			registry.addRecipe(new EmiCrumbleHornRecipe(info.getFirst(), info.getSecond()));
		}
		registry.addRecipe(new EmiMoonwormQueenRecipe());

		//remove other recipes as they arent actually possible recipes to use
		//emi makes a few assumptions about damageable items that it honestly shouldnt
		registry.removeRecipes(recipe -> {
			if (recipe instanceof EmiPatternCraftingRecipe || recipe instanceof EmiGrindstoneRecipe) {
				return recipe.getInputs().contains(EmiStack.of(TFItems.MOONWORM_QUEEN));
			} else if (recipe instanceof EmiGrindstoneDisenchantingRecipe) {
				return CANT_USE_ENCHANTS.apply(recipe.getInputs());
			} else if (recipe instanceof EmiAnvilEnchantRecipe) {
				return CANT_USE_ENCHANTS.apply(recipe.getInputs());
			}
			return false;
		});
	}
}
