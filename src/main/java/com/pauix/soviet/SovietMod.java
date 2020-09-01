package com.pauix.soviet;

import com.pauix.soviet.client.gui.MatrioshkaChestLScreen;
import com.pauix.soviet.client.gui.MatrioshkaChestSScreen;
import com.pauix.soviet.client.gui.MatrioshkaChestScreen;
import com.pauix.soviet.client.gui.NuclearFurnaceScreen;
import com.pauix.soviet.init.*;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod("sovietmod")
public class SovietMod
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "sovietmod";
    public static final ResourceLocation SIBERIA_DIM_TYPE = new ResourceLocation(MOD_ID, "siberia");

    public SovietMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        ModSounds.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEffects.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModContainerTypes.CONTAINER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntityTypes.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModDimensions.MOD_DIMENSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);
    }


    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(ModBlocks.CABBAGE.get(), RenderType.getCutout());
        ScreenManager.registerFactory(ModContainerTypes.MATRIOSHKA_CHEST.get(), MatrioshkaChestScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.MATRIOSHKA_CHEST_S.get(), MatrioshkaChestSScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.MATRIOSHKA_CHEST_L.get(), MatrioshkaChestLScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.NUCLEAR_FURNACE.get(), NuclearFurnaceScreen::new);
    }

    public static final ItemGroup TAB = new ItemGroup("invtab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.HAS.get());
        }
    };
}
