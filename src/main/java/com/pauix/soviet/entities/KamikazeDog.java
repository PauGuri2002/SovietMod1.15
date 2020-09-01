package com.pauix.soviet.entities;

import com.pauix.soviet.SovietMod;
import com.pauix.soviet.init.ModEntityTypes;
import com.pauix.soviet.init.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class KamikazeDog extends WolfEntity {

    public KamikazeDog(EntityType<? extends WolfEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.sitGoal = new SitGoal(this);
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, this.sitGoal);
        this.goalSelector.addGoal(3, new KamikazeDog.AvoidEntityGoal<>(this, LlamaEntity.class, 24.0F, 1.5D, 1.5D));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new BegGoal(this, 8.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(4, new NonTamedTargetGoal<>(this, AnimalEntity.class, false, TARGET_ENTITIES));
        this.targetSelector.addGoal(4, new NonTamedTargetGoal<>(this, TurtleEntity.class, false, TurtleEntity.TARGET_DRY_BABY));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, AbstractSkeletonEntity.class, false));
    }

    @Override
    public KamikazeDog createChild(AgeableEntity ageable) {
        KamikazeDog entity = new KamikazeDog(ModEntityTypes.KAMIKAZE_DOG.get(), this.world);
        entity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(entity)), SpawnReason.BREEDING, (ILivingEntityData)null, (CompoundNBT)null);
        return entity;
    }

    class AvoidEntityGoal<T extends LivingEntity> extends net.minecraft.entity.ai.goal.AvoidEntityGoal<T> {
        private final KamikazeDog wolf;

        public AvoidEntityGoal(KamikazeDog wolfIn, Class<T> entityClassToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
            super(wolfIn, entityClassToAvoidIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
            this.wolf = wolfIn;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            if (super.shouldExecute() && this.avoidTarget instanceof LlamaEntity) {
                return !this.wolf.isTamed() && this.avoidLlama((LlamaEntity)this.avoidTarget);
            } else {
                return false;
            }
        }

        private boolean avoidLlama(LlamaEntity llamaIn) {
            return llamaIn.getStrength() >= KamikazeDog.this.rand.nextInt(5);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            KamikazeDog.this.setAttackTarget((LivingEntity)null);
            super.startExecuting();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            KamikazeDog.this.setAttackTarget((LivingEntity)null);
            super.tick();
        }
    }

    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
        if (tamed) {
            this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
            this.setHealth(20.0F);
        } else {
            this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        }

        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    @Override
    public void onDeath(DamageSource cause) {
        if(cause.isExplosion()) {
            InstantTNTEntity tntentity = new InstantTNTEntity(this.world, (double) this.getPosX(), this.getPosY(), (double) this.getPosZ(), null);
            this.world.addEntity(tntentity);
        }
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if(!this.world.isRemote) {
            if(this.getAttackTarget() == null) {
                if(this.isAngry()) { this.setAngry(false); }
            } else if(this.world.getEntitiesWithinAABB(this.getAttackTarget().getClass(), this.getBoundingBox().grow(1.5D)).size() != 0) {
                    InstantTNTEntity tntentity = new InstantTNTEntity(this.world, (double) this.getPosX(), this.getPosY(), (double) this.getPosZ(), null);
                    this.world.addEntity(tntentity);
                    this.remove();
            }
        }
    }
}
