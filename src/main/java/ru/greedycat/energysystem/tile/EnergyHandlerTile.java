package ru.greedycat.energysystem.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import ru.greedycat.energysystem.api.EnumParticipantType;
import ru.greedycat.energysystem.capabilities.EnergyHandler;
import ru.greedycat.energysystem.capabilities.EnergyHandlerCap;
import ru.greedycat.energysystem.tile.util.NetParticipant;

import javax.annotation.Nullable;

public class EnergyHandlerTile extends NetParticipant {

    private EnergyHandler handler = new EnergyHandler(){
        @Override
        public void onChanges() {
            markDirty();
        }
    };

    @Override
    public void onLoad() {
        super.onLoad();
        this.TYPE = EnumParticipantType.HANDLER;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == EnergyHandlerCap.ENERGY_HANDLER;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return (capability == EnergyHandlerCap.ENERGY_HANDLER)? (T) this.handler : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound) EnergyHandlerCap.ENERGY_HANDLER.getStorage().writeNBT(EnergyHandlerCap.ENERGY_HANDLER, this.handler, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        EnergyHandlerCap.ENERGY_HANDLER.getStorage().readNBT(EnergyHandlerCap.ENERGY_HANDLER, this.handler, null, nbt);
    }

}
