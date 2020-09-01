package com.pauix.soviet.world.gen;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.init.ModEntityTypes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = SovietMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntitySpawns {

    @SubscribeEvent
    public static void spawnEntities(FMLLoadCompleteEvent event) {

        for (Biome biome : ForgeRegistries.BIOMES) {

            if(biome.getCategory() == Biome.Category.OCEAN || biome.getCategory() == Biome.Category.RIVER) {
                biome.getSpawns(EntityClassification.WATER_CREATURE)
                        .add(new Biome.SpawnListEntry(ModEntityTypes.STURGEON.get(), 7, 1, 6));
            }
        }
    }
}
