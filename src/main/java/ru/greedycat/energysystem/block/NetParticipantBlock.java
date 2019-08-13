package ru.greedycat.energysystem.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.greedycat.energysystem.tile.NetParticipant;

import javax.annotation.Nullable;

public abstract class NetParticipantBlock<T extends NetParticipant>  extends Block{

    public NetParticipantBlock(Material materialIn) {
        super(materialIn);
    }

    public abstract Class<T> getTileEntityClass();

    public T getTileEntity(IBlockAccess world, BlockPos position) {

        return (T) world.getTileEntity(position);
    }

    @Override
    public boolean hasTileEntity(IBlockState blockState) {

        return true;
    }

    @Nullable
    @Override
    public abstract T createTileEntity(World world, IBlockState blockState);

}
