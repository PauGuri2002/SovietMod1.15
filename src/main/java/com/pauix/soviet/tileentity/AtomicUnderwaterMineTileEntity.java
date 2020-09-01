package com.pauix.soviet.tileentity;

import com.pauix.soviet.blocks.UnderwaterMine;
import com.pauix.soviet.entities.AtomicBombEntity;
import com.pauix.soviet.init.ModTileEntityTypes;
import net.minecraft.block.Blocks;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;

public class AtomicUnderwaterMineTileEntity extends TileEntity implements ITickableTileEntity {

    private ITextComponent customName;
    private boolean isExploding = false;
    private int placeTimer = 100;

    public AtomicUnderwaterMineTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public AtomicUnderwaterMineTileEntity() {
        this(ModTileEntityTypes.ATOMIC_UNDERWATER_MINE.get());
    }

    @Override
    public void tick() {
        if(this.world != null && !this.world.isRemote ) {
            if (this.placeTimer > 0) {
                --this.placeTimer;
                this.world.setBlockState(this.getPos(), this.getBlockState().with(UnderwaterMine.ARMED, false));
            } else if(!this.isExploding) {
                final AxisAlignedBB t = new AxisAlignedBB(this.pos).grow(3);
                this.world.setBlockState(this.getPos(), this.getBlockState().with(UnderwaterMine.ARMED, true));
                if (!this.world.getEntitiesWithinAABB(PlayerEntity.class, t).isEmpty() || !this.world.getEntitiesWithinAABB(MonsterEntity.class, t).isEmpty()) {
                    this.isExploding = true;
                    AtomicBombEntity tntentity = new AtomicBombEntity(this.world, (double) this.pos.getX() + 0.5D, this.pos.getY(), (double) this.pos.getZ() + 0.5D, null);
                    this.world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
                    this.world.addEntity(tntentity);
                    this.world.playSound((PlayerEntity) null, tntentity.getPosX(), tntentity.getPosY(), tntentity.getPosZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 10.0F, 0.6F);

                }

            }
        }
    }

}
