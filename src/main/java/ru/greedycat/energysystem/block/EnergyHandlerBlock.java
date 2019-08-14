package ru.greedycat.energysystem.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import ru.greedycat.energysystem.block.util.NetParticipantBlock;
import ru.greedycat.energysystem.tile.EnergyHandlerTile;

import javax.annotation.Nullable;

public class EnergyHandlerBlock extends NetParticipantBlock<EnergyHandlerTile> {
    public EnergyHandlerBlock(Material materialIn) {
        super(materialIn);
        this.setRegistryName("energyhandler");
        this.setUnlocalizedName("energyhandler");
    }

    @Override
    public Class<EnergyHandlerTile> getTileEntityClass() {
        return EnergyHandlerTile.class;
    }

    @Nullable
    @Override
    public EnergyHandlerTile createTileEntity(World world, IBlockState blockState) {
        return new EnergyHandlerTile();
    }
}
