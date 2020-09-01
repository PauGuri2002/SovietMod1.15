package com.pauix.soviet.containers;

import com.pauix.soviet.client.gui.NuclearFurnaceFuelSlot;
import com.pauix.soviet.client.gui.NuclearFurnaceResultSlot;
import com.pauix.soviet.client.gui.NuclearFurnaceWaterSlot;
import com.pauix.soviet.init.ModBlocks;
import com.pauix.soviet.init.ModContainerTypes;
import com.pauix.soviet.tileentity.NuclearFurnaceTileEntity;
import com.pauix.soviet.util.FunctionalIntReferenceHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.Objects;

public class NuclearFurnaceContainer extends Container {

    private NuclearFurnaceTileEntity tileEntity;
    private IWorldPosCallable canInteractWithCallable;
    public FunctionalIntReferenceHolder currentSmeltTIme;
    public FunctionalIntReferenceHolder burnTime;
    public FunctionalIntReferenceHolder waterTime;

    // Server Constructor
    public NuclearFurnaceContainer(final int windowId, final PlayerInventory playerInv, final NuclearFurnaceTileEntity tile) {
        super(ModContainerTypes.NUCLEAR_FURNACE.get(), windowId);

        this.tileEntity = tile;
        this.canInteractWithCallable = IWorldPosCallable.of(tile.getWorld(), tile.getPos());

        final int slotSizePlus2 = 18;
        final int startX = 8;

        //Hotbar
        int hotbarY = 142;
        for(int column = 0; column < 9; column++) {
            this.addSlot(new Slot(playerInv, column, startX + (column * slotSizePlus2), hotbarY));
        }

        //Player Inventory
        final int startY = 84;
        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 9; column++) {
                this.addSlot(new Slot(playerInv, 9 + (row * 9) + column, startX + (column * slotSizePlus2), startY + (row * slotSizePlus2)));
            }
        }

        //Furnace Slots
        this.addSlot(new SlotItemHandler(tile.getInventory(), 0, 56, 17)); // Input
        this.addSlot(new NuclearFurnaceResultSlot(tile.getInventory(), 1, 116, 35)); // Output
        this.addSlot(new NuclearFurnaceFuelSlot(tile.getInventory(), 2, 56, 53)); // Uranium!
        this.addSlot(new NuclearFurnaceWaterSlot(tile.getInventory(), 3, 24, 35)); // Water Bucket!

        this.trackInt(currentSmeltTIme = new FunctionalIntReferenceHolder(() -> this.tileEntity.currentSmeltTime, value -> this.tileEntity.currentSmeltTime = value));
        this.trackInt(burnTime = new FunctionalIntReferenceHolder(() -> this.tileEntity.burnTime, value -> this.tileEntity.burnTime = value));
        this.trackInt(waterTime = new FunctionalIntReferenceHolder(() -> this.tileEntity.waterTime, value -> this.tileEntity.waterTime = value));
    }

    // Client Constructor
    public NuclearFurnaceContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
        this(windowId, playerInv, getTileEntity(playerInv, data));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, ModBlocks.NUCLEAR_FURNACE.get());
    }


    private static NuclearFurnaceTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
        Objects.requireNonNull(playerInv, "playerInv cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInv.player.world.getTileEntity(data.readBlockPos());
        if (tileAtPos instanceof NuclearFurnaceTileEntity) {
            return (NuclearFurnaceTileEntity) tileAtPos;
        }
        throw new IllegalStateException("TileEntity is not correct " + tileAtPos);
    }


    @Nonnull
    @Override
    public ItemStack transferStackInSlot(final PlayerEntity player, final int index) {
        ItemStack returnStack = ItemStack.EMPTY;
        final Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            final ItemStack slotStack = slot.getStack();
            returnStack = slotStack.copy();

            final int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.size();
            if (index < containerSlots) {
                if (!mergeItemStack(slotStack, containerSlots, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!mergeItemStack(slotStack, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }
            if (slotStack.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (slotStack.getCount() == returnStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, slotStack);
        }
        return returnStack;
    }

    @OnlyIn(Dist.CLIENT)
    public int getSmeltProgressionScaled() {
        return this.currentSmeltTIme.get() != 0 && this.tileEntity.maxSmeltTime != 0 ? this.currentSmeltTIme.get() * 24 / this.tileEntity.maxSmeltTime : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBurnLeftScaled() {

        return this.burnTime.get() != 0 ? this.burnTime.get() * 13 / this.tileEntity.maxBurnTime : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getWaterProgressionScaled(int lenghtToAccomodate) {
        return this.waterTime.get() != 0 ? this.waterTime.get() * lenghtToAccomodate / this.tileEntity.maxWaterTime : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isBurning() {
        return this.burnTime.get() > 0;
    }



}
