package com.pauix.soviet.items;

import com.pauix.soviet.SovietMod;
import net.minecraft.item.*;

public class Soup extends SoupItem {
    public Soup() {
        super(new Properties()
                .group(SovietMod.TAB)
                .food(new Food.Builder()
                    .hunger(5)
                    .saturation(5.0F)
                    .build())
                .maxStackSize(1)

        );
    }

}
