package com.pauix.soviet.client.gui;

import com.pauix.soviet.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class NuclearFurnaceResultSlot extends SlotItemHandler {

    public NuclearFurnaceResultSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }


    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }


}
