package com.bumppo109.firma_compat.blocks;

import com.bumppo109.firma_compat.FirmaCompatibility;
import com.bumppo109.firma_compat.items.ModItems;
import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.devices.DryingBricksBlock;
import net.dries007.tfc.common.blocks.rock.AqueductBlock;
import net.dries007.tfc.common.blocks.rock.LooseRockBlock;
import net.dries007.tfc.common.blocks.rock.RockAnvilBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FirmaCompatibility.MODID);

    //Ore Map
    public static final Map<String, DeferredBlock<Block>> ORES = new HashMap<>();

    //drying mud brick TODO - dries properly but doesnt return mud brick item
    public static final DeferredBlock<Block> DRYING_MUD_BRICK = registerBlock("drying_bricks/mud",
        () -> new DryingBricksBlock(ExtendedProperties.of(MapColor.DIRT).noCollission().noOcclusion().instabreak().sound(SoundType.STEM).randomTicks().blockEntity(TFCBlockEntities.TICK_COUNTER), ModItems.MUD_BRICK));

    public static final DeferredBlock<Block> STONE_ANVIL = registerBlock("rock/anvil/stone",
            () -> new RockAnvilBlock(ExtendedProperties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2.0F, 10.0F).requiresCorrectToolForDrops().cloneItem(Blocks.STONE).blockEntity(TFCBlockEntities.ANVIL)));

//Stone Blocks
    //loose
    public static final DeferredBlock<Block> LOOSE_STONE = registerBlock("rock/loose/stone",
        () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> LOOSE_DEEPSLATE = registerBlock("rock/loose/deepslate",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> LOOSE_ANDESITE = registerBlock("rock/loose/andesite",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> LOOSE_DIORITE = registerBlock("rock/loose/diorite",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> LOOSE_GRANITE = registerBlock("rock/loose/granite",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> LOOSE_TUFF = registerBlock("rock/loose/tuff",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> LOOSE_CALCITE = registerBlock("rock/loose/calcite",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> LOOSE_DRIPSTONE = registerBlock("rock/loose/dripstone",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> LOOSE_BASALT = registerBlock("rock/loose/basalt",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> LOOSE_BLACKSTONE = registerBlock("rock/loose/blackstone",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> LOOSE_NETHERRACK = registerBlock("rock/loose/netherrack",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> LOOSE_END_STONE = registerBlock("rock/loose/end_stone",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    //aqueduct
    public static final DeferredBlock<Block> STONE_BRICK_AQUEDUCT = registerBlock("rock/aqueduct/stone",
        () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    public static final DeferredBlock<Block> DEEPSLATE_BRICK_AQUEDUCT = registerBlock("rock/aqueduct/deepslate",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS)));
    public static final DeferredBlock<Block> BRICK_AQUEDUCT = registerBlock("rock/aqueduct/brick",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS)));
    public static final DeferredBlock<Block> POLISHED_ANDESITE_AQUEDUCT = registerBlock("rock/aqueduct/andesite",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE)));
    public static final DeferredBlock<Block> POLISHED_DIORITE_AQUEDUCT = registerBlock("rock/aqueduct/diorite",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DIORITE)));
    public static final DeferredBlock<Block> POLISHED_GRANITE_AQUEDUCT = registerBlock("rock/aqueduct/granite",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_GRANITE)));
    public static final DeferredBlock<Block> TUFF_BRICK_AQUEDUCT = registerBlock("rock/aqueduct/tuff",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF_BRICKS)));
    public static final DeferredBlock<Block> POLISHED_BLACKSTONE_BRICK_AQUEDUCT = registerBlock("rock/aqueduct/blackstone",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS)));
    public static final DeferredBlock<Block> END_STONE_BRICK_AQUEDUCT = registerBlock("rock/aqueduct/end_stone",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICKS)));
    public static final DeferredBlock<Block> PRISMARINE_BRICK_AQUEDUCT = registerBlock("rock/aqueduct/prismarine_brick",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICKS)));
    public static final DeferredBlock<Block> DARK_PRISMARINE_AQUEDUCT = registerBlock("rock/aqueduct/dark_prismarine",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE)));
    public static final DeferredBlock<Block> QUARTZ_BRICK_AQUEDUCT = registerBlock("rock/aqueduct/quartz_brick",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS)));
    public static final DeferredBlock<Block> NETHER_BRICK_AQUEDUCT = registerBlock("rock/aqueduct/nether_brick",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)));
    public static final DeferredBlock<Block> RED_NETHER_BRICK_AQUEDUCT = registerBlock("rock/aqueduct/red_nether_brick",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS)));
    public static final DeferredBlock<Block> PURPUR_BLOCK_AQUEDUCT = registerBlock("rock/aqueduct/purpur_block",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPUR_BLOCK)));

    //gravel deposit
    public static final DeferredBlock<Block> CASSITERITE_GRAVEL_DEPOSIT = registerBlock("deposit/cassiterite/gravel",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAVEL)));
    public static final DeferredBlock<Block> NATIVE_COPPER_GRAVEL_DEPOSIT = registerBlock("deposit/native_copper/gravel",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAVEL)));
    public static final DeferredBlock<Block> NATIVE_GOLD_GRAVEL_DEPOSIT = registerBlock("deposit/native_gold/gravel",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAVEL)));
    public static final DeferredBlock<Block> NATIVE_SILVER_GRAVEL_DEPOSIT = registerBlock("deposit/native_silver/gravel",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAVEL)));

    //cobblestone - collapse recipe
    public static final DeferredBlock<Block> STONE_COBBLESTONE = registerBlock("rock/cobblestone/stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
    public static final DeferredBlock<Block> DEEPSLATE_COBBLESTONE = registerBlock("rock/cobblestone/deepslate",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE)));
    public static final DeferredBlock<Block> ANDESITE_COBBLESTONE = registerBlock("rock/cobblestone/andesite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE)));
    public static final DeferredBlock<Block> DIORITE_COBBLESTONE = registerBlock("rock/cobblestone/diorite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE)));
    public static final DeferredBlock<Block> GRANITE_COBBLESTONE = registerBlock("rock/cobblestone/granite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE)));
    public static final DeferredBlock<Block> CALCITE_COBBLESTONE = registerBlock("rock/cobblestone/calcite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)));
    public static final DeferredBlock<Block> DRIPSTONE_COBBLESTONE = registerBlock("rock/cobblestone/dripstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK)));
    public static final DeferredBlock<Block> TUFF_COBBLESTONE = registerBlock("rock/cobblestone/tuff",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF)));
    public static final DeferredBlock<Block> BASALT_COBBLESTONE = registerBlock("rock/cobblestone/basalt",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT)));
    public static final DeferredBlock<Block> BLACKSTONE_COBBLESTONE = registerBlock("rock/cobblestone/blackstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)));
    public static final DeferredBlock<Block> END_STONE_COBBLESTONE = registerBlock("rock/cobblestone/end_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)));
    public static final DeferredBlock<Block> NETHERRACK_COBBLESTONE = registerBlock("rock/cobblestone/netherrack",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)));

    //cobbled - mortar recipe
    public static final DeferredBlock<Block> COBBLED_ANDESITE = registerBlock("rock/cobbled/andesite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE)));
    public static final DeferredBlock<Block> COBBLED_DIORITE = registerBlock("rock/cobbled/diorite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE)));
    public static final DeferredBlock<Block> COBBLED_GRANITE = registerBlock("rock/cobbled/granite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE)));
    public static final DeferredBlock<Block> COBBLED_CALCITE = registerBlock("rock/cobbled/calcite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)));
    public static final DeferredBlock<Block> COBBLED_DRIPSTONE = registerBlock("rock/cobbled/dripstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK)));
    public static final DeferredBlock<Block> COBBLED_TUFF = registerBlock("rock/cobbled/tuff",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF)));
    public static final DeferredBlock<Block> COBBLED_BASALT = registerBlock("rock/cobbled/basalt",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT)));
    public static final DeferredBlock<Block> COBBLED_BLACKSTONE = registerBlock("rock/cobbled/blackstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)));
    public static final DeferredBlock<Block> COBBLED_END_STONE = registerBlock("rock/cobbled/end_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)));
    public static final DeferredBlock<Block> COBBLED_NETHERRACK = registerBlock("rock/cobbled/netherrack",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)));

    //hardened rock
    public static final DeferredBlock<Block> HARDENED_STONE = registerBlock("rock/hardened/stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
    public static final DeferredBlock<Block> HARDENED_DEEPSLATE = registerBlock("rock/hardened/deepslate",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE)));
    public static final DeferredBlock<Block> HARDENED_ANDESITE = registerBlock("rock/hardened/andesite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE)));
    public static final DeferredBlock<Block> HARDENED_DIORITE = registerBlock("rock/hardened/diorite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE)));
    public static final DeferredBlock<Block> HARDENED_GRANITE = registerBlock("rock/hardened/granite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE)));
    public static final DeferredBlock<Block> HARDENED_CALCITE = registerBlock("rock/hardened/calcite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)));
    public static final DeferredBlock<Block> HARDENED_DRIPSTONE = registerBlock("rock/hardened/dripstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK)));
    public static final DeferredBlock<Block> HARDENED_TUFF = registerBlock("rock/hardened/tuff",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF)));
    public static final DeferredBlock<Block> HARDENED_BASALT = registerBlock("rock/hardened/basalt",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT)));
    public static final DeferredBlock<Block> HARDENED_BLACKSTONE = registerBlock("rock/hardened/blackstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)));
    public static final DeferredBlock<Block> HARDENED_END_STONE = registerBlock("rock/hardened/end_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)));
    public static final DeferredBlock<Block> HARDENED_NETHERRACK = registerBlock("rock/hardened/netherrack",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)));

//Ore Registry
    private static final List<StoneVariant> STONE_VARIANTS = List.of(
            new StoneVariant("stone", Blocks.STONE),
            new StoneVariant("deepslate", Blocks.DEEPSLATE),
            new StoneVariant("andesite", Blocks.ANDESITE),
            new StoneVariant("diorite", Blocks.DIORITE),
            new StoneVariant("granite", Blocks.GRANITE),
            new StoneVariant("tuff", Blocks.TUFF),
            new StoneVariant("calcite", Blocks.CALCITE),
            new StoneVariant("dripstone", Blocks.DRIPSTONE_BLOCK),
            new StoneVariant("basalt", Blocks.BASALT),
            new StoneVariant("blackstone", Blocks.BLACKSTONE),
            new StoneVariant("end_stone", Blocks.END_STONE)
    );

    // Ore grades: suffix for naming + optional property modifier (e.g., for hardness/sound tweaks)
    private static final List<Grade> GRADES = List.of(
            new Grade("poor", props -> props),    // No change (or e.g., props -> props.strength(0.5F))
            new Grade("normal", props -> props),  // Standard
            new Grade("rich", props -> props)     // No change (or e.g., props -> props.strength(3.0F).requiresCorrectToolForDrops())
            // Customize lambdas per grade if needed
    );

    //Records for variant and ore grade
    private record StoneVariant(String suffix, Block baseBlock) {}

    private record Grade(String suffix, UnaryOperator<BlockBehaviour.Properties> modifier) {}

    /**
     * Registers all ore variants for a given ore base name (e.g., "bismuth").
     * Returns a map of variant names to DeferredBlocks for exposure.
     */
    private static Map<String, DeferredBlock<Block>> registerOres(String oreBaseName, boolean hasGrades) {
        // If statement to discern types: Use grades only for specific ores
        boolean useGrades = hasGrades || GRADED_ORES.contains(oreBaseName);
        List<Grade> effectiveGrades = useGrades ? GRADES : List.of(new Grade("", props -> props)); // Empty suffix for non-graded

        return effectiveGrades.stream()
                .flatMap(grade -> STONE_VARIANTS.stream().map(stone -> {
                    String gradeOrePart = (grade.suffix().isEmpty() ? "" : grade.suffix() + "_") + oreBaseName;
                    String stonePart = stone.suffix();
                    String fullPath = "ore/" + gradeOrePart + "/" + stonePart;

                    return Map.entry(
                            fullPath,
                            registerBlock(fullPath,
                                    () -> {
                                        BlockBehaviour.Properties props = BlockBehaviour.Properties.ofFullCopy(stone.baseBlock);
                                        return new Block(grade.modifier().apply(props));
                                    }
                            )
                    );
                }))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    // Overload for convenience (defaults to true)
    private static Map<String, DeferredBlock<Block>> registerOres(String oreBaseName) {
        return registerOres(oreBaseName, true);
    }

    // Static set for graded ores (customize this)
    private static final Set<String> GRADED_ORES = Set.of(
            "native_gold", // Has grades (in set)
            "native_copper",
            "native_silver",
            "hematite",
            "cassiterite",
            "bismuthinite",
            "garnierite",
            "malachite",
            "magnetite",
            "limonite",
            "sphalerite",
            "tetrahedrite"
    );

    // Call this in mod init to register ores (defaults to graded)
    public static void registerOres(IEventBus eventBus) {
        ORES.putAll(registerOres("native_gold")); // Has grades (in set)
        ORES.putAll(registerOres("native_copper"));
        ORES.putAll(registerOres("native_silver"));
        ORES.putAll(registerOres("hematite"));
        ORES.putAll(registerOres("cassiterite"));
        ORES.putAll(registerOres("bismuthinite"));
        ORES.putAll(registerOres("garnierite"));
        ORES.putAll(registerOres("malachite"));
        ORES.putAll(registerOres("magnetite"));
        ORES.putAll(registerOres("limonite"));
        ORES.putAll(registerOres("sphalerite"));
        ORES.putAll(registerOres("tetrahedrite"));
        ORES.putAll(registerOres("gypsum", false)); // No grades (explicit false)
        ORES.putAll(registerOres("cinnabar", false));
        ORES.putAll(registerOres("cryolite", false));
        ORES.putAll(registerOres("borax", false));
        ORES.putAll(registerOres("graphite", false));
        ORES.putAll(registerOres("saltpeter", false));
        ORES.putAll(registerOres("sulfur", false));
        ORES.putAll(registerOres("sylvite", false));
        ORES.putAll(registerOres("amethyst", false));
        ORES.putAll(registerOres("diamond", false));
        ORES.putAll(registerOres("emerald", false));
        ORES.putAll(registerOres("lapis_lazuli", false));
        ORES.putAll(registerOres("opal", false));
        ORES.putAll(registerOres("pyrite", false));
        ORES.putAll(registerOres("ruby", false));
        ORES.putAll(registerOres("sapphire", false));
        ORES.putAll(registerOres("topaz", false));
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static DeferredBlock<LiquidBlock> registerBlockNoItem(String name, Supplier<? extends LiquidBlock> supplier) {
        return BLOCKS.register(name, supplier);
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
