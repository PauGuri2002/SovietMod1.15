package com.pauix.soviet.init;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.blocks.*;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, SovietMod.MOD_ID);


    // Blocks
    public static final RegistryObject<Block> SOVIETITE_BLOCK = BLOCKS.register("sovietite_block", SovietiteBlock::new);
    public static final RegistryObject<Block> STALINIUM_BLOCK = BLOCKS.register("stalinium_block", StaliniumBlock::new);
    public static final RegistryObject<Block> STALINIUM_ORE = BLOCKS.register("stalinium_ore", StaliniumOre::new);
    public static final RegistryObject<Block> URANIUM_ORE = BLOCKS.register("uranium_ore", UraniumOre::new);
    public static final RegistryObject<Block> MATRIOSHKA = BLOCKS.register("matrioshka", Matrioshka::new);
    public static final RegistryObject<Block> MATRIOSHKA_S = BLOCKS.register("matrioshka_s", MatrioshkaS::new);
    public static final RegistryObject<Block> MATRIOSHKA_L = BLOCKS.register("matrioshka_l", MatrioshkaL::new);
    public static final RegistryObject<Block> FLAG = BLOCKS.register("flag", Flag::new);
    public static final RegistryObject<Block> ATOMIC_BOMB = BLOCKS.register("atomic_bomb", AtomicBomb::new);
    public static final RegistryObject<Block> UNDERWATER_MINE = BLOCKS.register("underwater_mine", () -> new UnderwaterMine(false));
    public static final RegistryObject<Block> ATOMIC_UNDERWATER_MINE = BLOCKS.register("atomic_underwater_mine", () -> new UnderwaterMine(true));
    public static final RegistryObject<Block> HARMONIUM_ORE = BLOCKS.register("harmonium_ore", HarmoniumOre::new);
    public static final RegistryObject<Block> GASMASK = BLOCKS.register("gasmask", Gasmask::new);
    public static final RegistryObject<Block> NUCLEAR_FURNACE = BLOCKS.register("nuclear_furnace", NuclearFurnace::new);
    //public static final RegistryObject<Block> STALINIUM_DUST = BLOCKS.register("stalinium_dust", StaliniumDust::new);

    // Crops
    public static final RegistryObject<Block> CABBAGE = BLOCKS.register("cabbage", Cabbage::new);


}
