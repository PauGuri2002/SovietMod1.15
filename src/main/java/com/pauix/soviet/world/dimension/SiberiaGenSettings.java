package com.pauix.soviet.world.dimension;

import net.minecraft.world.gen.GenerationSettings;

public class SiberiaGenSettings extends GenerationSettings {

    public int getBiomeSize() {
        return 4;
    }

    public int getBiomeId() {
        return -1;
    }

    @Override
    public int getBedrockFloorHeight() {
        return 0;
    }
}
