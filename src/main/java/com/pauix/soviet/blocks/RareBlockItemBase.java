package com.pauix.soviet.blocks;

import com.pauix.soviet.SovietMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Rarity;

public class RareBlockItemBase extends BlockItem {

    public RareBlockItemBase(Block block) {
        super(block, new Properties().group(SovietMod.TAB)
        .rarity(Rarity.RARE)

        );
    }

}
