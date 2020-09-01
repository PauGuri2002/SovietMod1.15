package com.pauix.soviet.items;

import com.pauix.soviet.SovietMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class CabbageFood extends Item {
    public CabbageFood() {
        super(new Properties()
                .group(SovietMod.TAB)
                .food(new Food.Builder()
                    .hunger(4)
                    .saturation(5F)
                    .build())

        );
    }
}
