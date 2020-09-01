package com.pauix.soviet.init;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.effects.RadiationEffect;
import net.minecraft.block.Block;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects {

    public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, SovietMod.MOD_ID);

    public static final RegistryObject<Effect> RADIATION = EFFECTS.register("radiation", RadiationEffect::new);
}
