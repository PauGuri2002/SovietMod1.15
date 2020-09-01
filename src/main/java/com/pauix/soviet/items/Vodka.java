package com.pauix.soviet.items;

import com.pauix.soviet.SovietMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class Vodka extends Item {
    public Vodka() {
        super(new Item.Properties()
                .group(SovietMod.TAB)
                .food(new Food.Builder()
                    .hunger(1)
                    .saturation(1.5F)
                    .effect(() -> new EffectInstance(Effects.SPEED, 400, 10), 1)
                    .effect(() -> new EffectInstance(Effects.STRENGTH, 400, 5), 1)
                    .effect(() -> new EffectInstance(Effects.RESISTANCE, 400, 5), 1)
                    .effect(() -> new EffectInstance(Effects.NIGHT_VISION, 400, 0), 1)
                    .effect(() -> new EffectInstance(Effects.JUMP_BOOST, 400, 5), 1)
                    .effect(() -> new EffectInstance(Effects.NAUSEA, 400, 0), 0.1f)
                    .effect(() -> new EffectInstance(Effects.HUNGER, 400, 2), 0.2f)
                    .effect(() -> new EffectInstance(Effects.POISON, 400, 1), 0.02f)
                    .meat()
                    .setAlwaysEdible()
                    .build())

        );
    }
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }


}
