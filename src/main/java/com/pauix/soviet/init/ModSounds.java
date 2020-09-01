package com.pauix.soviet.init;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.items.MusicDiscBase;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, SovietMod.MOD_ID);


    public static final Lazy<SoundEvent> LAZY_ANTHEM_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.anthem")));
    public static final Lazy<SoundEvent> LAZY_ANTHEMEAR_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.anthemear")));
    public static final Lazy<SoundEvent> LAZY_FOURTEENMINUTES_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.14minutes")));
    public static final Lazy<SoundEvent> LAZY_COCONUT_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.coconut")));
    public static final Lazy<SoundEvent> LAZY_DENISE_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.denise")));
    public static final Lazy<SoundEvent> LAZY_AVIAMARCH_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.aviamarch")));
    public static final Lazy<SoundEvent> LAZY_BRITANNIA_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.britannia")));
    public static final Lazy<SoundEvent> LAZY_KATYUSHA_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.katyusha")));
    public static final Lazy<SoundEvent> LAZY_KOROBEINIKI_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.korobeiniki")));
    public static final Lazy<SoundEvent> LAZY_KALINKA_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.kalinka")));
    public static final Lazy<SoundEvent> LAZY_HUNGARIAN_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.hungarian")));
    public static final Lazy<SoundEvent> LAZY_HARDBASS_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.hardbass")));
    public static final Lazy<SoundEvent> LAZY_MOSKAU_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.moskau")));
    public static final Lazy<SoundEvent> LAZY_PARTY_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.party")));
    public static final Lazy<SoundEvent> LAZY_PINETONS_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.pinetons")));
    public static final Lazy<SoundEvent> LAZY_RASPUTIN_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.rasputin")));
    public static final Lazy<SoundEvent> LAZY_NEVERGONNA_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.nevergonna")));
    public static final Lazy<SoundEvent> LAZY_NUKE_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.nuke")));
    public static final Lazy<SoundEvent> LAZY_THOMAS_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.thomas")));
    public static final Lazy<SoundEvent> LAZY_SEGADORS_MUSIC = Lazy.of(() -> new SoundEvent(new ResourceLocation(SovietMod.MOD_ID, "disc.segadors")));


    public static final RegistryObject<SoundEvent> ANTHEM_MUSIC = SOUNDS.register("anthem_music", LAZY_ANTHEM_MUSIC);
    public static final RegistryObject<SoundEvent> ANTHEMEAR_MUSIC = SOUNDS.register("anthemear_music", LAZY_ANTHEMEAR_MUSIC);
    public static final RegistryObject<SoundEvent> FOURTEENMINUTES_MUSIC = SOUNDS.register("14minutes_music", LAZY_FOURTEENMINUTES_MUSIC);
    public static final RegistryObject<SoundEvent> COCONUT_MUSIC = SOUNDS.register("coconut_music", LAZY_COCONUT_MUSIC);
    public static final RegistryObject<SoundEvent> DENISE_MUSIC = SOUNDS.register("denise_music", LAZY_DENISE_MUSIC);
    public static final RegistryObject<SoundEvent> AVIAMARCH_MUSIC = SOUNDS.register("aviamarch_music", LAZY_AVIAMARCH_MUSIC);
    public static final RegistryObject<SoundEvent> BRITANNIA_MUSIC = SOUNDS.register("britannia_music", LAZY_BRITANNIA_MUSIC);
    public static final RegistryObject<SoundEvent> KATYUSHA_MUSIC = SOUNDS.register("katyusha_music", LAZY_KATYUSHA_MUSIC);
    public static final RegistryObject<SoundEvent> KOROBEINIKI_MUSIC = SOUNDS.register("korobeiniki_music", LAZY_KOROBEINIKI_MUSIC);
    public static final RegistryObject<SoundEvent> KALINKA_MUSIC = SOUNDS.register("kalinka_music", LAZY_KALINKA_MUSIC);
    public static final RegistryObject<SoundEvent> HUNGARIAN_MUSIC = SOUNDS.register("hungarian_music", LAZY_HUNGARIAN_MUSIC);
    public static final RegistryObject<SoundEvent> HARDBASS_MUSIC = SOUNDS.register("hardbass_music", LAZY_HARDBASS_MUSIC);
    public static final RegistryObject<SoundEvent> MOSKAU_MUSIC = SOUNDS.register("moskau_music", LAZY_MOSKAU_MUSIC);
    public static final RegistryObject<SoundEvent> PARTY_MUSIC = SOUNDS.register("party_music", LAZY_PARTY_MUSIC);
    public static final RegistryObject<SoundEvent> PINETONS_MUSIC = SOUNDS.register("pinetons_music", LAZY_PINETONS_MUSIC);
    public static final RegistryObject<SoundEvent> RASPUTIN_MUSIC = SOUNDS.register("rasputin_music", LAZY_RASPUTIN_MUSIC);
    public static final RegistryObject<SoundEvent> NEVERGONNA_MUSIC = SOUNDS.register("nevergonna_music", LAZY_NEVERGONNA_MUSIC);
    public static final RegistryObject<SoundEvent> NUKE_MUSIC = SOUNDS.register("nuke_music", LAZY_NUKE_MUSIC);
    public static final RegistryObject<SoundEvent> THOMAS_MUSIC = SOUNDS.register("thomas_music", LAZY_THOMAS_MUSIC);
    public static final RegistryObject<SoundEvent> SEGADORS_MUSIC = SOUNDS.register("segadors_music", LAZY_SEGADORS_MUSIC);
}
