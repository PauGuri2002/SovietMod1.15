package com.pauix.soviet.containers;

import com.pauix.soviet.init.ModBlocks;
import com.pauix.soviet.init.ModContainerTypes;
import com.pauix.soviet.tileentity.MatrioshkaChestSTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import java.util.Objects;


public class MatrioshkaChestSContainer extends Container {

    public final MatrioshkaChestSTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public MatrioshkaChestSContainer(final int windowId, final PlayerInventory playerInventory, final MatrioshkaChestSTileEntity tileEntity) {
        super(ModContainerTypes.MATRIOSHKA_CHEST_S.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        // Main Inventory
        int startX = 44;
        int startY = 20;
        int slotSizePlus2 = 18;
        for(int column = 0; column < 5; ++column) {
            this.addSlot(new Slot(tileEntity, column, startX + (column * slotSizePlus2), startY));
        }

        // Main Player Inventory
        int startPlaverInvX = 8;
        int startPlayerInvY = startY * 2 + 11;
        for(int row = 0; row < 3; ++row) {
            for(int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startPlaverInvX + (column * slotSizePlus2), startPlayerInvY + (row * slotSizePlus2)));
            }
        }

        //Hotbar
        int hotbarY = startPlayerInvY * 2 + 7;
        for(int column = 0; column < 9; ++column) {
            this.addSlot(new Slot(playerInventory, column, startPlaverInvX + (column * slotSizePlus2), hotbarY));
        }
    }

    private static  MatrioshkaChestSTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(data, "Data cannot be null");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if(tileAtPos instanceof MatrioshkaChestSTileEntity) {
            return (MatrioshkaChestSTileEntity)tileAtPos;
        }
        throw new IllegalStateException("WRONG tile entity" + tileAtPos);
    }

    public MatrioshkaChestSContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, ModBlocks.MATRIOSHKA_S.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(index < 5) {
                if(!this.mergeItemStack(itemstack1, 5, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if(!this.mergeItemStack(itemstack1, 0, 5, false)) {
                return ItemStack.EMPTY;
            }
            if(itemstack1.isEmpty()) {
                slot.putStack(itemstack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}
