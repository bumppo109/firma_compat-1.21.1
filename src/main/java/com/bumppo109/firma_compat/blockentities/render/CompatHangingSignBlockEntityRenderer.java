package com.bumppo109.firma_compat.blockentities.render;

import com.bumppo109.firma_compat.FirmaCompatibility;
import com.bumppo109.firma_compat.FirmaCompatibilityHelpers;
import com.bumppo109.firma_compat.blocks.ModCompatBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.client.RenderHelpers;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.mixin.client.accessor.SignRendererAccessor;
import net.dries007.tfc.util.Helpers;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;

public class CompatHangingSignBlockEntityRenderer extends HangingSignRenderer {
    public static final Map<Block, CompatHangingSignBlockEntityRenderer.Provider<Function<BlockEntityRendererProvider.Context, HangingSignModel>>> MODELS = RenderHelpers.mapOf(map -> {
        ModCompatBlocks.CEILING_HANGING_SIGNS.forEach((wood, m) -> m.forEach((metal, block) -> {
            final var model = new CompatHangingSignBlockEntityRenderer.Provider<Function<BlockEntityRendererProvider.Context, HangingSignModel>>(
                    new Material(
                            Sheets.SIGN_SHEET,
                            FirmaCompatibilityHelpers.modIdentifier("entity/signs/hanging/" + metal.getSerializedName() + "/" + wood.getSerializedName())
                    ),
                    Helpers.resourceLocation(FirmaCompatibility.MODID, wood.getSerializedName() + ".png").withPrefix("textures/gui/hanging_signs/" + metal.getSerializedName() + "/"),
                    context -> new HangingSignModel(context.bakeLayer(FirmaCompatibilityHelpers.layerId("hanging_sign/" + wood.getSerializedName())))
            );

            map.accept(block, model);
            map.accept(ModCompatBlocks.WALL_HANGING_SIGNS.get(wood).get(metal), model);
        }));
        TFCBlocks.CEILING_HANGING_SIGNS.forEach((wood, m) -> m.forEach((metal, block) -> {
            final var model = new CompatHangingSignBlockEntityRenderer.Provider<Function<BlockEntityRendererProvider.Context, HangingSignModel>>(
                    new Material(
                            Sheets.SIGN_SHEET,
                            Helpers.identifier("entity/signs/hanging/" + metal.getSerializedName() + "/" + wood.getSerializedName())
                    ),
                    Helpers.resourceLocation(TerraFirmaCraft.MOD_ID, wood.getSerializedName() + ".png").withPrefix("textures/gui/hanging_signs/" + metal.getSerializedName() + "/"),
                    context -> new HangingSignModel(context.bakeLayer(RenderHelpers.layerId("hanging_sign/" + wood.getSerializedName())))
            );

            map.accept(block, model);
            map.accept(TFCBlocks.WALL_HANGING_SIGNS.get(wood).get(metal), model);
        }));
    });

    private final Map<Block, CompatHangingSignBlockEntityRenderer.Provider<HangingSignModel>> hangingSignModels;

    public CompatHangingSignBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        super(context);
        hangingSignModels = Helpers.mapValue(MODELS, v -> new CompatHangingSignBlockEntityRenderer.Provider<>(v.modelMaterial, v.textureLocation, v.model.apply(context)));
    }

    @Override
    public void render(SignBlockEntity sign, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay)
    {
        final BlockState state = sign.getBlockState();
        final SignBlock signBlock = (SignBlock) state.getBlock();
        final @Nullable CompatHangingSignBlockEntityRenderer.Provider<HangingSignModel> model = hangingSignModels.get(state.getBlock());
        if (model == null)
        {
            return;
        }

        model.model.evaluateVisibleParts(state);
        renderSignWithText(sign, poseStack, buffer, light, overlay, state, signBlock, model.modelMaterial(), model.model);
    }

    // behavior copied from SignRenderer#renderSignWithText
    void renderSignWithText(SignBlockEntity sign, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay, BlockState blockstate, SignBlock signblock, Material modelMaterial, Model model)
    {
        poseStack.pushPose();
        ((SignRendererAccessor) this).invoke$translateSign(poseStack, -signblock.getYRotationDegrees(blockstate), blockstate);
        this.renderSign(poseStack, buffer, light, overlay, modelMaterial, model);
        ((SignRendererAccessor) this).invoke$renderSignText(sign.getBlockPos(), sign.getFrontText(), poseStack, buffer, light, sign.getTextLineHeight(), sign.getMaxTextLineWidth(), true);
        ((SignRendererAccessor) this).invoke$renderSignText(sign.getBlockPos(), sign.getBackText(), poseStack, buffer, light, sign.getTextLineHeight(), sign.getMaxTextLineWidth(), false);
        poseStack.popPose();
    }

    // behavior copied from SignRenderer#renderSign
    void renderSign(PoseStack poseStack, MultiBufferSource buffer, int light, int overlay, Material modelMaterial, Model model)
    {
        poseStack.pushPose();
        float f = this.getSignModelRenderScale();
        poseStack.scale(f, -f, -f);

        VertexConsumer vertexconsumer = modelMaterial.buffer(buffer, model::renderType);
        ((SignRendererAccessor) this).invoke$renderSignModel(poseStack, light, overlay, model, vertexconsumer);
        poseStack.popPose();
    }

    public record Provider<T>(
            Material modelMaterial,
            ResourceLocation textureLocation,
            T model
    ) {}
}
