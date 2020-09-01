package com.pauix.soviet.init;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.blocks.BlockItemBase;
import com.pauix.soviet.blocks.RareBlockItemBase;
import com.pauix.soviet.items.*;
import com.pauix.soviet.util.enums.ModArmorMaterial;
import com.pauix.soviet.util.enums.ModItemTier;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.system.CallbackI;

import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, SovietMod.MOD_ID);

    // Items
    public static final RegistryObject<Item> SOVIETITE = ITEMS.register("sovietite", ItemBase::new);
    public static final RegistryObject<Item> STALINIUM = ITEMS.register("stalinium", ItemBase::new);
    public static final RegistryObject<Item> URANIUM = ITEMS.register("uranium", ItemBase::new);
    public static final RegistryObject<Item> PLUTONIUM = ITEMS.register("plutonium", ItemBase::new);
    public static final RegistryObject<Item> HAS = ITEMS.register("hammer_and_sickle", ItemBase::new);
    public static final RegistryObject<Item> MEGA_BOWL = ITEMS.register("mega_bowl", ItemBase::new);
    public static final RegistryObject<Item> CLAY_MEGA_BOWL = ITEMS.register("clay_mega_bowl", ItemBase::new);
    public static final RegistryObject<Item> ETHANOL = ITEMS.register("ethanol", FuelItem::new);
    public static final RegistryObject<Item> STURGEON_BUCKET = ITEMS.register("sturgeon_bucket", SturgeonBucket::new);
    public static final RegistryObject<MolotovCocktail> MOLOTOV_COCKTAIL = ITEMS.register("molotov_cocktail", MolotovCocktail::new);

    // Food
    public static final RegistryObject<Vodka> VODKA = ITEMS.register("vodka", Vodka::new);
    public static final RegistryObject<Soup> SHCHI = ITEMS.register("shchi", Soup::new);
    public static final RegistryObject<Soup> BORSCH = ITEMS.register("borsch", Soup::new);
    public static final RegistryObject<CabbageFood> CABBAGE_FOOD = ITEMS.register("cabbage_food", CabbageFood::new);
    public static final RegistryObject<MegaSoup> MEGA_SHCHI = ITEMS.register("mega_shchi", MegaSoup::new);
    public static final RegistryObject<MegaSoup> MEGA_BORSCH = ITEMS.register("mega_borsch", MegaSoup::new);
    public static final RegistryObject<Cheese> CHEESE = ITEMS.register("cheese", Cheese::new);
    public static final RegistryObject<Caviar> CAVIAR = ITEMS.register("caviar", Caviar::new);
    public static final RegistryObject<Kombucha> KOMBUCHCA = ITEMS.register("kombucha", Kombucha::new);
    public static final RegistryObject<Kvass> KVASS = ITEMS.register("kvass", Kvass::new);

    // Tools
    public static final RegistryObject<SwordItem> STALINIUM_SWORD = ITEMS.register("stalinium_sword", () -> new SwordItem(ModItemTier.STALINIUM, 5, -2.4F, new Item.Properties().group(SovietMod.TAB)));
    public static final RegistryObject<PickaxeItem> STALINIUM_PICKAXE = ITEMS.register("stalinium_pickaxe", () -> new PickaxeItem(ModItemTier.STALINIUM, 0, -2.8F, new Item.Properties().group(SovietMod.TAB)));
    public static final RegistryObject<ShovelItem> STALINIUM_SHOVEL = ITEMS.register("stalinium_shovel", () -> new ShovelItem(ModItemTier.STALINIUM, 0.5F, -3.0F, new Item.Properties().group(SovietMod.TAB)));
    public static final RegistryObject<AxeItem> STALINIUM_AXE = ITEMS.register("stalinium_axe", () -> new AxeItem(ModItemTier.STALINIUM, 7, -3.1F, new Item.Properties().group(SovietMod.TAB)));
    public static final RegistryObject<HoeItem> STALINIUM_HOE = ITEMS.register("stalinium_hoe", () -> new HoeItem(ModItemTier.STALINIUM, -1.0F, new Item.Properties().group(SovietMod.TAB)));

    // Armor
    public static final RegistryObject<ArmorItem> STALINIUM_HELMET = ITEMS.register("stalinium_helmet", () -> new ArmorItem(ModArmorMaterial.STALINIUM, EquipmentSlotType.HEAD, new Item.Properties().group(SovietMod.TAB)));
    public static final RegistryObject<ArmorItem> STALINIUM_CHESTPLATE = ITEMS.register("stalinium_chestplate", () -> new ArmorItem(ModArmorMaterial.STALINIUM, EquipmentSlotType.CHEST, new Item.Properties().group(SovietMod.TAB)));
    public static final RegistryObject<ArmorItem> STALINIUM_LEGGINGS = ITEMS.register("stalinium_leggings", () -> new ArmorItem(ModArmorMaterial.STALINIUM, EquipmentSlotType.LEGS, new Item.Properties().group(SovietMod.TAB)));
    public static final RegistryObject<ArmorItem> STALINIUM_BOOTS = ITEMS.register("stalinium_boots", () -> new ArmorItem(ModArmorMaterial.STALINIUM, EquipmentSlotType.FEET, new Item.Properties().group(SovietMod.TAB)));
    public static final RegistryObject<ArmorItem> ADIDAS_HELMET = ITEMS.register("adidas_helmet", () -> new ArmorItem(ModArmorMaterial.ADIDAS, EquipmentSlotType.HEAD, new Item.Properties().group(SovietMod.TAB)));
    public static final RegistryObject<ArmorItem> ADIDAS_CHESTPLATE = ITEMS.register("adidas_chestplate", () -> new ArmorItem(ModArmorMaterial.ADIDAS, EquipmentSlotType.CHEST, new Item.Properties().group(SovietMod.TAB)));
    public static final RegistryObject<ArmorItem> ADIDAS_LEGGINGS = ITEMS.register("adidas_leggings", () -> new ArmorItem(ModArmorMaterial.ADIDAS, EquipmentSlotType.LEGS, new Item.Properties().group(SovietMod.TAB)));
    public static final RegistryObject<ArmorItem> ADIDAS_BOOTS = ITEMS.register("adidas_boots", () -> new ArmorItem(ModArmorMaterial.ADIDAS, EquipmentSlotType.FEET, new Item.Properties().group(SovietMod.TAB)));
    public static final RegistryObject<ArmorItem> ANTIRAD_CHESTPLATE = ITEMS.register("antirad_chestplate", () -> new ArmorItem(ModArmorMaterial.HAZMAT, EquipmentSlotType.CHEST, new Item.Properties().group(SovietMod.TAB)));
    public static final RegistryObject<ArmorItem> ANTIRAD_LEGGINGS = ITEMS.register("antirad_leggings", () -> new ArmorItem(ModArmorMaterial.HAZMAT, EquipmentSlotType.LEGS, new Item.Properties().group(SovietMod.TAB)));
    public static final RegistryObject<ArmorItem> ANTIRAD_BOOTS = ITEMS.register("antirad_boots", () -> new ArmorItem(ModArmorMaterial.HAZMAT, EquipmentSlotType.FEET, new Item.Properties().group(SovietMod.TAB)));
    public static final RegistryObject<ArmorItem> BALACLAVA = ITEMS.register("balaclava", () -> new ArmorItem(ModArmorMaterial.BALACLAVA, EquipmentSlotType.HEAD, new Item.Properties().group(SovietMod.TAB)));

    // Block Items
    public static final RegistryObject<Item> SOVIETITE_BLOCK_ITEM = ITEMS.register("sovietite_block", () -> new BlockItemBase(ModBlocks.SOVIETITE_BLOCK.get()));
    public static final RegistryObject<Item> STALINIUM_BLOCK_ITEM = ITEMS.register("stalinium_block", () -> new BlockItemBase(ModBlocks.STALINIUM_BLOCK.get()));
    public static final RegistryObject<Item> STALINIUM_ORE_ITEM = ITEMS.register("stalinium_ore", () -> new BlockItemBase(ModBlocks.STALINIUM_ORE.get()));
    public static final RegistryObject<Item> URANIUM_ORE_ITEM = ITEMS.register("uranium_ore", () -> new BlockItemBase(ModBlocks.URANIUM_ORE.get()));
    public static final RegistryObject<Item> MATRIOSHKA_ITEM = ITEMS.register("matrioshka", () -> new BlockItemBase(ModBlocks.MATRIOSHKA.get()));
    public static final RegistryObject<Item> MATRIOSHKA_S_ITEM = ITEMS.register("matrioshka_s", () -> new BlockItemBase(ModBlocks.MATRIOSHKA_S.get()));
    public static final RegistryObject<Item> MATRIOSHKA_L_ITEM = ITEMS.register("matrioshka_l", () -> new BlockItemBase(ModBlocks.MATRIOSHKA_L.get()));
    public static final RegistryObject<Item> FLAG_ITEM = ITEMS.register("flag", () -> new BlockItemBase(ModBlocks.FLAG.get()));
    public static final RegistryObject<Item> CABBAGE_SEED = ITEMS.register("cabbage_seed", () -> new BlockItemBase(ModBlocks.CABBAGE.get()));
    public static final RegistryObject<Item> ATOMIC_BOMB_ITEM = ITEMS.register("atomic_bomb", () -> new RareBlockItemBase(ModBlocks.ATOMIC_BOMB.get()));
    public static final RegistryObject<Item> UNDERWATER_MINE = ITEMS.register("underwater_mine", () -> new BlockItemBase(ModBlocks.UNDERWATER_MINE.get()));
    public static final RegistryObject<Item> ATOMIC_UNDERWATER_MINE = ITEMS.register("atomic_underwater_mine", () -> new RareBlockItemBase(ModBlocks.ATOMIC_UNDERWATER_MINE.get()));
    public static final RegistryObject<Item> HARMONIUM_ORE_ITEM = ITEMS.register("harmonium_ore", () -> new BlockItemBase(ModBlocks.HARMONIUM_ORE.get()));
    public static final RegistryObject<Item> GASMASK_ITEM = ITEMS.register("gasmask", () -> new GasmaskItem(ModBlocks.GASMASK.get()));
    public static final RegistryObject<Item> NUCLEAR_FURNACE = ITEMS.register("nuclear_furnace", () -> new BlockItemBase(ModBlocks.NUCLEAR_FURNACE.get()));
    //public static final RegistryObject<Item> STALINIUM_DUST = ITEMS.register("stalinium_dust", () -> new BlockItemBase(ModBlocks.STALINIUM_DUST.get()));


    // Music Discs
    public static final RegistryObject<Item> MUSIC_DISC_ANTHEM = ITEMS.register("music_disc_anthem", () -> new MusicDiscBase(2, ModSounds.LAZY_ANTHEM_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_ANTHEMEAR = ITEMS.register("music_disc_anthemear", () -> new MusicDiscBase(2, ModSounds.LAZY_ANTHEMEAR_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MUSIC_DISC_FOURTEENMINUTES = ITEMS.register("music_disc_14minutes", () -> new MusicDiscBase(0, ModSounds.LAZY_FOURTEENMINUTES_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_COCONUT = ITEMS.register("music_disc_coconut", () -> new MusicDiscBase(0, ModSounds.LAZY_COCONUT_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_DENISE = ITEMS.register("music_disc_denise", () -> new MusicDiscBase(2, ModSounds.LAZY_DENISE_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_AVIAMARCH = ITEMS.register("music_disc_aviamarch", () -> new MusicDiscBase(2, ModSounds.LAZY_AVIAMARCH_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_BRITANNIA = ITEMS.register("music_disc_britannia", () -> new MusicDiscBase(2, ModSounds.LAZY_BRITANNIA_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_KATYUSHA = ITEMS.register("music_disc_katyusha", () -> new MusicDiscBase(2, ModSounds.LAZY_KATYUSHA_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_KOROBEINIKI = ITEMS.register("music_disc_korobeiniki", () -> new MusicDiscBase(2, ModSounds.LAZY_KOROBEINIKI_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_KALINKA = ITEMS.register("music_disc_kalinka", () -> new MusicDiscBase(2, ModSounds.LAZY_KALINKA_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_HUNGARIAN = ITEMS.register("music_disc_hungarian", () -> new MusicDiscBase(2, ModSounds.LAZY_HUNGARIAN_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_HARDBASS = ITEMS.register("music_disc_hardbass", () -> new MusicDiscBase(2, ModSounds.LAZY_HARDBASS_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_MOSKAU = ITEMS.register("music_disc_moskau", () -> new MusicDiscBase(2, ModSounds.LAZY_MOSKAU_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_PARTY = ITEMS.register("music_disc_party", () -> new MusicDiscBase(2, ModSounds.LAZY_PARTY_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_PINETONS = ITEMS.register("music_disc_pinetons", () -> new MusicDiscBase(2, ModSounds.LAZY_PINETONS_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_RASPUTIN = ITEMS.register("music_disc_rasputin", () -> new MusicDiscBase(2, ModSounds.LAZY_RASPUTIN_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_NEVERGONNA = ITEMS.register("music_disc_nevergonna", () -> new MusicDiscBase(2, ModSounds.LAZY_NEVERGONNA_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_NUKE = ITEMS.register("music_disc_nuke", () -> new MusicDiscBase(2, ModSounds.LAZY_NUKE_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_THOMAS = ITEMS.register("music_disc_thomas", () -> new MusicDiscBase(2, ModSounds.LAZY_THOMAS_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_SEGADORS = ITEMS.register("music_disc_segadors", () -> new MusicDiscBase(2, ModSounds.LAZY_SEGADORS_MUSIC.get(), new Item.Properties().group(SovietMod.TAB).maxStackSize(1).rarity(Rarity.RARE)));
}
