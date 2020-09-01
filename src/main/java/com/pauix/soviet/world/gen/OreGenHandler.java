package com.pauix.soviet.world.gen;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = SovietMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OreGenHandler {

    @SubscribeEvent
    public static void generateOres(FMLLoadCompleteEvent  event) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            genOre(biome, 1, 5, 5, 30, OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.HARMONIUM_ORE.get().getDefaultState(), 3);
            genOre(biome, 7, 5, 5, 30, OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.STALINIUM_ORE.get().getDefaultState(), 6);
            genOre(biome, 3, 8, 5, 35, OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.URANIUM_ORE.get().getDefaultState(), 10);
        }
    }

    private static void genOre(Biome biome, int count, int bottomOffset, int topOffset, int max, OreFeatureConfig.FillerBlockType filler, BlockState defaultBlockState, int size) {
        CountRangeConfig range = new CountRangeConfig(count, bottomOffset, topOffset, max);
        OreFeatureConfig feature = new OreFeatureConfig(filler, defaultBlockState, size);
        ConfiguredPlacement config = Placement.COUNT_RANGE.configure(range);
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(feature).withPlacement(config));
    }
}
