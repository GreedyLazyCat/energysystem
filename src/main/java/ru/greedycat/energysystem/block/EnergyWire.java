package ru.greedycat.energysystem.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import ru.greedycat.energysystem.block.util.NetParticipantBlock;
import ru.greedycat.energysystem.tile.EnergyWireTile;

import javax.annotation.Nullable;

public class EnergyWire extends NetParticipantBlock<EnergyWireTile> {
    public EnergyWire(Material materialIn) {
        super(materialIn);
        this.setRegistryName("energywire");
        this.setUnlocalizedName("energywire");
    }

    @Override
    public Class<EnergyWireTile> getTileEntityClass() {
        return EnergyWireTile.class;
    }

    @Nullable
    @Override
    public EnergyWireTile createTileEntity(World world, IBlockState blockState) {
        return new EnergyWireTile();
    }
}
