package ru.greedycat.energysystem.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import ru.greedycat.energysystem.api.EnumParticipantType;
import ru.greedycat.energysystem.capabilities.EnergyReceiver;
import ru.greedycat.energysystem.capabilities.EnergyReceiverCap;
import ru.greedycat.energysystem.tile.util.NetParticipant;

import javax.annotation.Nullable;

public class EnergyReceiverTile extends NetParticipant {

    private EnergyReceiver receiver = new EnergyReceiver(){
        @Override
        public void onChanges() {
            markDirty();
        }
    };

    @Override
    public void onLoad() {
        super.onLoad();
        this.TYPE = EnumParticipantType.RECEIVER;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == EnergyReceiverCap.ENERGY_RECEIVER;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return (capability == EnergyReceiverCap.ENERGY_RECEIVER)? (T) this.receiver : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound) EnergyReceiverCap.ENERGY_RECEIVER.getStorage().writeNBT(EnergyReceiverCap.ENERGY_RECEIVER, this.receiver, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        super.deserializeNBT(nbt);
        EnergyReceiverCap.ENERGY_RECEIVER.getStorage().readNBT(EnergyReceiverCap.ENERGY_RECEIVER, this.receiver, null, nbt);
    }
}
