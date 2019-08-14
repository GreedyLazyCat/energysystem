package ru.greedycat.energysystem.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import ru.greedycat.energysystem.block.util.NetParticipantBlock;
import ru.greedycat.energysystem.tile.EnergyProviderTile;

import javax.annotation.Nullable;

public class EnergyProviderBlock extends NetParticipantBlock<EnergyProviderTile> {
    public EnergyProviderBlock(Material materialIn) {
        super(materialIn);
        this.setRegistryName("energyprovider");
        this.setUnlocalizedName("energyprovider");
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
