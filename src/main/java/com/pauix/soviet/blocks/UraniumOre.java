package com.pauix.soviet.blocks;

import com.pauix.soviet.init.ModEffects;
import com.pauix.soviet.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class UraniumOre extends Block {
    public UraniumOre() {
        super(Properties.create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .sound(SoundType.STONE)
                .harvestLevel(1)
                .harvestTool(ToolType.PICKAXE)


        );
    }

    @Override
    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        if (!player.isCreative() && !player.isSpectator()) {
            if (player.inventory.armorItemInSlot(3).getItem() != ModItems.GASMASK_ITEM.get() || player.inventory.armorItemInSlot(2).getItem() != ModItems.ANTIRAD_CHESTPLATE.get() || player.inventory.armorItemInSlot(1).getItem() != ModItems.ANTIRAD_LEGGINGS.get() || player.inventory.armorItemInSlot(0).getItem() != ModItems.ANTIRAD_BOOTS.get()) {
                player.addPotionEffect(new EffectInstance(ModEffects.RADIATION.get(), 5 * 20, 4));
            }
        }
    }

}
