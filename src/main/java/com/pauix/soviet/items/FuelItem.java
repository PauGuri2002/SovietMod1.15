package com.pauix.soviet.items;

import com.pauix.soviet.SovietMod;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FuelItem extends Item {
    public FuelItem() {
        super(new Properties().group(SovietMod.TAB));
    }


    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 3200;
    }
}
