package com.pauix.soviet.util.enums;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.init.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial {

    STALINIUM(SovietMod.MOD_ID + ":stalinium", 40, new int[] { 3, 6, 8, 3 }, 15, SoundEvents.BLOCK_ANVIL_PLACE, 4.0F, () -> { return Ingredient.fromItems(ModItems.STALINIUM.get()); }),
    ADIDAS(SovietMod.MOD_ID + ":adidas", 8, new int[] { 1, 2, 3, 1 }, 20, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 4.0F, () -> { return Ingredient.fromItems(ModItems.SOVIETITE.get()); }),
    HAZMAT(SovietMod.MOD_ID + ":hazmat", 8, new int[] { 1, 2, 3, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> { return Ingredient.fromItems(Items.LEATHER); }),
    BALACLAVA(SovietMod.MOD_ID + ":balaclava", 8, new int[] { 1, 2, 3, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> { return Ingredient.fromItems(Items.BLACK_WOOL); });

    private static final int[] MAX_DAMAGE_ARRAY = new int[] { 11, 16, 15, 13};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final Supplier<Ingredient> repairMaterial;

    ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }
}
