package ru.greedycat.energysystem.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import ru.greedycat.energysystem.api.IEnergyProviderCap;
import ru.greedycat.energysystem.block.util.NetParticipantBlock;
import ru.greedycat.energysystem.capabilities.EnergyProviderCap;
import ru.greedycat.energysystem.tile.EnergyProviderTile;

import javax.annotation.Nullable;

public class EnergyProviderBlock extends NetParticipantBlock<EnergyProviderTile> {
    public EnergyProviderBlock(Material materialIn) {
        super(materialIn);
        this.setRegistryName("energyprovider");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        //<for debug>
        if(!worldIn.isRemote) {
            TileEntity entity = worldIn.getTileEntity(pos);

            if (entity != null && entity instanceof EnergyProviderTile) {
                EnergyProviderTile tile = (EnergyProviderTile) entity;
                if (tile.hasCapability(EnergyProviderCap.ENERGY_PROVIDER, null)) {
                    IEnergyProviderCap provider = tile.getCapability(EnergyProviderCap.ENERGY_PROVIDER, null);
                    String str = (worldIn.isRemote) ? " client" : " server";
                    playerIn.sendMessage(new TextComponentString("Energy stored: " + provider.getEnergyStored() + "\n Type: " + tile.getTYPE() + str));
                    if (playerIn.getHeldItem(hand) != ItemStack.EMPTY && playerIn.getHeldItem(hand).getItem() == Items.STICK) {
                        provider.setEnergyStored(provider.getEnergyStored() + 10);
                    }
                }
            }
        }
        //</for debug>
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public Class<EnergyProviderTile> getTileEntityClass() {
        return EnergyProviderTile.class;
    }

    @Nullable
    @Override
    public EnergyProviderTile createTileEntity(World world, IBlockState blockState) {
        return new EnergyProviderTile();
    }
}
