package com.bumppo109.firma_compat.block;

import com.bumppo109.firma_compat.block.entities.ModBlockEntities;
import net.dries007.tfc.common.blockentities.BarrelBlockEntity;
import net.dries007.tfc.common.blockentities.LoomBlockEntity;
import net.dries007.tfc.common.blockentities.SluiceBlockEntity;
import net.dries007.tfc.common.blockentities.rotation.WaterWheelBlockEntity;
import net.dries007.tfc.common.blockentities.rotation.WindmillBlockEntity;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.GroundcoverBlock;
import net.dries007.tfc.common.blocks.ShelfBlock;
import net.dries007.tfc.common.blocks.devices.BarrelBlock;
import net.dries007.tfc.common.blocks.devices.SluiceBlock;
import net.dries007.tfc.common.blocks.rotation.*;
import net.dries007.tfc.common.blocks.wood.*;
import net.dries007.tfc.common.items.BarrelBlockItem;
import net.dries007.tfc.util.Helpers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.apache.commons.lang3.function.TriFunction;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public enum VanillaWood implements ModRegistryWood {
    ACACIA(false, MapColor.COLOR_ORANGE, MapColor.STONE, 0, 0),
    BIRCH(false, MapColor.SAND, MapColor.QUARTZ, 0, 0),
    CHERRY(false, MapColor.TERRACOTTA_WHITE, MapColor.TERRACOTTA_GRAY, 0, 0),
    DARK_OAK(false, MapColor.COLOR_BROWN, MapColor.COLOR_BROWN, 0, 0),
    JUNGLE(false, MapColor.DIRT, MapColor.PODZOL, 0, 0),
    MANGROVE(false, MapColor.COLOR_RED, MapColor.PODZOL, 0, 0),
    OAK(false, MapColor.COLOR_BROWN, MapColor.PODZOL, 0, 0),
    SPRUCE(false, MapColor.PODZOL, MapColor.COLOR_BROWN, 0, 0),
    CRIMSON(false, MapColor.CRIMSON_STEM, MapColor.CRIMSON_HYPHAE, 0, 0),
    WARPED(false, MapColor.WARPED_STEM, MapColor.WARPED_HYPHAE, 0, 0);

    public static final VanillaWood[] VALUES = values();

    private final String serializedName;
    private final boolean conifer;
    private final MapColor woodColor;
    private final MapColor barkColor;
    //private final TFCTreeGrower tree;
    private final int daysToGrow;
    private final BlockSetType blockSet;
    private final WoodType woodType;
    private final int autumnIndex;

    VanillaWood(boolean conifer, MapColor woodColor, MapColor barkColor, int daysToGrow, int autumnIndex)
    {
        this.serializedName = name().toLowerCase(Locale.ROOT);
        this.conifer = conifer;
        this.woodColor = woodColor;
        this.barkColor = barkColor;
        //this.tree = new TFCTreeGrower(Helpers.identifier("tree/" + serializedName), Helpers.identifier("tree/" + serializedName + "_large"));
        this.daysToGrow = daysToGrow;
        this.autumnIndex = autumnIndex;
        this.blockSet = new BlockSetType(serializedName);
        this.woodType = new WoodType(Helpers.identifier(serializedName).toString(), blockSet);
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }

    @Override
    public MapColor woodColor() {
        return woodColor;
    }

    @Override
    public MapColor barkColor() {
        return barkColor;
    }

    @Override
    public int autumnIndex() {
        return 0;
    }

    @Override
    public TreeGrower tree() {
        return null;
    }

    @Override
    public Supplier<Integer> ticksToGrow() {
        return null;
    }

    @Override
    public Supplier<Block> getBlock(VanillaWood.BlockType blockType) {
        return null;
    }

    @Override
    public BlockSetType getBlockSet() {
        return null;
    }

    @Override
    public WoodType getVanillaWoodType() {
        return woodType;
    }

    public enum BlockType
    {
        //LEAVES((self, wood) -> new TFCLeavesBlock(ExtendedProperties.of().mapColor(MapColor.PLANT).strength(0.5F).sound(SoundType.GRASS).defaultInstrument().randomTicks().noOcclusion().isViewBlocking(TFCBlocks::never).flammableLikeLeaves(), wood.autumnIndex(), wood.getBlock(self.fallenLeaves()), wood.getBlock(self.twig()))),
        //FALLEN_LEAVES((self, wood) -> new FallenLeavesBlock(ExtendedProperties.of().strength(0.05F, 0.0F).noOcclusion().noCollission().isViewBlocking(ModBlocks::never).sound(SoundType.CROP).flammableLikeWool(), wood.getBlock(self.leaves()))),
        LOG_FENCE(wood -> new TFCFenceBlock(properties(wood).strength(2.0F, 3.0F).flammableLikeLogs())),
        TWIG(wood -> GroundcoverBlock.twig(ExtendedProperties.of().strength(0.05F, 0.0F).sound(SoundType.WOOD).noCollission().flammableLikeWool())),
        VERTICAL_SUPPORT(wood -> new VerticalSupportBlock(properties(wood).strength(1.0F).noOcclusion().flammableLikeLogs())),
        HORIZONTAL_SUPPORT(wood -> new HorizontalSupportBlock(properties(wood).strength(1.0F).noOcclusion().flammableLikeLogs())),

        /*
        BOOKSHELF(wood -> new BookshelfBlock(properties(wood).strength(2.0F, 3.0F).flammable(20, 30).enchantPower(BookshelfBlock::getEnchantPower).blockEntity(ModBlockEntities.BOOKSHELF))),
        TOOL_RACK(wood -> new ToolRackBlock(properties(wood).strength(2.0F).noOcclusion().blockEntity(ModBlockEntities.TOOL_RACK))),
        WORKBENCH(wood -> new TFCCraftingTableBlock(properties(wood).strength(2.5F).flammableLikeLogs())),
        LOOM((self, wood) -> new TFCLoomBlock(properties(wood).strength(2.5F).noOcclusion().flammableLikePlanks().blockEntity(ModBlockEntities.LOOM).ticks(LoomBlockEntity::tick))),
        SLUICE(wood -> new SluiceBlock(properties(wood).strength(3F).noOcclusion().flammableLikeLogs().blockEntity(ModBlockEntities.SLUICE).serverTicks(SluiceBlockEntity::serverTick))),
        BARREL((self, wood) -> new BarrelBlock(properties(wood).strength(2.5f).flammableLikePlanks().noOcclusion().blockEntity(ModBlockEntities.BARREL).serverTicks(BarrelBlockEntity::serverTick)), BarrelBlockItem::new),
        LECTERN(wood -> new TFCLecternBlock(properties(wood).noCollission().strength(2.5F).flammableLikePlanks().blockEntity(ModBlockEntities.LECTERN))),
        SCRIBING_TABLE(wood -> new ScribingTableBlock(properties(wood).noOcclusion().strength(2.5F).flammable(20, 30))),
        SEWING_TABLE(wood -> new SewingTableBlock(properties(wood).noOcclusion().strength(2.5F).flammable(20, 30))),
        SHELF(wood -> new ShelfBlock(properties(wood).noOcclusion().strength(2.5f).flammableLikePlanks().blockEntity(ModBlockEntities.SHELF), false)),
        AXLE((self, wood) -> new AxleBlock(properties(wood).noOcclusion().strength(2.5F).flammableLikeLogs().pushReaction(PushReaction.DESTROY).blockEntity(ModBlockEntities.AXLE), getBlock(wood, self.windmill()), self.planksTexture(wood))),
        BLADED_AXLE((self, wood) -> new BladedAxleBlock(properties(wood).noOcclusion().strength(2.5F).flammableLikeLogs().pushReaction(PushReaction.DESTROY).blockEntity(ModBlockEntities.BLADED_AXLE), getBlock(wood, self.axle()))),
        ENCASED_AXLE((self, wood) -> new EncasedAxleBlock(properties(wood).strength(2.5F).flammableLikeLogs().pushReaction(PushReaction.DESTROY).blockEntity(ModBlockEntities.ENCASED_AXLE))),
        CLUTCH((self, wood) -> new ClutchBlock(properties(wood).strength(2.5F).flammableLikeLogs().pushReaction(PushReaction.DESTROY).blockEntity(ModBlockEntities.CLUTCH), getBlock(wood, self.axle()))),
        GEAR_BOX((self, wood) -> new GearBoxBlock(properties(wood).strength(2f).noOcclusion().blockEntity(ModBlockEntities.GEAR_BOX), getBlock(wood, self.axle()))),
        WINDMILL((self, wood) -> new WindmillBlock(properties(wood).strength(9f).noOcclusion().blockEntity(ModBlockEntities.WINDMILL).ticks(WindmillBlockEntity::serverTick, WindmillBlockEntity::clientTick), getBlock(wood, self.axle()))),
        WATER_WHEEL((self, wood) -> new WaterWheelBlock(properties(wood).strength(9f).noOcclusion().blockEntity(ModBlockEntities.WATER_WHEEL).ticks(WaterWheelBlockEntity::serverTick, WaterWheelBlockEntity::clientTick), getBlock(wood, self.axle())))
        */
        ;

        private static ExtendedProperties properties(ModRegistryWood wood)
        {
            return ExtendedProperties.of(wood.woodColor()).sound(SoundType.WOOD).instrument(NoteBlockInstrument.BASS);
        }

        @SuppressWarnings("unchecked")
        private static <B extends Block> Supplier<? extends B> getBlock(ModRegistryWood wood, BlockType type)
        {
            return (Supplier<? extends B>) wood.getBlock(type);
        }

        private final BiFunction<BlockType, ModRegistryWood, Block> blockFactory;
        private final TriFunction<Block, Item.Properties, ModRegistryWood, ? extends BlockItem> blockItemFactory;

        BlockType(Function<ModRegistryWood, Block> blockFactory)
        {
            this((self, wood) -> blockFactory.apply(wood));
        }

        BlockType(BiFunction<BlockType, ModRegistryWood, Block> blockFactory)
        {
            this(blockFactory, BlockItem::new);
        }

        BlockType(BiFunction<BlockType, ModRegistryWood, Block> blockFactory, BiFunction<Block, Item.Properties, ? extends BlockItem> blockItemFactory)
        {
            this(blockFactory, (block, properties, self) -> blockItemFactory.apply(block, properties));
        }

        BlockType(BiFunction<BlockType, ModRegistryWood, Block> blockFactory, TriFunction<Block, Item.Properties, ModRegistryWood, ? extends BlockItem> blockItemFactory)
        {
            this.blockFactory = blockFactory;
            this.blockItemFactory = blockItemFactory;
        }

        @Nullable
        public Function<Block, BlockItem> createBlockItem(ModRegistryWood wood, Item.Properties properties)
        {
            return needsItem() ? block -> blockItemFactory.apply(block, properties, wood) : null;
        }

        public String nameFor(ModRegistryWood wood)
        {
            return (wood.getSerializedName() + "_" + name()).toLowerCase(Locale.ROOT);
        }

        public boolean needsItem()
        {
            return switch(this)
            {
                case VERTICAL_SUPPORT, HORIZONTAL_SUPPORT -> false;
                default -> true;
            };
        }

        private ResourceLocation planksTexture(ModRegistryWood wood)
        {
            return Helpers.identifier("block/wood/planks/" + wood.getSerializedName());
        }

        private BlockType twig() { return TWIG; }
        //private BlockType fallenLeaves() { return FALLEN_LEAVES; }
        //private BlockType leaves() { return LEAVES; }
        //private BlockType axle() { return AXLE; }
        //private BlockType windmill() { return WINDMILL; }

        public Supplier<Block> create(ModRegistryWood wood)
        {
            return () -> blockFactory.apply(this, wood);
        }
    }

    public static void registerBlockSetTypes()
    {
        for (VanillaWood wood : VALUES)
        {
            BlockSetType.register(wood.blockSet);
            WoodType.register(wood.woodType);
        }
    }

}
