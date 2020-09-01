package com.pauix.soviet.tileentity;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.blocks.NuclearFurnace;
import com.pauix.soviet.containers.NuclearFurnaceContainer;
import com.pauix.soviet.entities.AtomicBombEntity;
import com.pauix.soviet.init.ModItems;
import com.pauix.soviet.init.ModRecipeSerializers;
import com.pauix.soviet.init.ModTileEntityTypes;
import com.pauix.soviet.recipes.NuclearRecipe;
import com.pauix.soviet.util.NuclearItemHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class NuclearFurnaceTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private ITextComponent customName;
    public int currentSmeltTime;
    public int burnTime;
    public int waterTime;
    public int vodkaTime;
    public final int maxSmeltTime = 200;
    public final int maxBurnTime = 3000;
    public final int maxWaterTime = 12000;
    private NuclearItemHandler inventory;
    Random rand = new Random();
    boolean hasTriedToVodkaBlow = false;



    public NuclearFurnaceTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);

        this.inventory = new NuclearItemHandler(4);
    }

    public NuclearFurnaceTileEntity() {
        this(ModTileEntityTypes.NUCLEAR_FURNACE.get());
    }

    @Override
    public Container createMenu(final int windowId, PlayerInventory playerInv, PlayerEntity player) {
        return new NuclearFurnaceContainer(windowId, playerInv, this);
    }

    @Override
    public void tick() {
        boolean flag1 = false;
        if (this.isBurning()) {
            if(vodkaTime != 0) {
                --this.vodkaTime;
                if(!hasTriedToVodkaBlow) {
                    if (rand.nextInt(50) == 0) {
                        blowUp();
                    } else {
                        hasTriedToVodkaBlow = true;
                        SovietMod.LOGGER.info("Has Tried To Vodka Blow");
                    }
                }
            }

            if(this.waterTime <= 0) {
                blowUp();
            }

            --this.burnTime;
            --this.waterTime;
            this.world.setBlockState(this.getPos(), this.getBlockState().with(NuclearFurnace.LIT, true));
            //SovietMod.LOGGER.info("burnTime decreased to " + this.burnTime);
            //SovietMod.LOGGER.info("waterTime decreased to " + this.waterTime);
        } else if (this.currentSmeltTime > 0) {
            //SovietMod.LOGGER.info("Furnace is burning with no input!");
            this.currentSmeltTime = MathHelper.clamp(this.currentSmeltTime - 2, 0, this.maxSmeltTime);
            hasTriedToVodkaBlow = false;
        } else {
            this.world.setBlockState(this.getPos(), this.getBlockState().with(NuclearFurnace.LIT, false));
            hasTriedToVodkaBlow = false;
        }

        if(this.world != null && !this.world.isRemote) {
            //SovietMod.LOGGER.info("world is not null nor remote.");
            if(this.inventory.getStackInSlot(2).getItem() == ModItems.URANIUM.get() && !this.isBurning()) {
                this.inventory.decrStackSize(2, 1);
                this.burnTime = this.maxBurnTime;
                //SovietMod.LOGGER.info("Detected Uranium and set burnTime to " + this.burnTime);
            }

            if(this.inventory.getStackInSlot(3).getItem() == Items.WATER_BUCKET) {
                this.inventory.decrStackSize(3, 1);
                this.waterTime = this.waterTime + 4000;
                if(this.waterTime > this.maxWaterTime) {
                    this.waterTime = this.maxWaterTime;
                }
                //SovietMod.LOGGER.info("Detected Water and set waterTime to " + this.waterTime);
            } else if(this.inventory.getStackInSlot(3).getItem() == ModItems.VODKA.get()) {
                this.inventory.decrStackSize(3, 1);
                this.waterTime = this.waterTime + 2000;
                if(this.waterTime > this.maxWaterTime) {
                    this.vodkaTime = this.vodkaTime + (this.waterTime - this.maxWaterTime);
                    this.waterTime = this.maxWaterTime;
                } else {
                    this.vodkaTime = this.vodkaTime + 2000;
                }
                //SovietMod.LOGGER.info("Vodka Time: " + this.vodkaTime);

            }

            if(this.getRecipe(this.inventory.getStackInSlot(0)) != null) {
                if(this.currentSmeltTime >= this.maxSmeltTime) {
                    this.currentSmeltTime = 0;
                    ItemStack output = this.getRecipe(this.inventory.getStackInSlot(0)).getRecipeOutput();
                    this.inventory.insertItem(1, output.copy(), false);
                    this.inventory.decrStackSize(0,1);
                    this.hasTriedToVodkaBlow = false;
                    //SovietMod.LOGGER.info("Furnace Ended smelting. Hopefully you received " + output);
                    flag1 = true;
                } else {
                    if(this.isBurning()) {
                        if (this.waterTime > 0) {
                            if(vodkaTime != 0) {
                                this.currentSmeltTime++;
                            }
                            this.currentSmeltTime++;
                            //SovietMod.LOGGER.info("Furnace Lit and smelting. smeltTime:  " + this.currentSmeltTime);
                            flag1 = true;
                        } else {
                            blowUp();
                        }
                    }
                }
            } else {
                this.currentSmeltTime = 0;
                //SovietMod.LOGGER.info("Input was removed. currentSmeltTime set back to " + this.currentSmeltTime);
            }


        }

        if(flag1) {
            this.markDirty();
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    private void blowUp() {
        AtomicBombEntity tntentity = new AtomicBombEntity(this.world, (double) this.pos.getX() + 0.5D, this.pos.getY(), (double) this.pos.getZ() + 0.5D, null);
        this.world.addEntity(tntentity);
        this.world.destroyBlock(this.pos, false);
        this.remove();
        this.world.playSound(null, tntentity.getPosX(), tntentity.getPosY(), tntentity.getPosZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 10.0F, 0.6F);
        SovietMod.LOGGER.info("Furnace blew up.");
    }

    public void setCustomName(ITextComponent name) {
        this.customName = name;
    }

    public ITextComponent getName() {
        return this.customName != null ? this.customName : this.getDefaultName();
    }

    private ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + SovietMod.MOD_ID + ".nuclear_furnace");
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.getName();
    }

    @Nullable
    public ITextComponent getCustomName() {
        return this.customName;
    }

    private boolean isBurning() {
        return this.burnTime > 0;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        if(compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            this.customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
        }

        NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(this.inventory.getSlots(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, inv);
        this.inventory.setNonNullList(inv);

        this.currentSmeltTime = compound.getInt("CurrentSmeltTime");
        this.burnTime = compound.getInt("BurnTime");
        this.waterTime = compound.getInt("WaterTime");
        this.vodkaTime = compound.getInt("VodkaTime");
        this.hasTriedToVodkaBlow = compound.getBoolean("HasTriedToVodkaBlow");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if(this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        ItemStackHelper.saveAllItems(compound, this.inventory.toNonNullList());
        compound.putInt("CurrentSmeltTime", this.currentSmeltTime);
        compound.putInt("BurnTime", this.burnTime);
        compound.putInt("WaterTime", this.waterTime);
        compound.putInt("VodkaTime", this.vodkaTime);
        compound.putBoolean("HasTriedToVodkaBlow", this.hasTriedToVodkaBlow);

        return compound;
    }

    @Nullable
    private NuclearRecipe getRecipe(ItemStack stack) {
        if(stack == null) {
            return null;
        }

        Set<IRecipe<?>> recipes = findRecipesByType(ModRecipeSerializers.NUCLEAR_TYPE, this.world);
        for(IRecipe<?> iRecipe : recipes) {
            NuclearRecipe recipe = (NuclearRecipe) iRecipe;
            if(recipe.matches(new RecipeWrapper(this.inventory), this.world)) {
                return recipe;
            }
        }
        return null;
    }

    private static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn, World world) {
        return world != null ? world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
    }

    @OnlyIn(Dist.CLIENT)
    private static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn) {
        ClientWorld world = Minecraft.getInstance().world;
        return world != null ? world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
    }

    public static Set<ItemStack> getAllRecipeInputs(IRecipeType<?> typeIn, World worldIn) {
        Set<ItemStack> inputs = new HashSet<ItemStack>();
        Set<IRecipe<?>> recipes = findRecipesByType(typeIn, worldIn);
        for(IRecipe<?> recipe : recipes) {
            NonNullList<Ingredient> ingredients = recipe.getIngredients();
            ingredients.forEach(ingredient -> {
                for(ItemStack stack : ingredient.getMatchingStacks()) {
                    inputs.add(stack);
                }
            });
        }
        return inputs;
    }

    public final IItemHandlerModifiable getInventory() {
        return this.inventory;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);
        return new SUpdateTileEntityPacket(this.pos, 0, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);
        return nbt;
    }

    @Override
    public void handleUpdateTag(CompoundNBT nbt) {
        this.read(nbt);
    }

    @Override
    public <T> LazyOptional<T> getCapability( Capability<T> cap, Direction side) {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> this.inventory));
    }
}
