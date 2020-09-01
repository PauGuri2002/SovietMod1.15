package com.pauix.soviet.items;

import com.pauix.soviet.SovietMod;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemBase extends Item {
    public ItemBase() {
        super(new Item.Properties().group(SovietMod.TAB));
    }


}
