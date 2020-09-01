package com.pauix.soviet.entities;

import com.pauix.soviet.init.ModEntityTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Predicate;


//public class Bear extends AnimalEntity {
//
//    private int warningSoundTicks;
//
//    public Bear(EntityType<? extends AnimalEntity> type, World worldIn) {
//        super(type, worldIn);
//    }
//
//    @Override
//    protected void registerAttributes() {
//        super.registerAttributes();
//        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
//        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
//        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
//        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
//        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
//    }
//
//    @Override
//    public AgeableEntity createChild(AgeableEntity ageable) {
//        Bear entity = new Bear(ModEntityTypes.BEAR.get(), this.world);
//        entity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(entity)), SpawnReason.BREEDING, (ILivingEntityData)null, (CompoundNBT)null);
//        return entity;
//    }
//
//    @Override
//    protected void registerGoals() {
//        super.registerGoals();
//        super.registerGoals();
//        this.goalSelector.addGoal(0, new SwimGoal(this));
//        this.goalSelector.addGoal(1, new Bear.MeleeAttackGoal());
//        this.goalSelector.addGoal(1, new Bear.PanicGoal());
//        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
//        this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
//        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
//        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
//        this.targetSelector.addGoal(1, new Bear.HurtByTargetGoal());
//        this.targetSelector.addGoal(2, new Bear.AttackPlayerGoal());
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, FoxEntity.class, 10, true, true, (Predicate<LivingEntity>)null));
//    }
//
//
//    protected void playWarningSound() {
//        if (this.warningSoundTicks <= 0) {
//            this.playSound(SoundEvents.ENTITY_POLAR_BEAR_WARNING, 1.0F, this.getSoundPitch());
//            this.warningSoundTicks = 40;
//        }
//
//    }
//
//    class MeleeAttackGoal extends net.minecraft.entity.ai.goal.MeleeAttackGoal {
//        public MeleeAttackGoal() {
//            super(Bear.this, 1.25D, true);
//        }
//
//        protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
//            double d0 = this.getAttackReachSqr(enemy);
//            if (distToEnemySqr <= d0 && this.attackTick <= 0) {
//                this.attackTick = 20;
//                this.attacker.attackEntityAsMob(enemy);
//            } else if (distToEnemySqr <= d0 * 2.0D) {
//                if (this.attackTick <= 0) {
//                    this.attackTick = 20;
//                }
//
//                if (this.attackTick <= 10) {
//                    Bear.this.playWarningSound();
//                }
//            } else {
//                this.attackTick = 20;
//            }
//
//        }
//    }
//
//    class PanicGoal extends net.minecraft.entity.ai.goal.PanicGoal {
//        public PanicGoal() {
//            super(Bear.this, 2.0D);
//        }
//
//        public boolean shouldExecute() {
//            return !Bear.this.isChild() && !Bear.this.isBurning() ? false : super.shouldExecute();
//        }
//    }
//
//    class HurtByTargetGoal extends net.minecraft.entity.ai.goal.HurtByTargetGoal {
//        public HurtByTargetGoal() {
//            super(Bear.this);
//        }
//
//        public void startExecuting() {
//            super.startExecuting();
//            if (Bear.this.isChild()) {
//                this.alertOthers();
//                this.resetTask();
//            }
//
//        }
//
//        protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
//            if (mobIn instanceof PolarBearEntity && !mobIn.isChild()) {
//                super.setAttackTarget(mobIn, targetIn);
//            }
//
//        }
//    }
//
//    @Override
//    public void onStruckByLightning(LightningBoltEntity lightningBolt) {
//        this.remove();
//        PolarBearEntity entity = new PolarBearEntity(EntityType.POLAR_BEAR, this.world);
//        entity.setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
//        this.world.addEntity(entity);
//    }
//
//    class AttackPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
//        public AttackPlayerGoal() {
//            super(Bear.this, PlayerEntity.class, 20, true, true, (Predicate<LivingEntity>)null);
//        }
//
//        /**
//         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
//         * method as well.
//         */
//        public boolean shouldExecute() {
//            if (Bear.this.isChild()) {
//                return false;
//            } else {
//                if (super.shouldExecute()) {
//                    for(PolarBearEntity polarbearentity : Bear.this.world.getEntitiesWithinAABB(PolarBearEntity.class, Bear.this.getBoundingBox().grow(8.0D, 4.0D, 8.0D))) {
//                        if (polarbearentity.isChild()) {
//                            return true;
//                        }
//                    }
//                }
//
//                return false;
//            }
//        }
//
//        protected double getTargetDistance() {
//            return super.getTargetDistance() * 0.5D;
//        }
//    }
//}
