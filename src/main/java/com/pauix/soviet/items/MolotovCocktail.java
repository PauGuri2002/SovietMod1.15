package com.pauix.soviet.items;

import com.pauix.soviet.SovietMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;

import javax.annotation.Nullable;

public class MolotovCocktail extends Item {


    public MolotovCocktail() {
        super(new Item.Properties().group(SovietMod.TAB).rarity(Rarity.RARE).maxStackSize(16));
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        if(entity.collided) {
            cocktailExplosion(entity, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 2.0F, true, Explosion.Mode.NONE);
            entity.remove();
        }
        return super.onEntityItemUpdate(stack, entity);
    }

    public Explosion cocktailExplosion(@Nullable Entity entityIn, double xIn, double yIn, double zIn, float explosionRadius, boolean causesFire, Explosion.Mode modeIn) {
        Explosion explosion = new Explosion(entityIn.world, entityIn, xIn, yIn, zIn, explosionRadius, causesFire, modeIn);

        if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(entityIn.world, explosion)) return explosion;

        explosion.doExplosionA();
        explosion.doExplosionB(false);
        entityIn.world.playSound(xIn, yIn, zIn, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 4.0F, (1.0F + (entityIn.world.rand.nextFloat() - entityIn.world.rand.nextFloat()) * 0.2F) * 1.0F, false);
        return explosion;
    }

}
