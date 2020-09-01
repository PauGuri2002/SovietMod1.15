package com.pauix.soviet.items;

import net.minecraft.client.audio.Sound;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundEvent;

import java.util.function.Supplier;

public class MusicDiscBase extends MusicDiscItem {
    public MusicDiscBase(int comparatorValueIn, SoundEvent soundIn, Properties builder) {
        super(comparatorValueIn, soundIn, builder);
    }
}
