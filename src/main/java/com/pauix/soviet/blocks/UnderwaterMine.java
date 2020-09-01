package com.pauix.soviet.blocks;

import com.pauix.soviet.entities.AtomicBombEntity;
import com.pauix.soviet.entities.InstantTNTEntity;
import com.pauix.soviet.init.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.*;
import net.minecraftforge.common.ToolType;

import java.util.stream.Stream;

public class UnderwaterMine extends Block {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty ARMED = BooleanProperty.create("armed");
    private boolean isAtomic;

    public UnderwaterMine(boolean isAtomic) {
        super(Block.Properties.create(Material.ANVIL)
                .hardnessAndResistance(2.0f, 3.0f)
                .sound(SoundType.ANVIL)
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE)
                .doesNotBlockMovement()
        );
        this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false).with(ARMED, false));
        this.isAtomic = isAtomic;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return isAtomic ? ModTileEntityTypes.ATOMIC_UNDERWATER_MINE.get().create() : ModTileEntityTypes.UNDERWATER_MINE.get().create();
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return worldIn.getFluidState(pos).getFluid() == Fluids.WATER;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        IFluidState ifluidstate = context.getWorld().getFluidState(blockpos);
        return this.getDefaultState().with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosionIn) {
        return false;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, ARMED);
    }

    @Override
    public IFluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
            worldIn.removeTileEntity(pos);
        }
    }

    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
        if (!worldIn.isRemote) {
            if(isAtomic) {
                AtomicBombEntity tntentity = new AtomicBombEntity(worldIn, (double) ((float) pos.getX() + 0.5F), (double) pos.getY(), (double) ((float) pos.getZ() + 0.5F), explosionIn.getExplosivePlacedBy());
                worldIn.addEntity(tntentity);
            } else {
                InstantTNTEntity tntentity = new InstantTNTEntity(worldIn, (double) ((float) pos.getX() + 0.5F), (double) pos.getY(), (double) ((float) pos.getZ() + 0.5F), explosionIn.getExplosivePlacedBy());
                worldIn.addEntity(tntentity);
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape defaultShape = Block.makeCuboidShape(2, 2, 2, 14, 14, 14);
        VoxelShape armedShape = Stream.of(
                        Block.makeCuboidShape(2, 2, 2, 14, 14, 14),
                        Block.makeCuboidShape(0, 8, 6, 16, 9, 7),
                        Block.makeCuboidShape(4, 0, 4, 5, 16, 5),
                        Block.makeCuboidShape(11, 0, 11, 12, 16, 12),
                        Block.makeCuboidShape(4, 0, 11, 5, 16, 12),
                        Block.makeCuboidShape(8, 0, 9, 9, 16, 10),
                        Block.makeCuboidShape(7, 0, 6, 8, 16, 7),
                        Block.makeCuboidShape(11, 0, 4, 12, 16, 5),
                        Block.makeCuboidShape(4, 4, 0, 5, 5, 16),
                        Block.makeCuboidShape(6, 8, 0, 7, 9, 16),
                        Block.makeCuboidShape(9, 7, 0, 10, 8, 16),
                        Block.makeCuboidShape(11, 11, 0, 12, 12, 16),
                        Block.makeCuboidShape(11, 4, 0, 12, 5, 16),
                        Block.makeCuboidShape(4, 11, 0, 5, 12, 16),
                        Block.makeCuboidShape(0, 11, 4, 16, 12, 5),
                        Block.makeCuboidShape(0, 4, 11, 16, 5, 12),
                        Block.makeCuboidShape(0, 11, 11, 16, 12, 12),
                        Block.makeCuboidShape(0, 7, 9, 16, 8, 10),
                        Block.makeCuboidShape(0, 4, 4, 16, 5, 5)
                ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
        return state.get(ARMED) ? armedShape : defaultShape;
    }
}
