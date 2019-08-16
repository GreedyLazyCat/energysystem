package ru.greedycat.energysystem.block.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.greedycat.energysystem.capabilities.EnergyNetworkListCap;
import ru.greedycat.energysystem.tile.util.NetParticipant;
import ru.greedycat.energysystem.util.EnergyNetworkUtil;

import javax.annotation.Nullable;

public abstract class NetParticipantBlock<T extends NetParticipant>  extends Block{

    public NetParticipantBlock(Material materialIn) {
        super(materialIn);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if(!worldIn.isRemote){
            TileEntity tile = worldIn.getTileEntity(pos);
            if(tile != null && tile instanceof NetParticipant){
                NetParticipant participant = (NetParticipant) tile;
                EnergyNetworkUtil.onNetParticipantPlaced(worldIn, participant, pos);
            }
        }
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
