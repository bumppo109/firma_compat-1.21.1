package com.bumppo109.firma_compat.event;

import com.bumppo109.firma_compat.blocks.ModBlocks;
import net.dries007.tfc.common.TFCTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber (modid = "firma_compat") // Replace with your mod ID
public class RockAnvilTransform {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        ItemStack stack = event.getItemStack();
        if (!stack.is(TFCTags.Items.TOOLS_HAMMER)) {
            return;
        }

        BlockPos pos = event.getPos();
        Level level = event.getLevel();
        BlockState state = level.getBlockState(pos);

        if (state.is(Blocks.STONE)) { // Replace with your target block, e.g., state.is(ModBlocks.TARGET_BLOCK)
            if (!level.isClientSide()) { // Server-side only
                BlockState newState = ModBlocks.STONE_ANVIL.get().defaultBlockState();
                level.setBlock(pos, newState, 3);
                //if (!event.getEntity().getAbilities().instabuild) {
                //    stack.shrink(1);
                //}
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true); // Prevent default interaction
            }
        }
    }
}
