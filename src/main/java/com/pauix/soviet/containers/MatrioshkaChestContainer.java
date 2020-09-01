package com.pauix.soviet.containers;

import com.pauix.soviet.init.ModBlocks;
import com.pauix.soviet.init.ModContainerTypes;
import com.pauix.soviet.tileentity.MatrioshkaChestTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import org.lwjgl.system.CallbackI;

import java.util.Objects;


public class MatrioshkaChestContainer extends Container {

    public final MatrioshkaChestTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public MatrioshkaChestContainer(final int windowId, final PlayerInventory playerInventory, final MatrioshkaChestTileEntity tileEntity) {
        super(ModContainerTypes.MATRIOSHKA_CHEST.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        // Main Inventory
        int startX = 44;
        int startY = 20;
        int slotSizePlus2 = 18;
        for(int row = 0; row < 2; ++row) {
            for(int column = 0; column < 5; ++column) {
                this.addSlot(new Slot(tileEntity, (row * 5) + column, startX + (column * slotSizePlus2), startY + (row * slotSizePlus2)));
            }
        }

        // Main Player Inventory
        int startPlaverInvX = 8;
        int startPlayerInvY = startY * 3 + 12;
        for(int row = 0; row < 3; ++row) {
            for(int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startPlaverInvX + (column * slotSizePlus2), startPlayerInvY + (row * slotSizePlus2)));
            }
        }

        //Hotbar
        int hotbarY = startPlayerInvY * 2 - 14;
        for(int column = 0; column < 9; ++column) {
            this.addSlot(new Slot(playerInventory, column, startPlaverInvX + (column * slotSizePlus2), hotbarY));
        }
    }

    private static  MatrioshkaChestTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(data, "Data cannot be null");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if(tileAtPos instanceof MatrioshkaChestTileEntity) {
            return (MatrioshkaChestTileEntity)tileAtPos;
        }
        throw new IllegalStateException("WRONG tile entity" + tileAtPos);
    }

    public MatrioshkaChestContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, ModBlocks.MATRIOSHKA.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(index < 10) {
                if(!this.mergeItemStack(itemstack1, 10, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if(!this.mergeItemStack(itemstack1, 0, 10, false)) {
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
