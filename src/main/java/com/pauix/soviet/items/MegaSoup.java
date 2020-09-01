package com.pauix.soviet.items;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.init.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;

public class MegaSoup extends Item {
    public MegaSoup() {
        super(new Item.Properties()
                .group(SovietMod.TAB)
                .food(new Food.Builder()
                    .hunger(10)
                    .saturation(8.5F)
                    .build())
                .maxStackSize(1)

        );
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if(entityLiving instanceof PlayerEntity) {
            PlayerEntity entityplayer = (PlayerEntity) entityLiving;
            entityplayer.getFoodStats().addStats(10, 8.5F);
            entityplayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 100, 9));
        }
        return new ItemStack(ModItems.MEGA_BOWL.get());

    }




}
