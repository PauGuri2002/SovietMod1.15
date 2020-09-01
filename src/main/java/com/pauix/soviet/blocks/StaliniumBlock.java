package com.pauix.soviet.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class StaliniumBlock extends Block {
    public StaliniumBlock() {
        super(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(70.0f, 5000.0f)
                .sound(SoundType.METAL)
                .harvestLevel(4)
                .harvestTool(ToolType.PICKAXE)

        );
    }
}
