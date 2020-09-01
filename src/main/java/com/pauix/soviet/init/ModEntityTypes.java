package com.pauix.soviet.init;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.entities.KamikazeDog;
import com.pauix.soviet.entities.Sturgeon;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, SovietMod.MOD_ID);

    public static final RegistryObject<EntityType<KamikazeDog>> KAMIKAZE_DOG = ENTITY_TYPES.register("kamikaze_dog",
            () -> EntityType.Builder.<KamikazeDog>create(KamikazeDog::new, EntityClassification.CREATURE)
                    .size(0.6f, 0.85f)
                    .build(new ResourceLocation(SovietMod.MOD_ID, "kamikaze_dog").toString()));
    //public static final RegistryObject<EntityType<Bear>> BEAR = ENTITY_TYPES.register("bear",
            //() -> EntityType.Builder.<Bear>create(Bear::new, EntityClassification.CREATURE)
                    //.size(1.4f, 1.4f)
                    //.build(new ResourceLocation(SovietMod.MOD_ID, "bear").toString()));
    public static final RegistryObject<EntityType<Sturgeon>> STURGEON = ENTITY_TYPES.register("sturgeon",
            () -> EntityType.Builder.<Sturgeon>create(Sturgeon::new, EntityClassification.WATER_CREATURE)
                    .size(0.7f, 0.4f)
                    .build(new ResourceLocation(SovietMod.MOD_ID, "sturgeon").toString()));
}
