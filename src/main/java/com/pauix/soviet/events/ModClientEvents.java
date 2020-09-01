package com.pauix.soviet.events;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.entities.KamikazeDog;
import com.pauix.soviet.init.ModBlocks;
import com.pauix.soviet.init.ModEffects;
import com.pauix.soviet.init.ModEntityTypes;
import com.pauix.soviet.init.ModItems;

import javafx.scene.layout.Priority;
import net.minecraft.block.Block;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;


import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;

import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.EventListenerProxy;
import java.util.Random;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = SovietMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent()
    public static void onUraniumBreak(BlockEvent.BreakEvent event) {
        if (event.isCancelable()) {
            if (!event.getPlayer().isCreative() && !event.getPlayer().isSpectator()) {
                PlayerEntity player = event.getPlayer();
                Block block = event.getState().getBlock();
                World world = player.getEntityWorld();
                if (block == ModBlocks.URANIUM_ORE.get()) {
                    if (player.inventory.armorItemInSlot(3).getItem() != ModItems.GASMASK_ITEM.get() || player.inventory.armorItemInSlot(2).getItem() != ModItems.ANTIRAD_CHESTPLATE.get() || player.inventory.armorItemInSlot(1).getItem() != ModItems.ANTIRAD_LEGGINGS.get() || player.inventory.armorItemInSlot(0).getItem() != ModItems.ANTIRAD_BOOTS.get()) {
                        player.addPotionEffect(new EffectInstance(ModEffects.RADIATION.get(), 5 * 20, 4));
                        event.setCanceled(true);
                    }
                }
            }

        }
    }


    @SubscribeEvent()
    public static void onUraniumInventory(TickEvent.PlayerTickEvent event) {
        if (!event.player.isCreative() && !event.player.isSpectator()) {
            PlayerEntity player = event.player;
            if (player.inventory.hasItemStack(new ItemStack(ModItems.URANIUM.get())) || player.inventory.hasItemStack(new ItemStack(ModItems.URANIUM_ORE_ITEM.get()))) {
                if (player.inventory.armorItemInSlot(3).getItem() != ModItems.GASMASK_ITEM.get() || player.inventory.armorItemInSlot(2).getItem() != ModItems.ANTIRAD_CHESTPLATE.get() || player.inventory.armorItemInSlot(1).getItem() != ModItems.ANTIRAD_LEGGINGS.get() || player.inventory.armorItemInSlot(0).getItem() != ModItems.ANTIRAD_BOOTS.get()) {
                    player.addPotionEffect(new EffectInstance(ModEffects.RADIATION.get(), 10, 9));
                } else {
                    player.removePotionEffect(ModEffects.RADIATION.get());
                }
            }
        }
    }

    @SubscribeEvent
    public static void antiradAntiWither(TickEvent.PlayerTickEvent event) {
        if (!event.player.isCreative() && !event.player.isSpectator()) {
            PlayerEntity player = event.player;
            if (player.inventory.armorItemInSlot(3).getItem() == ModItems.GASMASK_ITEM.get() && player.inventory.armorItemInSlot(2).getItem() == ModItems.ANTIRAD_CHESTPLATE.get() && player.inventory.armorItemInSlot(1).getItem() == ModItems.ANTIRAD_LEGGINGS.get() && player.inventory.armorItemInSlot(0).getItem() == ModItems.ANTIRAD_BOOTS.get()) {
                player.removePotionEffect(ModEffects.RADIATION.get());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTargeted(LivingSetAttackTargetEvent event) {
        if(event.getTarget() instanceof PlayerEntity) {
            if (event.getEntityLiving() instanceof MobEntity) {
                PlayerEntity player = (PlayerEntity) event.getTarget();
                if (player.inventory.armorItemInSlot(3).getItem() == ModItems.BALACLAVA.get()) {
                    MobEntity mob = (MobEntity) event.getEntityLiving();
                    mob.setAttackTarget(null);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onWolfRightClick(PlayerInteractEvent.EntityInteract event) {
        if(!event.getWorld().isRemote && event.getTarget() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) event.getTarget();
            if(target instanceof WolfEntity && !(target instanceof KamikazeDog) && event.getPlayer().getHeldItemMainhand().getItem() == Items.TNT) {
                event.getPlayer().getHeldItemMainhand().shrink(1);
                KamikazeDog kamikazeDog = new KamikazeDog(ModEntityTypes.KAMIKAZE_DOG.get(), event.getWorld());
                boolean isTamed = ((WolfEntity) target).isTamed();
                UUID getOwner = ((WolfEntity) target).getOwner() instanceof PlayerEntity ? ((WolfEntity) target).getOwner().getUniqueID() : null;
                kamikazeDog.setPositionAndRotation(target.getPosX(), target.getPosY(), target.getPosZ(), target.rotationYaw, target.rotationPitch);
                target.remove();
                event.getWorld().addEntity(kamikazeDog);
                kamikazeDog.setTamed(isTamed);
                kamikazeDog.setOwnerId(getOwner);
                SovietMod.LOGGER.info("Kamikaze wolf is tamed: " + kamikazeDog.isTamed());
                SovietMod.LOGGER.info("Owner is: " + ((WolfEntity) target).getOwner());
            }
        }
    }

}
