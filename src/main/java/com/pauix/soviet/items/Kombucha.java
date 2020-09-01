package com.pauix.soviet.items;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.init.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

import javax.swing.*;

public class Kombucha extends Item {
    public Kombucha() {
        super(new Properties()
                .group(SovietMod.TAB)
                .food(new Food.Builder()
                    .hunger(4)
                    .saturation(2.5F)
                    .build())

        );
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if(entityLiving instanceof PlayerEntity) {
            PlayerEntity entityplayer = (PlayerEntity) entityLiving;
            entityplayer.getFoodStats().addStats(4, 2.5F);
            entityplayer.clearActivePotions();
            entityplayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 50, 9));
            stack.shrink(1);
            entityplayer.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
            return stack;

        }


    return ItemStack.EMPTY;
    }

}
