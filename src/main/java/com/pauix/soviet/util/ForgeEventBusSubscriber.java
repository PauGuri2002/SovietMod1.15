package com.pauix.soviet.util;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.init.ModDimensions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SovietMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventBusSubscriber {

    @SubscribeEvent
    public static void registerDimensions(final RegisterDimensionsEvent event) {
        if(DimensionType.byName(SovietMod.SIBERIA_DIM_TYPE) == null) {
            DimensionManager.registerDimension(SovietMod.SIBERIA_DIM_TYPE, ModDimensions.SIBERIA.get(), null, true);
        }
        SovietMod.LOGGER.info("Siberia Dimension Loaded");
    }
}
