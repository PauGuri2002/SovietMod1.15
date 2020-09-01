package com.pauix.soviet.items;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.init.ModBlocks;
import com.pauix.soviet.init.ModItems;
import com.pauix.soviet.util.enums.ModArmorMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GasmaskItem extends BlockItem {
    public GasmaskItem(Block block) {
        super(block, new Item.Properties().group(SovietMod.TAB)
        .maxStackSize(1));
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
        if(armorType == EquipmentSlotType.HEAD) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean canPlace(BlockItemUseContext p_195944_1_, BlockState p_195944_2_) {
        return false;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.inventory.armorInventory.set(3, new ItemStack(ModBlocks.GASMASK.get()));
        return new ActionResult<>(ActionResultType.SUCCESS, ItemStack.EMPTY);
    }
}



