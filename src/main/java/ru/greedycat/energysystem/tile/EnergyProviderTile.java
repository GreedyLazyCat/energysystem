package ru.greedycat.energysystem.tile;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import ru.greedycat.energysystem.api.EnumParticipantType;
import ru.greedycat.energysystem.capabilities.EnergyProvider;
import ru.greedycat.energysystem.capabilities.EnergyProviderCap;
import ru.greedycat.energysystem.tile.util.NetParticipant;

import javax.annotation.Nullable;

public class EnergyProviderTile extends NetParticipant{

    private EnergyProvider provider = new EnergyProvider(){
        @Override
        public void onChanges() {
            markDirty();
        }
    };

    public EnergyProviderTile(){
        super();
        this.TYPE = EnumParticipantType.PROVIDER;
    }

    @Override
    public void onLoad() {
        this.TYPE = EnumParticipantType.PROVIDER;
        super.onLoad();
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == EnergyProviderCap.ENERGY_PROVIDER;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return (capability == EnergyProviderCap.ENERGY_PROVIDER)? (T) this.provider : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound) EnergyProviderCap.ENERGY_PROVIDER.getStorage().writeNBT(EnergyProviderCap.ENERGY_PROVIDER, this.provider,null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        EnergyProviderCap.ENERGY_PROVIDER.getStorage().readNBT(EnergyProviderCap.ENERGY_PROVIDER, this.provider,null, nbt);
    }
}
