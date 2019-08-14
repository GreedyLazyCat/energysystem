package ru.greedycat.energysystem.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import ru.greedycat.energysystem.block.util.NetParticipantBlock;
import ru.greedycat.energysystem.tile.EnergyReceiverTile;

import javax.annotation.Nullable;

public class EnergyReceiverBlock extends NetParticipantBlock<EnergyReceiverTile>{
    public EnergyReceiverBlock(Material materialIn) {
        super(materialIn);
        this.setRegistryName("energyreceiver");
        this.setUnlocalizedName("energyreceiver");
    }

    @Override
    public Class<EnergyReceiverTile> getTileEntityClass() {
        return EnergyReceiverTile.class;
    }

    @Nullable
    @Override
    public EnergyReceiverTile createTileEntity(World world, IBlockState blockState) {
        return new EnergyReceiverTile();
    }
}
