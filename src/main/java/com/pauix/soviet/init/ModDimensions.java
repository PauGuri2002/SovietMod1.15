package com.pauix.soviet.init;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.world.dimension.ModSiberia;
import net.minecraft.world.biome.ModifiedBadlandsPlateauBiome;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModDimensions {
    public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, SovietMod.MOD_ID);

    public static final RegistryObject<ModDimension> SIBERIA = MOD_DIMENSIONS.register("siberia", ModSiberia::new);
}
