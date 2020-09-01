package com.pauix.soviet.init;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.tileentity.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, SovietMod.MOD_ID);

    public static final RegistryObject<TileEntityType<MatrioshkaChestTileEntity>> MATRIOSHKA_CHEST = TILE_ENTITY_TYPES.register("matrioshka_chest", () -> TileEntityType.Builder
            .create(MatrioshkaChestTileEntity::new, ModBlocks.MATRIOSHKA.get()).build(null));
    public static final RegistryObject<TileEntityType<MatrioshkaChestSTileEntity>> MATRIOSHKA_CHEST_S = TILE_ENTITY_TYPES.register("matrioshka_chest_s", () -> TileEntityType.Builder
            .create(MatrioshkaChestSTileEntity::new, ModBlocks.MATRIOSHKA_S.get()).build(null));
    public static final RegistryObject<TileEntityType<MatrioshkaChestLTileEntity>> MATRIOSHKA_CHEST_L = TILE_ENTITY_TYPES.register("matrioshka_chest_l", () -> TileEntityType.Builder
            .create(MatrioshkaChestLTileEntity::new, ModBlocks.MATRIOSHKA_L.get()).build(null));
    public static final RegistryObject<TileEntityType<NuclearFurnaceTileEntity>> NUCLEAR_FURNACE = TILE_ENTITY_TYPES.register("nuclear_furnace", () -> TileEntityType.Builder
            .create(NuclearFurnaceTileEntity::new, ModBlocks.NUCLEAR_FURNACE.get()).build(null));
    public static final RegistryObject<TileEntityType<UnderwaterMineTileEntity>> UNDERWATER_MINE = TILE_ENTITY_TYPES.register("underwater_mine", () -> TileEntityType.Builder
            .create(UnderwaterMineTileEntity::new, ModBlocks.UNDERWATER_MINE.get()).build(null));
    public static final RegistryObject<TileEntityType<AtomicUnderwaterMineTileEntity>> ATOMIC_UNDERWATER_MINE = TILE_ENTITY_TYPES.register("atomic_underwater_mine", () -> TileEntityType.Builder
            .create(AtomicUnderwaterMineTileEntity::new, ModBlocks.ATOMIC_UNDERWATER_MINE.get()).build(null));
}
