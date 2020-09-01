package com.pauix.soviet.init;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.containers.MatrioshkaChestContainer;
import com.pauix.soviet.containers.MatrioshkaChestLContainer;
import com.pauix.soviet.containers.MatrioshkaChestSContainer;
import com.pauix.soviet.containers.NuclearFurnaceContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, SovietMod.MOD_ID);

    // Containers
    public static final RegistryObject<ContainerType<MatrioshkaChestContainer>> MATRIOSHKA_CHEST = CONTAINER_TYPES
            .register("matrioshka_chest", () -> IForgeContainerType.create(MatrioshkaChestContainer::new));
    public static final RegistryObject<ContainerType<MatrioshkaChestSContainer>> MATRIOSHKA_CHEST_S = CONTAINER_TYPES
            .register("matrioshka_chest_s", () -> IForgeContainerType.create(MatrioshkaChestSContainer::new));
    public static final RegistryObject<ContainerType<MatrioshkaChestLContainer>> MATRIOSHKA_CHEST_L = CONTAINER_TYPES
            .register("matrioshka_chest_l", () -> IForgeContainerType.create(MatrioshkaChestLContainer::new));
    public static final RegistryObject<ContainerType<NuclearFurnaceContainer>> NUCLEAR_FURNACE = CONTAINER_TYPES
            .register("nuclear_furnace", () -> IForgeContainerType.create(NuclearFurnaceContainer::new));
}
