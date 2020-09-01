package com.pauix.soviet.world.dimension;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;

import java.util.Set;

public class SiberiaBiomeProvider extends BiomeProvider {

    private static final Set<Biome> biomeList = ImmutableSet.of(
            Biomes.SNOWY_TUNDRA,
            Biomes.SNOWY_BEACH,
            Biomes.SNOWY_MOUNTAINS,
            Biomes.SNOWY_TAIGA,
            Biomes.SNOWY_TAIGA_HILLS,
            Biomes.SNOWY_TAIGA_MOUNTAINS,
            Biomes.ICE_SPIKES,
            Biomes.FROZEN_OCEAN,
            Biomes.FROZEN_RIVER,
            Biomes.DEEP_FROZEN_OCEAN);

    public SiberiaBiomeProvider() {
        super(biomeList);
    }



    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return Biomes.SNOWY_TUNDRA;
    }
}
