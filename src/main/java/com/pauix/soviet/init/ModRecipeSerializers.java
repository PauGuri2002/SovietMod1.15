package com.pauix.soviet.init;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.recipes.INuclearRecipe;
import com.pauix.soviet.recipes.NuclearRecipe;
import com.pauix.soviet.recipes.NuclearRecipeSerializer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeSerializers {

    public static final IRecipeSerializer<NuclearRecipe> NUCLEAR_RECIPE_SERIALIZER = new NuclearRecipeSerializer();
    public static final IRecipeType<INuclearRecipe> NUCLEAR_TYPE = registerType(INuclearRecipe.RECIPE_TYPE_ID);

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS, SovietMod.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<?>> NUCLEAR_SERIALIZER = RECIPE_SERIALIZERS.register("nuclear", () -> NUCLEAR_RECIPE_SERIALIZER);

    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {

        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }

    private static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId) {
        return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
    }



}
