package com.pauix.soviet.items;

import com.pauix.soviet.SovietMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class Caviar extends Item {
    public Caviar() {
        super(new Properties()
                .group(SovietMod.TAB)
                .food(new Food.Builder()
                    .hunger(1)
                    .saturation(0.5F)
                        .fastToEat()
                    .build())

        );
    }

}
