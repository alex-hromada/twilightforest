package twilightforest.loot;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.providers.number.*;
import twilightforest.TFConfig;
import twilightforest.entity.MultiplayerFlexibleEnemy;
import twilightforest.init.TFLoot;

import java.util.Set;

public record MultiplayerBasedNumberProvider(NumberProvider rollsPerPlayer, NumberProvider defaultRolls) implements NumberProvider {
	public static final Codec<MultiplayerBasedNumberProvider> CODEC = RecordCodecBuilder.create(instance -> instance.group(
					NumberProviders.CODEC.fieldOf("per_player_rolls").forGetter(MultiplayerBasedNumberProvider::rollsPerPlayer),
					NumberProviders.CODEC.fieldOf("default_rolls").forGetter(MultiplayerBasedNumberProvider::defaultRolls))
			.apply(instance, MultiplayerBasedNumberProvider::new)
	);

	@Override
	public LootNumberProviderType getType() {
		return TFLoot.MULTIPLAYER_ROLLS.get();
	}

	public static MultiplayerBasedNumberProvider rollsForPlayers(NumberProvider rollsPerPlayer, NumberProvider defaultRolls) {
		return new MultiplayerBasedNumberProvider(rollsPerPlayer, defaultRolls);
	}

	@Override
	public float getFloat(LootContext context) {
		if (TFConfig.COMMON_CONFIG.multiplayerFightAdjuster.get().adjustsLootRolls()) {
			if (context.hasParam(LootContextParams.THIS_ENTITY) && context.getParam(LootContextParams.THIS_ENTITY) instanceof MultiplayerFlexibleEnemy enemy && enemy.getQualifiedPlayers().size() > 1) {
				float total = this.defaultRolls.getFloat(context);
				for (int i = 0; i < enemy.getQualifiedPlayers().size() - 1; i++) {
					total += Math.max(0, this.rollsPerPlayer.getFloat(context));
				}
				return total;
			}
		}
		return this.defaultRolls.getFloat(context);

	}

	/**
	 * Get the parameters used by this object.
	 */
	@Override
	public Set<LootContextParam<?>> getReferencedContextParams() {
		return Sets.union(this.rollsPerPlayer.getReferencedContextParams(), this.defaultRolls.getReferencedContextParams());
	}
}
