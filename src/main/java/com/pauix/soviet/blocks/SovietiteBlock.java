package com.pauix.soviet.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class SovietiteBlock extends Block {
    public SovietiteBlock() {
        super(Block.Properties.create(Material.GLASS)
                .hardnessAndResistance(0.3f, 1200.0f)
                .sound(SoundType.GLASS)
                .lightValue(15)
        );
    }
}
