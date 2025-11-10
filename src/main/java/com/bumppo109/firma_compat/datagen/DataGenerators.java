package com.bumppo109.firma_compat.datagen;

import com.bumppo109.firma_compat.FirmaCompatibility;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = FirmaCompatibility.MODID)
public class DataGenerators
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Loot
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(CompatBlockLootProvider::new, LootContextParamSets.BLOCK)),
                lookupProvider)
        );

        // Tags
        BlockTagsProvider blockTagsProvider = new CompatBlockTagProvider(packOutput, lookupProvider, FirmaCompatibility.MODID, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new CompatItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), FirmaCompatibility.MODID, existingFileHelper));
        //generator.addProvider(event.includeServer(), new AFCFluidTagProvider(packOutput, lookupProvider, FirmaCompatibility.MODID, existingFileHelper));

        // Recipes
        generator.addProvider(event.includeServer(), new CompatRecipeProvider(packOutput, lookupProvider));

    }
}
