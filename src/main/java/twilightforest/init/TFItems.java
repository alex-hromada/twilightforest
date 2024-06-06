package twilightforest.init;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;
import twilightforest.TwilightForestMod;
import twilightforest.components.item.PotionFlaskComponent;
import twilightforest.data.tags.CustomTagGenerator;
import twilightforest.entity.TwilightBoat;
import twilightforest.item.*;
import twilightforest.util.TFToolMaterials;

public class TFItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TwilightForestMod.ID);

	public static final DeferredItem<Item> NAGA_SCALE = ITEMS.register("naga_scale", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> NAGA_CHESTPLATE = ITEMS.register("naga_chestplate", () -> new ArmorItem(TFArmorMaterials.NAGA, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(21)).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> NAGA_LEGGINGS = ITEMS.register("naga_leggings", () -> new ArmorItem(TFArmorMaterials.NAGA, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(21)).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> TWILIGHT_SCEPTER = ITEMS.register("twilight_scepter", () -> new TwilightWandItem(new Item.Properties().durability(99).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> LIFEDRAIN_SCEPTER = ITEMS.register("lifedrain_scepter", () -> new LifedrainScepterItem(new Item.Properties().durability(99).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> ZOMBIE_SCEPTER = ITEMS.register("zombie_scepter", () -> new ZombieWandItem(new Item.Properties().durability(9).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> FORTIFICATION_SCEPTER = ITEMS.register("fortification_scepter", () -> new FortificationWandItem(new Item.Properties().durability(9).rarity(Rarity.UNCOMMON)));
	//items.register("Wand of Pacification [NYI]", new Item().setIconIndex(6).setTranslationKey("wandPacification").setMaxStackSize(1));
	public static final DeferredItem<Item> MAGIC_PAINTING = ITEMS.register("magic_painting", () -> new MagicPaintingItem(new Item.Properties()));
	public static final DeferredItem<Item> ORE_METER = ITEMS.register("ore_meter", () -> new OreMeterItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> FILLED_MAGIC_MAP = ITEMS.register("filled_magic_map", () -> new MagicMapItem(new Item.Properties()));
	public static final DeferredItem<Item> FILLED_MAZE_MAP = ITEMS.register("filled_maze_map", () -> new MazeMapItem(false, new Item.Properties()));
	public static final DeferredItem<Item> FILLED_ORE_MAP = ITEMS.register("filled_ore_map", () -> new MazeMapItem(true, new Item.Properties()));
	public static final DeferredItem<Item> RAVEN_FEATHER = ITEMS.register("raven_feather", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> MAGIC_MAP_FOCUS = ITEMS.register("magic_map_focus", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> MAZE_MAP_FOCUS = ITEMS.register("maze_map_focus", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> MAGIC_MAP = ITEMS.register("magic_map", () -> new EmptyMagicMapItem(new Item.Properties()));
	public static final DeferredItem<Item> MAZE_MAP = ITEMS.register("maze_map", () -> new EmptyMazeMapItem(false, new Item.Properties()));
	public static final DeferredItem<Item> ORE_MAP = ITEMS.register("ore_map", () -> new EmptyMazeMapItem(true, new Item.Properties()));
	public static final DeferredItem<Item> LIVEROOT = ITEMS.register("liveroot", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> RAW_IRONWOOD = ITEMS.register("raw_ironwood", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> IRONWOOD_INGOT = ITEMS.register("ironwood_ingot", () -> new Item(new Item.Properties()));
	public static final DeferredItem<ArmorItem> IRONWOOD_HELMET = ITEMS.register("ironwood_helmet", () -> new ArmorItem(TFArmorMaterials.IRONWOOD, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(20))));
	public static final DeferredItem<ArmorItem> IRONWOOD_CHESTPLATE = ITEMS.register("ironwood_chestplate", () -> new ArmorItem(TFArmorMaterials.IRONWOOD, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(20))));
	public static final DeferredItem<ArmorItem> IRONWOOD_LEGGINGS = ITEMS.register("ironwood_leggings", () -> new ArmorItem(TFArmorMaterials.IRONWOOD, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(20))));
	public static final DeferredItem<ArmorItem> IRONWOOD_BOOTS = ITEMS.register("ironwood_boots", () -> new ArmorItem(TFArmorMaterials.IRONWOOD, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(20))));
	public static final DeferredItem<Item> IRONWOOD_SWORD = ITEMS.register("ironwood_sword", () -> new SwordItem(TFToolMaterials.IRONWOOD, new Item.Properties().attributes(SwordItem.createAttributes(TFToolMaterials.IRONWOOD, 3, -2.4F))));
	public static final DeferredItem<Item> IRONWOOD_SHOVEL = ITEMS.register("ironwood_shovel", () -> new ShovelItem(TFToolMaterials.IRONWOOD, new Item.Properties().attributes(ShovelItem.createAttributes(TFToolMaterials.IRONWOOD, 1.5F, -3.0F))));
	public static final DeferredItem<Item> IRONWOOD_PICKAXE = ITEMS.register("ironwood_pickaxe", () -> new PickaxeItem(TFToolMaterials.IRONWOOD, new Item.Properties().attributes(PickaxeItem.createAttributes(TFToolMaterials.IRONWOOD, 1.0F, -2.8F))));
	public static final DeferredItem<Item> IRONWOOD_AXE = ITEMS.register("ironwood_axe", () -> new AxeItem(TFToolMaterials.IRONWOOD, new Item.Properties().attributes(AxeItem.createAttributes(TFToolMaterials.IRONWOOD, 6.0F, -3.1F))));
	public static final DeferredItem<Item> IRONWOOD_HOE = ITEMS.register("ironwood_hoe", () -> new HoeItem(TFToolMaterials.IRONWOOD, new Item.Properties().attributes(HoeItem.createAttributes(TFToolMaterials.IRONWOOD, -2, -1.0F))));
	public static final DeferredItem<Item> TORCHBERRIES = ITEMS.register("torchberries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().effect(() -> new MobEffectInstance(MobEffects.GLOWING, 100, 0), 0.75F).build())));
	public static final DeferredItem<Item> RAW_VENISON = ITEMS.register("raw_venison", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.3F).build())));
	public static final DeferredItem<Item> COOKED_VENISON = ITEMS.register("cooked_venison", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationModifier(0.8F).build())));
	public static final DeferredItem<Item> HYDRA_CHOP = ITEMS.register("hydra_chop", () -> new HydraChopItem(new Item.Properties().fireResistant().food(new FoodProperties.Builder().nutrition(18).saturationModifier(2.0F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1.0F).build()).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> FIERY_BLOOD = ITEMS.register("fiery_blood", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> FIERY_TEARS = ITEMS.register("fiery_tears", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> FIERY_INGOT = ITEMS.register("fiery_ingot", () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<ArmorItem> FIERY_HELMET = ITEMS.register("fiery_helmet", () -> new FieryArmorItem(TFArmorMaterials.FIERY, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(25)).fireResistant().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<ArmorItem> FIERY_CHESTPLATE = ITEMS.register("fiery_chestplate", () -> new FieryArmorItem(TFArmorMaterials.FIERY, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(25)).fireResistant().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<ArmorItem> FIERY_LEGGINGS = ITEMS.register("fiery_leggings", () -> new FieryArmorItem(TFArmorMaterials.FIERY, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(25)).fireResistant().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<ArmorItem> FIERY_BOOTS = ITEMS.register("fiery_boots", () -> new FieryArmorItem(TFArmorMaterials.FIERY, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(25)).fireResistant().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> FIERY_SWORD = ITEMS.register("fiery_sword", () -> new FierySwordItem(TFToolMaterials.FIERY, new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).attributes(SwordItem.createAttributes(TFToolMaterials.FIERY, 3, -2.4F))));
	public static final DeferredItem<Item> FIERY_PICKAXE = ITEMS.register("fiery_pickaxe", () -> new FieryPickItem(TFToolMaterials.FIERY, new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).attributes(PickaxeItem.createAttributes(TFToolMaterials.FIERY, 1.0F, -2.8F))));
	public static final DeferredItem<Item> STEELEAF_INGOT = ITEMS.register("steeleaf_ingot", () -> new Item(new Item.Properties()));
	public static final DeferredItem<ArmorItem> STEELEAF_HELMET = ITEMS.register("steeleaf_helmet", () -> new ArmorItem(TFArmorMaterials.STEELEAF, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(10))));
	public static final DeferredItem<ArmorItem> STEELEAF_CHESTPLATE = ITEMS.register("steeleaf_chestplate", () -> new ArmorItem(TFArmorMaterials.STEELEAF, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(10))));
	public static final DeferredItem<ArmorItem> STEELEAF_LEGGINGS = ITEMS.register("steeleaf_leggings", () -> new ArmorItem(TFArmorMaterials.STEELEAF, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(10))));
	public static final DeferredItem<ArmorItem> STEELEAF_BOOTS = ITEMS.register("steeleaf_boots", () -> new ArmorItem(TFArmorMaterials.STEELEAF, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(10))));
	public static final DeferredItem<Item> STEELEAF_SWORD = ITEMS.register("steeleaf_sword", () -> new SwordItem(TFToolMaterials.STEELEAF, new Item.Properties().attributes(SwordItem.createAttributes(TFToolMaterials.STEELEAF, 3, -2.4F))));
	public static final DeferredItem<Item> STEELEAF_SHOVEL = ITEMS.register("steeleaf_shovel", () -> new ShovelItem(TFToolMaterials.STEELEAF, new Item.Properties().attributes(ShovelItem.createAttributes(TFToolMaterials.STEELEAF, 1.5F, -3.0F))));
	public static final DeferredItem<Item> STEELEAF_PICKAXE = ITEMS.register("steeleaf_pickaxe", () -> new PickaxeItem(TFToolMaterials.STEELEAF, new Item.Properties().attributes(PickaxeItem.createAttributes(TFToolMaterials.STEELEAF, 1.0F, -2.8F))));
	public static final DeferredItem<Item> STEELEAF_AXE = ITEMS.register("steeleaf_axe", () -> new AxeItem(TFToolMaterials.STEELEAF, new Item.Properties().attributes(AxeItem.createAttributes(TFToolMaterials.STEELEAF, 6.0F, -3.0F))));
	public static final DeferredItem<Item> STEELEAF_HOE = ITEMS.register("steeleaf_hoe", () -> new HoeItem(TFToolMaterials.STEELEAF, new Item.Properties().attributes(HoeItem.createAttributes(TFToolMaterials.STEELEAF, -3.0F, -0.5F))));
	public static final DeferredItem<Item> GOLDEN_MINOTAUR_AXE = ITEMS.register("gold_minotaur_axe", () -> new MinotaurAxeItem(Tiers.GOLD, new Item.Properties().rarity(Rarity.COMMON).attributes(AxeItem.createAttributes(Tiers.GOLD, 6.0F, -3.2F))));
	public static final DeferredItem<Item> DIAMOND_MINOTAUR_AXE = ITEMS.register("diamond_minotaur_axe", () -> new MinotaurAxeItem(Tiers.DIAMOND, new Item.Properties().rarity(Rarity.UNCOMMON).attributes(AxeItem.createAttributes(Tiers.DIAMOND, 6.0F, -3.2F))));
	public static final DeferredItem<Item> MAZEBREAKER_PICKAXE = ITEMS.register("mazebreaker_pickaxe", () -> new MazebreakerPickItem(Tiers.DIAMOND, new Item.Properties().setNoRepair().rarity(Rarity.RARE).attributes(PickaxeItem.createAttributes(Tiers.DIAMOND, 1.0F, -2.8F))));
	public static final DeferredItem<Item> TRANSFORMATION_POWDER = ITEMS.register("transformation_powder", () -> new TransformPowderItem(new Item.Properties()));
	public static final DeferredItem<Item> RAW_MEEF = ITEMS.register("raw_meef", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).build())));
	public static final DeferredItem<Item> COOKED_MEEF = ITEMS.register("cooked_meef", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.6F).build())));
	public static final DeferredItem<Item> MEEF_STROGANOFF = ITEMS.register("meef_stroganoff", () -> new BowlFoodItem(new Item.Properties().stacksTo(1).fireResistant().food(new FoodProperties.Builder().nutrition(8).saturationModifier(0.6F).alwaysEdible().build())));
	public static final DeferredItem<Item> MAZE_WAFER = ITEMS.register("maze_wafer", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.6F).build())));
	public static final DeferredItem<Item> ORE_MAGNET = ITEMS.register("ore_magnet", () -> new OreMagnetItem(new Item.Properties().durability(64)));
	public static final DeferredItem<Item> CRUMBLE_HORN = ITEMS.register("crumble_horn", () -> new CrumbleHornItem(new Item.Properties().durability(1024).rarity(Rarity.RARE)));
	public static final DeferredItem<Item> PEACOCK_FEATHER_FAN = ITEMS.register("peacock_feather_fan", () -> new PeacockFanItem(new Item.Properties().durability(1024).rarity(Rarity.RARE)));
	public static final DeferredItem<Item> MOONWORM_QUEEN = ITEMS.register("moonworm_queen", () -> new MoonwormQueenItem(new Item.Properties().setNoRepair().durability(256).rarity(Rarity.RARE)));
	public static final DeferredItem<Item> BRITTLE_FLASK = ITEMS.register("brittle_potion_flask", () -> new BrittleFlaskItem(new Item.Properties().stacksTo(1).component(TFDataComponents.POTION_FLASK_CONTENTS, PotionFlaskComponent.EMPTY)));
	public static final DeferredItem<Item> GREATER_FLASK = ITEMS.register("greater_potion_flask", () -> new GreaterFlaskItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant().component(TFDataComponents.POTION_FLASK_CONTENTS, PotionFlaskComponent.EMPTY_UNBREAKABLE)));
	public static final DeferredItem<Item> CHARM_OF_LIFE_1 = ITEMS.register("charm_of_life_1", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> CHARM_OF_LIFE_2 = ITEMS.register("charm_of_life_2", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> CHARM_OF_KEEPING_1 = ITEMS.register("charm_of_keeping_1", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> CHARM_OF_KEEPING_2 = ITEMS.register("charm_of_keeping_2", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> CHARM_OF_KEEPING_3 = ITEMS.register("charm_of_keeping_3", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> TOWER_KEY = ITEMS.register("tower_key", () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> BORER_ESSENCE = ITEMS.register("borer_essence", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> CARMINITE = ITEMS.register("carminite", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> EXPERIMENT_115 = ITEMS.register("experiment_115", () -> new Experiment115Item(TFBlocks.EXPERIMENT_115.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).build())));
	public static final DeferredItem<Item> ROPE = ITEMS.register("rope", () -> new RopeItem(TFBlocks.ROPE.get(), new Item.Properties()));
	public static final DeferredItem<Item> MASON_JAR = ITEMS.register("mason_jar", () -> new MasonJarItem(TFBlocks.MASON_JAR.get(), new Item.Properties()));
	public static final DeferredItem<Item> ARMOR_SHARD = ITEMS.register("armor_shard", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> ARMOR_SHARD_CLUSTER = ITEMS.register("armor_shard_cluster", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> KNIGHTMETAL_INGOT = ITEMS.register("knightmetal_ingot", () -> new Item(new Item.Properties()));
	public static final DeferredItem<ArmorItem> KNIGHTMETAL_HELMET = ITEMS.register("knightmetal_helmet", () -> new KnightmetalArmorItem(TFArmorMaterials.KNIGHTMETAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(20))));
	public static final DeferredItem<ArmorItem> KNIGHTMETAL_CHESTPLATE = ITEMS.register("knightmetal_chestplate", () -> new KnightmetalArmorItem(TFArmorMaterials.KNIGHTMETAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(20))));
	public static final DeferredItem<ArmorItem> KNIGHTMETAL_LEGGINGS = ITEMS.register("knightmetal_leggings", () -> new KnightmetalArmorItem(TFArmorMaterials.KNIGHTMETAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(20))));
	public static final DeferredItem<ArmorItem> KNIGHTMETAL_BOOTS = ITEMS.register("knightmetal_boots", () -> new KnightmetalArmorItem(TFArmorMaterials.KNIGHTMETAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(20))));
	public static final DeferredItem<Item> KNIGHTMETAL_SWORD = ITEMS.register("knightmetal_sword", () -> new KnightmetalSwordItem(TFToolMaterials.KNIGHTMETAL, new Item.Properties().attributes(SwordItem.createAttributes(TFToolMaterials.KNIGHTMETAL, 3, -2.4F))));
	public static final DeferredItem<Item> KNIGHTMETAL_PICKAXE = ITEMS.register("knightmetal_pickaxe", () -> new KnightmetalPickItem(TFToolMaterials.KNIGHTMETAL, new Item.Properties().attributes(PickaxeItem.createAttributes(TFToolMaterials.KNIGHTMETAL, 1, -2.8F))));
	public static final DeferredItem<Item> KNIGHTMETAL_AXE = ITEMS.register("knightmetal_axe", () -> new KnightmetalAxeItem(TFToolMaterials.KNIGHTMETAL, new Item.Properties().attributes(AxeItem.createAttributes(TFToolMaterials.KNIGHTMETAL, 6F, TFToolMaterials.KNIGHTMETAL.getSpeed() * 0.05f - 3.4f))));
	public static final DeferredItem<Item> KNIGHTMETAL_RING = ITEMS.register("knightmetal_ring", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> KNIGHTMETAL_SHIELD = ITEMS.register("knightmetal_shield", () -> new KnightmetalShieldItem(new Item.Properties().durability(1024)));
	public static final DeferredItem<Item> BLOCK_AND_CHAIN = ITEMS.register("block_and_chain", () -> new ChainBlockItem(new Item.Properties().durability(99)));
	public static final DeferredItem<ArmorItem> PHANTOM_HELMET = ITEMS.register("phantom_helmet", () -> new PhantomArmorItem(TFArmorMaterials.PHANTOM, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(30)).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<ArmorItem> PHANTOM_CHESTPLATE = ITEMS.register("phantom_chestplate", () -> new PhantomArmorItem(TFArmorMaterials.PHANTOM, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(30)).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> ICE_BOMB = ITEMS.register("ice_bomb", () -> new IceBombItem(new Item.Properties().stacksTo(16)));
	public static final DeferredItem<Item> ARCTIC_FUR = ITEMS.register("arctic_fur", () -> new Item(new Item.Properties()));
	public static final DeferredItem<ArmorItem> ARCTIC_HELMET = ITEMS.register("arctic_helmet", () -> new ArcticArmorItem(TFArmorMaterials.ARCTIC, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(10))));
	public static final DeferredItem<ArmorItem> ARCTIC_CHESTPLATE = ITEMS.register("arctic_chestplate", () -> new ArcticArmorItem(TFArmorMaterials.ARCTIC, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(10))));
	public static final DeferredItem<ArmorItem> ARCTIC_LEGGINGS = ITEMS.register("arctic_leggings", () -> new ArcticArmorItem(TFArmorMaterials.ARCTIC, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(10))));
	public static final DeferredItem<ArmorItem> ARCTIC_BOOTS = ITEMS.register("arctic_boots", () -> new ArcticArmorItem(TFArmorMaterials.ARCTIC, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(10))));
	public static final DeferredItem<Item> ALPHA_YETI_FUR = ITEMS.register("alpha_yeti_fur", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<ArmorItem> YETI_HELMET = ITEMS.register("yeti_helmet", () -> new YetiArmorItem(TFArmorMaterials.YETI, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(20)).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<ArmorItem> YETI_CHESTPLATE = ITEMS.register("yeti_chestplate", () -> new YetiArmorItem(TFArmorMaterials.YETI, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(20)).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<ArmorItem> YETI_LEGGINGS = ITEMS.register("yeti_leggings", () -> new YetiArmorItem(TFArmorMaterials.YETI, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(20)).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<ArmorItem> YETI_BOOTS = ITEMS.register("yeti_boots", () -> new YetiArmorItem(TFArmorMaterials.YETI, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(20)).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> TRIPLE_BOW = ITEMS.register("triple_bow", () -> new TripleBowItem(new Item.Properties().rarity(Rarity.UNCOMMON).durability(384)));
	public static final DeferredItem<Item> SEEKER_BOW = ITEMS.register("seeker_bow", () -> new SeekerBowItem(new Item.Properties().rarity(Rarity.UNCOMMON).durability(384)));
	public static final DeferredItem<Item> ICE_BOW = ITEMS.register("ice_bow", () -> new IceBowItem(new Item.Properties().rarity(Rarity.UNCOMMON).durability(384)));
	public static final DeferredItem<Item> ENDER_BOW = ITEMS.register("ender_bow", () -> new EnderBowItem(new Item.Properties().rarity(Rarity.UNCOMMON).durability(384)));
	public static final DeferredItem<Item> ICE_SWORD = ITEMS.register("ice_sword", () -> new IceSwordItem(TFToolMaterials.ICE, new Item.Properties().attributes(SwordItem.createAttributes(TFToolMaterials.ICE, 3, -2.4F))));
	public static final DeferredItem<Item> GLASS_SWORD = ITEMS.register("glass_sword", () -> new GlassSwordItem(TFToolMaterials.GLASS, new Item.Properties().setNoRepair().rarity(Rarity.UNCOMMON).attributes(SwordItem.createAttributes(TFToolMaterials.GLASS, 3, -2.4F))));
	public static final DeferredItem<Item> MAGIC_BEANS = ITEMS.register("magic_beans", () -> new MagicBeansItem(new Item.Properties()));
	public static final DeferredItem<Item> GIANT_PICKAXE = ITEMS.register("giant_pickaxe", () -> new GiantPickItem(TFToolMaterials.GIANT, new Item.Properties().attributes(GiantPickItem.createGiantAttributes(TFToolMaterials.GIANT, 8, -3.5F))));
	public static final DeferredItem<Item> GIANT_SWORD = ITEMS.register("giant_sword", () -> new GiantSwordItem(TFToolMaterials.GIANT, new Item.Properties().attributes(GiantSwordItem.createGiantAttributes(TFToolMaterials.GIANT, 10, -3.5F))));
	public static final DeferredItem<Item> LAMP_OF_CINDERS = ITEMS.register("lamp_of_cinders", () -> new LampOfCindersItem(new Item.Properties().fireResistant().durability(1024).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> CUBE_TALISMAN = ITEMS.register("cube_talisman", () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> CUBE_OF_ANNIHILATION = ITEMS.register("cube_of_annihilation", () -> new CubeOfAnnihilationItem(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> MOON_DIAL = ITEMS.register("moon_dial", () -> new MoonDialItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> POCKET_WATCH = ITEMS.register("pocket_watch", () -> new PocketWatchItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> EMPERORS_CLOTH = ITEMS.register("emperors_cloth", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

	public static final DeferredItem<Item> HUGE_LILY_PAD = ITEMS.register("huge_lily_pad", () -> new HugeLilyPadItem(TFBlocks.HUGE_LILY_PAD.get(), new Item.Properties()));
	public static final DeferredItem<Item> HUGE_WATER_LILY = ITEMS.register("huge_water_lily", () -> new PlaceOnWaterBlockItem(TFBlocks.HUGE_WATER_LILY.get(), new Item.Properties()));
	public static final DeferredItem<Item> FALLEN_LEAVES = ITEMS.register("fallen_leaves", () -> new BlockItem(TFBlocks.FALLEN_LEAVES.get(), new Item.Properties()) {
		@Override
		public InteractionResult useOn(UseOnContext context) {
			return context.getLevel().getBlockState(context.getClickedPos()).is(this.getBlock()) ? super.useOn(context) : InteractionResult.PASS;
		}

		@Override
		public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
			BlockHitResult fluidHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
			BlockHitResult placeBlockResult = fluidHitResult.withPosition(fluidHitResult.getBlockPos().above());
			InteractionResult result = super.useOn(new UseOnContext(player, hand, placeBlockResult));
			return new InteractionResultHolder<>(result, player.getItemInHand(hand));
		}
	});

	public static final DeferredItem<Item> ZOMBIE_SKULL_CANDLE = ITEMS.register("zombie_skull_candle", () -> new SkullCandleItem(TFBlocks.ZOMBIE_SKULL_CANDLE.get(), TFBlocks.ZOMBIE_WALL_SKULL_CANDLE.get(), new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> SKELETON_SKULL_CANDLE = ITEMS.register("skeleton_skull_candle", () -> new SkullCandleItem(TFBlocks.SKELETON_SKULL_CANDLE.get(), TFBlocks.SKELETON_WALL_SKULL_CANDLE.get(), new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> WITHER_SKELETON_SKULL_CANDLE = ITEMS.register("wither_skeleton_skull_candle", () -> new SkullCandleItem(TFBlocks.WITHER_SKELE_SKULL_CANDLE.get(), TFBlocks.WITHER_SKELE_WALL_SKULL_CANDLE.get(), new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> CREEPER_SKULL_CANDLE = ITEMS.register("creeper_skull_candle", () -> new SkullCandleItem(TFBlocks.CREEPER_SKULL_CANDLE.get(), TFBlocks.CREEPER_WALL_SKULL_CANDLE.get(), new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> PLAYER_SKULL_CANDLE = ITEMS.register("player_skull_candle", () -> new SkullCandleItem(TFBlocks.PLAYER_SKULL_CANDLE.get(), TFBlocks.PLAYER_WALL_SKULL_CANDLE.get(), new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> PIGLIN_SKULL_CANDLE = ITEMS.register("piglin_skull_candle", () -> new SkullCandleItem(TFBlocks.PIGLIN_SKULL_CANDLE.get(), TFBlocks.PIGLIN_WALL_SKULL_CANDLE.get(), new Item.Properties().rarity(Rarity.UNCOMMON)));

	public static final DeferredItem<Item> NAGA_TROPHY = ITEMS.register("naga_trophy", () -> new TrophyItem(TFBlocks.NAGA_TROPHY.get(), TFBlocks.NAGA_WALL_TROPHY.get(), new Item.Properties().rarity(TwilightForestMod.getRarity())));
	public static final DeferredItem<Item> LICH_TROPHY = ITEMS.register("lich_trophy", () -> new TrophyItem(TFBlocks.LICH_TROPHY.get(), TFBlocks.LICH_WALL_TROPHY.get(), new Item.Properties().rarity(TwilightForestMod.getRarity())));
	public static final DeferredItem<Item> MINOSHROOM_TROPHY = ITEMS.register("minoshroom_trophy", () -> new TrophyItem(TFBlocks.MINOSHROOM_TROPHY.get(), TFBlocks.MINOSHROOM_WALL_TROPHY.get(), new Item.Properties().rarity(TwilightForestMod.getRarity())));
	public static final DeferredItem<Item> HYDRA_TROPHY = ITEMS.register("hydra_trophy", () -> new TrophyItem(TFBlocks.HYDRA_TROPHY.get(), TFBlocks.HYDRA_WALL_TROPHY.get(), new Item.Properties().rarity(TwilightForestMod.getRarity())));
	public static final DeferredItem<Item> KNIGHT_PHANTOM_TROPHY = ITEMS.register("knight_phantom_trophy", () -> new TrophyItem(TFBlocks.KNIGHT_PHANTOM_TROPHY.get(), TFBlocks.KNIGHT_PHANTOM_WALL_TROPHY.get(), new Item.Properties().rarity(TwilightForestMod.getRarity())));
	public static final DeferredItem<Item> UR_GHAST_TROPHY = ITEMS.register("ur_ghast_trophy", () -> new TrophyItem(TFBlocks.UR_GHAST_TROPHY.get(), TFBlocks.UR_GHAST_WALL_TROPHY.get(), new Item.Properties().rarity(TwilightForestMod.getRarity())));
	public static final DeferredItem<Item> ALPHA_YETI_TROPHY = ITEMS.register("alpha_yeti_trophy", () -> new TrophyItem(TFBlocks.ALPHA_YETI_TROPHY.get(), TFBlocks.ALPHA_YETI_WALL_TROPHY.get(), new Item.Properties().rarity(TwilightForestMod.getRarity())));
	public static final DeferredItem<Item> SNOW_QUEEN_TROPHY = ITEMS.register("snow_queen_trophy", () -> new TrophyItem(TFBlocks.SNOW_QUEEN_TROPHY.get(), TFBlocks.SNOW_QUEEN_WALL_TROPHY.get(), new Item.Properties().rarity(TwilightForestMod.getRarity())));
	public static final DeferredItem<Item> QUEST_RAM_TROPHY = ITEMS.register("quest_ram_trophy", () -> new TrophyItem(TFBlocks.QUEST_RAM_TROPHY.get(), TFBlocks.QUEST_RAM_WALL_TROPHY.get(), new Item.Properties().rarity(TwilightForestMod.getRarity())));

	public static final DeferredItem<HollowLogItem> HOLLOW_TWILIGHT_OAK_LOG = ITEMS.register("hollow_twilight_oak_log", () -> new HollowLogItem(TFBlocks.HOLLOW_TWILIGHT_OAK_LOG_HORIZONTAL, TFBlocks.HOLLOW_TWILIGHT_OAK_LOG_VERTICAL, TFBlocks.HOLLOW_TWILIGHT_OAK_LOG_CLIMBABLE, new Item.Properties()));
	public static final DeferredItem<HollowLogItem> HOLLOW_CANOPY_LOG = ITEMS.register("hollow_canopy_log", () -> new HollowLogItem(TFBlocks.HOLLOW_CANOPY_LOG_HORIZONTAL, TFBlocks.HOLLOW_CANOPY_LOG_VERTICAL, TFBlocks.HOLLOW_CANOPY_LOG_CLIMBABLE, new Item.Properties()));
	public static final DeferredItem<HollowLogItem> HOLLOW_MANGROVE_LOG = ITEMS.register("hollow_mangrove_log", () -> new HollowLogItem(TFBlocks.HOLLOW_MANGROVE_LOG_HORIZONTAL, TFBlocks.HOLLOW_MANGROVE_LOG_VERTICAL, TFBlocks.HOLLOW_MANGROVE_LOG_CLIMBABLE, new Item.Properties()));
	public static final DeferredItem<HollowLogItem> HOLLOW_DARK_LOG = ITEMS.register("hollow_dark_log", () -> new HollowLogItem(TFBlocks.HOLLOW_DARK_LOG_HORIZONTAL, TFBlocks.HOLLOW_DARK_LOG_VERTICAL, TFBlocks.HOLLOW_DARK_LOG_CLIMBABLE, new Item.Properties()));
	public static final DeferredItem<HollowLogItem> HOLLOW_TIME_LOG = ITEMS.register("hollow_time_log", () -> new HollowLogItem(TFBlocks.HOLLOW_TIME_LOG_HORIZONTAL, TFBlocks.HOLLOW_TIME_LOG_VERTICAL, TFBlocks.HOLLOW_TIME_LOG_CLIMBABLE, new Item.Properties()));
	public static final DeferredItem<HollowLogItem> HOLLOW_TRANSFORMATION_LOG = ITEMS.register("hollow_transformation_log", () -> new HollowLogItem(TFBlocks.HOLLOW_TRANSFORMATION_LOG_HORIZONTAL, TFBlocks.HOLLOW_TRANSFORMATION_LOG_VERTICAL, TFBlocks.HOLLOW_TRANSFORMATION_LOG_CLIMBABLE, new Item.Properties()));
	public static final DeferredItem<HollowLogItem> HOLLOW_MINING_LOG = ITEMS.register("hollow_mining_log", () -> new HollowLogItem(TFBlocks.HOLLOW_MINING_LOG_HORIZONTAL, TFBlocks.HOLLOW_MINING_LOG_VERTICAL, TFBlocks.HOLLOW_MINING_LOG_CLIMBABLE, new Item.Properties()));
	public static final DeferredItem<HollowLogItem> HOLLOW_SORTING_LOG = ITEMS.register("hollow_sorting_log", () -> new HollowLogItem(TFBlocks.HOLLOW_SORTING_LOG_HORIZONTAL, TFBlocks.HOLLOW_SORTING_LOG_VERTICAL, TFBlocks.HOLLOW_SORTING_LOG_CLIMBABLE, new Item.Properties()));

	public static final DeferredItem<HollowLogItem> HOLLOW_OAK_LOG = ITEMS.register("hollow_oak_log", () -> new HollowLogItem(TFBlocks.HOLLOW_OAK_LOG_HORIZONTAL, TFBlocks.HOLLOW_OAK_LOG_VERTICAL, TFBlocks.HOLLOW_OAK_LOG_CLIMBABLE, new Item.Properties()));
	public static final DeferredItem<HollowLogItem> HOLLOW_SPRUCE_LOG = ITEMS.register("hollow_spruce_log", () -> new HollowLogItem(TFBlocks.HOLLOW_SPRUCE_LOG_HORIZONTAL, TFBlocks.HOLLOW_SPRUCE_LOG_VERTICAL, TFBlocks.HOLLOW_SPRUCE_LOG_CLIMBABLE, new Item.Properties()));
	public static final DeferredItem<HollowLogItem> HOLLOW_BIRCH_LOG = ITEMS.register("hollow_birch_log", () -> new HollowLogItem(TFBlocks.HOLLOW_BIRCH_LOG_HORIZONTAL, TFBlocks.HOLLOW_BIRCH_LOG_VERTICAL, TFBlocks.HOLLOW_BIRCH_LOG_CLIMBABLE, new Item.Properties()));
	public static final DeferredItem<HollowLogItem> HOLLOW_JUNGLE_LOG = ITEMS.register("hollow_jungle_log", () -> new HollowLogItem(TFBlocks.HOLLOW_JUNGLE_LOG_HORIZONTAL, TFBlocks.HOLLOW_JUNGLE_LOG_VERTICAL, TFBlocks.HOLLOW_JUNGLE_LOG_CLIMBABLE, new Item.Properties()));
	public static final DeferredItem<HollowLogItem> HOLLOW_ACACIA_LOG = ITEMS.register("hollow_acacia_log", () -> new HollowLogItem(TFBlocks.HOLLOW_ACACIA_LOG_HORIZONTAL, TFBlocks.HOLLOW_ACACIA_LOG_VERTICAL, TFBlocks.HOLLOW_ACACIA_LOG_CLIMBABLE, new Item.Properties()));
	public static final DeferredItem<HollowLogItem> HOLLOW_DARK_OAK_LOG = ITEMS.register("hollow_dark_oak_log", () -> new HollowLogItem(TFBlocks.HOLLOW_DARK_OAK_LOG_HORIZONTAL, TFBlocks.HOLLOW_DARK_OAK_LOG_VERTICAL, TFBlocks.HOLLOW_DARK_OAK_LOG_CLIMBABLE, new Item.Properties()));
	public static final DeferredItem<HollowLogItem> HOLLOW_CRIMSON_STEM = ITEMS.register("hollow_crimson_stem", () -> new HollowLogItem(TFBlocks.HOLLOW_CRIMSON_STEM_HORIZONTAL, TFBlocks.HOLLOW_CRIMSON_STEM_VERTICAL, TFBlocks.HOLLOW_CRIMSON_STEM_CLIMBABLE, new Item.Properties()));
	public static final DeferredItem<HollowLogItem> HOLLOW_WARPED_STEM = ITEMS.register("hollow_warped_stem", () -> new HollowLogItem(TFBlocks.HOLLOW_WARPED_STEM_HORIZONTAL, TFBlocks.HOLLOW_WARPED_STEM_VERTICAL, TFBlocks.HOLLOW_WARPED_STEM_CLIMBABLE, new Item.Properties()));
	public static final DeferredItem<HollowLogItem> HOLLOW_VANGROVE_LOG = ITEMS.register("hollow_vangrove_log", () -> new HollowLogItem(TFBlocks.HOLLOW_VANGROVE_LOG_HORIZONTAL, TFBlocks.HOLLOW_VANGROVE_LOG_VERTICAL, TFBlocks.HOLLOW_VANGROVE_LOG_CLIMBABLE, new Item.Properties()));
	public static final DeferredItem<HollowLogItem> HOLLOW_CHERRY_LOG = ITEMS.register("hollow_cherry_log", () -> new HollowLogItem(TFBlocks.HOLLOW_CHERRY_LOG_HORIZONTAL, TFBlocks.HOLLOW_CHERRY_LOG_VERTICAL, TFBlocks.HOLLOW_CHERRY_LOG_CLIMBABLE, new Item.Properties()));

	public static final DeferredItem<Item> TWILIGHT_OAK_SIGN = ITEMS.register("twilight_oak_sign", () -> new SignItem(new Item.Properties().stacksTo(16), TFBlocks.TWILIGHT_OAK_SIGN.get(), TFBlocks.TWILIGHT_WALL_SIGN.get()));
	public static final DeferredItem<Item> TWILIGHT_OAK_HANGING_SIGN = ITEMS.register("twilight_oak_hanging_sign", () -> new HangingSignItem(TFBlocks.TWILIGHT_OAK_HANGING_SIGN.get(), TFBlocks.TWILIGHT_OAK_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
	public static final DeferredItem<Item> CANOPY_SIGN = ITEMS.register("canopy_sign", () -> new SignItem(new Item.Properties().stacksTo(16), TFBlocks.CANOPY_SIGN.get(), TFBlocks.CANOPY_WALL_SIGN.get()));
	public static final DeferredItem<Item> CANOPY_HANGING_SIGN = ITEMS.register("canopy_hanging_sign", () -> new HangingSignItem(TFBlocks.CANOPY_HANGING_SIGN.get(), TFBlocks.CANOPY_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
	public static final DeferredItem<Item> MANGROVE_SIGN = ITEMS.register("mangrove_sign", () -> new SignItem(new Item.Properties().stacksTo(16), TFBlocks.MANGROVE_SIGN.get(), TFBlocks.MANGROVE_WALL_SIGN.get()));
	public static final DeferredItem<Item> MANGROVE_HANGING_SIGN = ITEMS.register("mangrove_hanging_sign", () -> new HangingSignItem(TFBlocks.MANGROVE_HANGING_SIGN.get(), TFBlocks.MANGROVE_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
	public static final DeferredItem<Item> DARK_SIGN = ITEMS.register("dark_sign", () -> new SignItem(new Item.Properties().stacksTo(16), TFBlocks.DARK_SIGN.get(), TFBlocks.DARK_WALL_SIGN.get()));
	public static final DeferredItem<Item> DARK_HANGING_SIGN = ITEMS.register("dark_hanging_sign", () -> new HangingSignItem(TFBlocks.DARK_HANGING_SIGN.get(), TFBlocks.DARK_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
	public static final DeferredItem<Item> TIME_SIGN = ITEMS.register("time_sign", () -> new SignItem(new Item.Properties().stacksTo(16), TFBlocks.TIME_SIGN.get(), TFBlocks.TIME_WALL_SIGN.get()));
	public static final DeferredItem<Item> TIME_HANGING_SIGN = ITEMS.register("time_hanging_sign", () -> new HangingSignItem(TFBlocks.TIME_HANGING_SIGN.get(), TFBlocks.TIME_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
	public static final DeferredItem<Item> TRANSFORMATION_SIGN = ITEMS.register("transformation_sign", () -> new SignItem(new Item.Properties().stacksTo(16), TFBlocks.TRANSFORMATION_SIGN.get(), TFBlocks.TRANSFORMATION_WALL_SIGN.get()));
	public static final DeferredItem<Item> TRANSFORMATION_HANGING_SIGN = ITEMS.register("transformation_hanging_sign", () -> new HangingSignItem(TFBlocks.TRANSFORMATION_HANGING_SIGN.get(), TFBlocks.TRANSFORMATION_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
	public static final DeferredItem<Item> MINING_SIGN = ITEMS.register("mining_sign", () -> new SignItem(new Item.Properties().stacksTo(16), TFBlocks.MINING_SIGN.get(), TFBlocks.MINING_WALL_SIGN.get()));
	public static final DeferredItem<Item> MINING_HANGING_SIGN = ITEMS.register("mining_hanging_sign", () -> new HangingSignItem(TFBlocks.MINING_HANGING_SIGN.get(), TFBlocks.MINING_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
	public static final DeferredItem<Item> SORTING_SIGN = ITEMS.register("sorting_sign", () -> new SignItem(new Item.Properties().stacksTo(16), TFBlocks.SORTING_SIGN.get(), TFBlocks.SORTING_WALL_SIGN.get()));
	public static final DeferredItem<Item> SORTING_HANGING_SIGN = ITEMS.register("sorting_hanging_sign", () -> new HangingSignItem(TFBlocks.SORTING_HANGING_SIGN.get(), TFBlocks.SORTING_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

	public static final DeferredItem<Item> TWILIGHT_OAK_BOAT = ITEMS.register("twilight_oak_boat", () -> new TwilightBoatItem(false, TwilightBoat.Type.TWILIGHT_OAK, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> TWILIGHT_OAK_CHEST_BOAT = ITEMS.register("twilight_oak_chest_boat", () -> new TwilightBoatItem(true, TwilightBoat.Type.TWILIGHT_OAK, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> CANOPY_BOAT = ITEMS.register("canopy_boat", () -> new TwilightBoatItem(false, TwilightBoat.Type.CANOPY, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> CANOPY_CHEST_BOAT = ITEMS.register("canopy_chest_boat", () -> new TwilightBoatItem(true, TwilightBoat.Type.CANOPY, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> MANGROVE_BOAT = ITEMS.register("mangrove_boat", () -> new TwilightBoatItem(false, TwilightBoat.Type.MANGROVE, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> MANGROVE_CHEST_BOAT = ITEMS.register("mangrove_chest_boat", () -> new TwilightBoatItem(true, TwilightBoat.Type.MANGROVE, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> DARK_BOAT = ITEMS.register("dark_boat", () -> new TwilightBoatItem(false, TwilightBoat.Type.DARKWOOD, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> DARK_CHEST_BOAT = ITEMS.register("dark_chest_boat", () -> new TwilightBoatItem(true, TwilightBoat.Type.DARKWOOD, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> TIME_BOAT = ITEMS.register("time_boat", () -> new TwilightBoatItem(false, TwilightBoat.Type.TIME, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> TIME_CHEST_BOAT = ITEMS.register("time_chest_boat", () -> new TwilightBoatItem(true, TwilightBoat.Type.TIME, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> TRANSFORMATION_BOAT = ITEMS.register("transformation_boat", () -> new TwilightBoatItem(false, TwilightBoat.Type.TRANSFORMATION, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> TRANSFORMATION_CHEST_BOAT = ITEMS.register("transformation_chest_boat", () -> new TwilightBoatItem(true, TwilightBoat.Type.TRANSFORMATION, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> MINING_BOAT = ITEMS.register("mining_boat", () -> new TwilightBoatItem(false, TwilightBoat.Type.MINING, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> MINING_CHEST_BOAT = ITEMS.register("mining_chest_boat", () -> new TwilightBoatItem(true, TwilightBoat.Type.MINING, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> SORTING_BOAT = ITEMS.register("sorting_boat", () -> new TwilightBoatItem(false, TwilightBoat.Type.SORTING, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> SORTING_CHEST_BOAT = ITEMS.register("sorting_chest_boat", () -> new TwilightBoatItem(true, TwilightBoat.Type.SORTING, new Item.Properties().stacksTo(1)));

	public static final DeferredItem<RecordItem> MUSIC_DISC_RADIANCE = ITEMS.register("music_disc_radiance", () -> new RecordItem(15, TFSounds.MUSIC_DISC_RADIANCE, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 123 * 20));
	public static final DeferredItem<RecordItem> MUSIC_DISC_STEPS = ITEMS.register("music_disc_steps", () -> new RecordItem(15, TFSounds.MUSIC_DISC_STEPS, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 195 * 20));
	public static final DeferredItem<RecordItem> MUSIC_DISC_SUPERSTITIOUS = ITEMS.register("music_disc_superstitious", () -> new RecordItem(15, TFSounds.MUSIC_DISC_SUPERSTITIOUS, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 192 * 20));
	public static final DeferredItem<RecordItem> MUSIC_DISC_HOME = ITEMS.register("music_disc_home", () -> new RecordItem(15, TFSounds.MUSIC_DISC_HOME, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 215 * 20));
	public static final DeferredItem<RecordItem> MUSIC_DISC_WAYFARER = ITEMS.register("music_disc_wayfarer", () -> new RecordItem(15, TFSounds.MUSIC_DISC_WAYFARER, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 173 * 20));
	public static final DeferredItem<RecordItem> MUSIC_DISC_FINDINGS = ITEMS.register("music_disc_findings", () -> new RecordItem(15, TFSounds.MUSIC_DISC_FINDINGS, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 196 * 20));
	public static final DeferredItem<RecordItem> MUSIC_DISC_MAKER = ITEMS.register("music_disc_maker", () -> new RecordItem(15, TFSounds.MUSIC_DISC_MAKER, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 207 * 20));
	public static final DeferredItem<RecordItem> MUSIC_DISC_THREAD = ITEMS.register("music_disc_thread", () -> new RecordItem(15, TFSounds.MUSIC_DISC_THREAD, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 201 * 20));
	public static final DeferredItem<RecordItem> MUSIC_DISC_MOTION = ITEMS.register("music_disc_motion", () -> new RecordItem(15, TFSounds.MUSIC_DISC_MOTION, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 169 * 20));

	public static final DeferredItem<Item> NAGA_BANNER_PATTERN = ITEMS.register("naga_banner_pattern", () -> new BannerPatternItem(CustomTagGenerator.BannerPatternTagGenerator.NAGA_BANNER_PATTERN, new Item.Properties().stacksTo(1).rarity(TwilightForestMod.getRarity())));
	public static final DeferredItem<Item> LICH_BANNER_PATTERN = ITEMS.register("lich_banner_pattern", () -> new BannerPatternItem(CustomTagGenerator.BannerPatternTagGenerator.LICH_BANNER_PATTERN, new Item.Properties().stacksTo(1).rarity(TwilightForestMod.getRarity())));
	public static final DeferredItem<Item> MINOSHROOM_BANNER_PATTERN = ITEMS.register("minoshroom_banner_pattern", () -> new BannerPatternItem(CustomTagGenerator.BannerPatternTagGenerator.MINOSHROOM_BANNER_PATTERN, new Item.Properties().stacksTo(1).rarity(TwilightForestMod.getRarity())));
	public static final DeferredItem<Item> HYDRA_BANNER_PATTERN = ITEMS.register("hydra_banner_pattern", () -> new BannerPatternItem(CustomTagGenerator.BannerPatternTagGenerator.HYDRA_BANNER_PATTERN, new Item.Properties().stacksTo(1).rarity(TwilightForestMod.getRarity())));
	public static final DeferredItem<Item> KNIGHT_PHANTOM_BANNER_PATTERN = ITEMS.register("knight_phantom_banner_pattern", () -> new BannerPatternItem(CustomTagGenerator.BannerPatternTagGenerator.KNIGHT_PHANTOM_BANNER_PATTERN, new Item.Properties().stacksTo(1).rarity(TwilightForestMod.getRarity())));
	public static final DeferredItem<Item> UR_GHAST_BANNER_PATTERN = ITEMS.register("ur_ghast_banner_pattern", () -> new BannerPatternItem(CustomTagGenerator.BannerPatternTagGenerator.UR_GHAST_BANNER_PATTERN, new Item.Properties().stacksTo(1).rarity(TwilightForestMod.getRarity())));
	public static final DeferredItem<Item> ALPHA_YETI_BANNER_PATTERN = ITEMS.register("alpha_yeti_banner_pattern", () -> new BannerPatternItem(CustomTagGenerator.BannerPatternTagGenerator.ALPHA_YETI_BANNER_PATTERN, new Item.Properties().stacksTo(1).rarity(TwilightForestMod.getRarity())));
	public static final DeferredItem<Item> SNOW_QUEEN_BANNER_PATTERN = ITEMS.register("snow_queen_banner_pattern", () -> new BannerPatternItem(CustomTagGenerator.BannerPatternTagGenerator.SNOW_QUEEN_BANNER_PATTERN, new Item.Properties().stacksTo(1).rarity(TwilightForestMod.getRarity())));
	public static final DeferredItem<Item> QUEST_RAM_BANNER_PATTERN = ITEMS.register("quest_ram_banner_pattern", () -> new BannerPatternItem(CustomTagGenerator.BannerPatternTagGenerator.QUEST_RAM_BANNER_PATTERN, new Item.Properties().stacksTo(1).rarity(TwilightForestMod.getRarity())));

	public static void addItemModelProperties() {
		ItemProperties.register(CUBE_OF_ANNIHILATION.get(), TwilightForestMod.prefix("thrown"), (stack, level, entity, idk) ->
			stack.get(TFDataComponents.THROWN_PROJECTILE) != null ? 1 : 0);

		ItemProperties.register(TFItems.KNIGHTMETAL_SHIELD.get(), new ResourceLocation("blocking"), (stack, level, entity, idk) ->
			entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);

		ItemProperties.register(MOON_DIAL.get(), new ResourceLocation("phase"), new ClampedItemPropertyFunction() {
			@Override
			public float unclampedCall(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entityBase, int idk) {
				boolean flag = entityBase != null;
				Entity entity = flag ? entityBase : stack.getFrame();

				if (level == null && entity != null) level = (ClientLevel) entity.level();

				return level == null ? 0.0F : (float) (level.dimensionType().natural() ? Mth.frac(level.getMoonPhase() / 8.0f) : this.wobble(level, Math.random()));
			}

			double rotation;
			double rota;
			long lastUpdateTick;

			private double wobble(Level level, double rotation) {
				if (level.getGameTime() != this.lastUpdateTick) {
					this.lastUpdateTick = level.getGameTime();
					double delta = rotation - this.rotation;
					delta = Mth.positiveModulo(delta + 0.5D, 1.0D) - 0.5D;
					this.rota += delta * 0.1D;
					this.rota *= 0.9D;
					this.rotation = Mth.positiveModulo(this.rotation + this.rota, 1.0D);
				}
				return this.rotation;
			}
		});

		ItemProperties.register(ORE_METER.get(), TwilightForestMod.prefix("active"), (stack, level, entity, idk) -> {
			if (OreMeterItem.isLoading(stack)) {
				int totalLoadTime = OreMeterItem.LOAD_TIME + OreMeterItem.getRange(stack) * 25;
				int progress = OreMeterItem.getLoadProgress(stack);
				return progress % 5 >= 2 + (int) (Math.random() * 2) && progress <= totalLoadTime - 15 ? 1 : 0;
			}
			return stack.has(TFDataComponents.ORE_DATA) ? 1 : 0;
		});

		ItemProperties.register(MOONWORM_QUEEN.get(), TwilightForestMod.prefix("alt"), (stack, level, entity, idk) -> {
			if (entity != null && entity.getUseItem() == stack) {
				int useTime = stack.getUseDuration() - entity.getUseItemRemainingTicks();
				if (useTime >= MoonwormQueenItem.FIRING_TIME && (useTime >>> 1) % 2 == 0) {
					return 1;
				}
			}
			return 0;
		});

		ItemProperties.register(TFItems.ENDER_BOW.get(), new ResourceLocation("pull"), (stack, level, entity, idk) -> {
			if (entity == null) return 0.0F;
			else
				return entity.getUseItem() != stack ? 0.0F : (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
		});

		ItemProperties.register(TFItems.ENDER_BOW.get(), new ResourceLocation("pulling"), (stack, level, entity, idk) ->
			entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);

		ItemProperties.register(TFItems.ICE_BOW.get(), new ResourceLocation("pull"), (stack, level, entity, idk) -> {
			if (entity == null) return 0.0F;
			else
				return entity.getUseItem() != stack ? 0.0F : (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
		});

		ItemProperties.register(TFItems.ICE_BOW.get(), new ResourceLocation("pulling"), (stack, level, entity, idk) ->
			entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);

		ItemProperties.register(TFItems.SEEKER_BOW.get(), new ResourceLocation("pull"), (stack, level, entity, idk) -> {
			if (entity == null) return 0.0F;
			else
				return entity.getUseItem() != stack ? 0.0F : (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
		});

		ItemProperties.register(TFItems.SEEKER_BOW.get(), new ResourceLocation("pulling"), (stack, level, entity, idk) ->
			entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);

		ItemProperties.register(TFItems.TRIPLE_BOW.get(), new ResourceLocation("pull"), (stack, level, entity, idk) -> {
			if (entity == null) return 0.0F;
			else
				return entity.getUseItem() != stack ? 0.0F : (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
		});

		ItemProperties.register(TFItems.TRIPLE_BOW.get(), new ResourceLocation("pulling"), (stack, level, entity, idk) ->
			entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);

		ItemProperties.register(ORE_MAGNET.get(), new ResourceLocation("pull"), (stack, level, entity, idk) -> {
			if (entity == null) return 0.0F;
			else {
				ItemStack itemstack = entity.getUseItem();
				return !itemstack.isEmpty() ? (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F : 0.0F;
			}
		});

		ItemProperties.register(TFBlocks.RED_THREAD.get().asItem(), TwilightForestMod.prefix("size"), (stack, level, entity, idk) -> {
			if (stack.getCount() >= 32) {
				return 1.0F;
			} else if (stack.getCount() >= 16) {
				return 0.5F;
			} else if (stack.getCount() >= 4) {
				return 0.25F;
			}
			return 0.0F;
		});

		ItemProperties.register(ORE_MAGNET.get(), new ResourceLocation("pulling"), (stack, level, entity, idk) ->
			entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);

		ItemProperties.register(BLOCK_AND_CHAIN.get(), TwilightForestMod.prefix("thrown"), (stack, level, entity, idk) ->
			stack.get(TFDataComponents.THROWN_PROJECTILE) != null ? 1 : 0);

		ItemProperties.register(EXPERIMENT_115.get(), Experiment115Item.THINK, (stack, level, entity, idk) ->
			stack.get(TFDataComponents.EXPERIMENT_115_VARIANTS) != null && stack.get(TFDataComponents.EXPERIMENT_115_VARIANTS).equals("think") ? 1 : 0);

		ItemProperties.register(EXPERIMENT_115.get(), Experiment115Item.FULL, (stack, level, entity, idk) ->
			stack.get(TFDataComponents.EXPERIMENT_115_VARIANTS) != null && stack.get(TFDataComponents.EXPERIMENT_115_VARIANTS).equals("full") ? 1 : 0);

		ItemProperties.register(TFItems.BRITTLE_FLASK.get(), TwilightForestMod.prefix("breakage"), (stack, level, entity, i) ->
			stack.getOrDefault(TFDataComponents.POTION_FLASK_CONTENTS, PotionFlaskComponent.EMPTY).breakage());

		ItemProperties.register(TFItems.BRITTLE_FLASK.get(), TwilightForestMod.prefix("potion_level"), (stack, level, entity, i) ->
			stack.getOrDefault(TFDataComponents.POTION_FLASK_CONTENTS, PotionFlaskComponent.EMPTY).doses());

		ItemProperties.register(TFItems.GREATER_FLASK.get(), TwilightForestMod.prefix("potion_level"), (stack, level, entity, i) ->
			stack.getOrDefault(TFDataComponents.POTION_FLASK_CONTENTS, PotionFlaskComponent.EMPTY).doses());

		ItemProperties.register(TFItems.CRUMBLE_HORN.get(), TwilightForestMod.prefix("tooting"), (stack, world, entity, i) ->
			entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F
		);
	}
}
