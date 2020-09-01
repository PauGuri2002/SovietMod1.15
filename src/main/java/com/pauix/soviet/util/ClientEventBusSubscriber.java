package com.pauix.soviet.util;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.client.render.KamikazeDogRenderer;
import com.pauix.soviet.client.render.SturgeonRenderer;
import com.pauix.soviet.init.ModEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SovietMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.KAMIKAZE_DOG.get(), KamikazeDogRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.STURGEON.get(), SturgeonRenderer::new);
    }

}
