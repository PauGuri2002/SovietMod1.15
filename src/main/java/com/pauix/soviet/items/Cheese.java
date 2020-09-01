package com.pauix.soviet.items;

import com.pauix.soviet.SovietMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.SoupItem;

public class Cheese extends Item {
    public Cheese() {
        super(new Properties()
                .group(SovietMod.TAB)
                .food(new Food.Builder()
                    .hunger(2)
                    .saturation(1.5F)
                        .fastToEat()
                    .build())

        );
    }

}
