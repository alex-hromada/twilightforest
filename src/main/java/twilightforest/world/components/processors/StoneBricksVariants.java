package twilightforest.world.components.processors;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import twilightforest.util.FeaturePlacers;
import twilightforest.world.registration.TFStructureProcessors;

import javax.annotation.Nullable;
import java.util.Random;

public final class StoneBricksVariants extends StructureProcessor {
	public static final StoneBricksVariants INSTANCE = new StoneBricksVariants();
	public static final Codec<StoneBricksVariants> CODEC = Codec.unit(() -> INSTANCE);

	private StoneBricksVariants() {
    }

	@Override
	public StructureTemplate.StructureBlockInfo process(LevelReader worldReaderIn, BlockPos pos, BlockPos piecepos, StructureTemplate.StructureBlockInfo originalBlock, StructureTemplate.StructureBlockInfo blockInfo, StructurePlaceSettings placementSettingsIn, @Nullable StructureTemplate template) {
		Random random = placementSettingsIn.getRandom(blockInfo.pos);

		BlockState state = blockInfo.state;
		Block block = state.getBlock();

		if (block == Blocks.STONE_BRICKS && random.nextBoolean())
			return new StructureTemplate.StructureBlockInfo(blockInfo.pos, random.nextBoolean() ? Blocks.MOSSY_STONE_BRICKS.defaultBlockState() : Blocks.CRACKED_STONE_BRICKS.defaultBlockState(), null);

		if (block == Blocks.STONE_BRICK_STAIRS && random.nextBoolean())
			return new StructureTemplate.StructureBlockInfo(blockInfo.pos, FeaturePlacers.transferAllStateKeys(state, Blocks.MOSSY_STONE_BRICK_STAIRS), null);

		if (block == Blocks.STONE_BRICK_SLAB && random.nextBoolean())
			return new StructureTemplate.StructureBlockInfo(blockInfo.pos, FeaturePlacers.transferAllStateKeys(state, Blocks.MOSSY_STONE_BRICK_SLAB), null);

		if (block == Blocks.STONE_BRICK_WALL && random.nextBoolean())
			return new StructureTemplate.StructureBlockInfo(blockInfo.pos, FeaturePlacers.transferAllStateKeys(state, Blocks.MOSSY_STONE_BRICK_WALL), null);

		return blockInfo;
	}

	@Override
	protected StructureProcessorType<?> getType() {
		return TFStructureProcessors.STONE_BRICK_VARIANTS;
	}
}