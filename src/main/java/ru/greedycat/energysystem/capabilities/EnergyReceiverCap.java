package ru.greedycat.energysystem.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import ru.greedycat.energysystem.api.IEnergyReceiverCap;

import javax.annotation.Nullable;

public class EnergyReceiverCap {
    @CapabilityInject(IEnergyReceiverCap.class)
    public static Capability<IEnergyReceiverCap> ENERGY_RECEIVER = null;
    public static void register(){
        CapabilityManager.INSTANCE.register(IEnergyReceiverCap.class, new Capability.IStorage<IEnergyReceiverCap>() {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<IEnergyReceiverCap> capability, IEnergyReceiverCap instance, EnumFacing side) {
                NBTTagCompound nbtTagList = new NBTTagCompound();

                nbtTagList.setInteger("energy", instance.getEnergyStored());
                nbtTagList.setInteger("max_energy_stored", instance.getMaxEnergyStored());

                return nbtTagList;
            }

            @Override
            public void readNBT(Capability<IEnergyReceiverCap> capability, IEnergyReceiverCap instance, EnumFacing side, NBTBase nbt) {
                NBTTagCompound nbtTagCompound = (NBTTagCompound) nbt;
                instance.setEnergyStored(nbtTagCompound.getInteger("energy"));
                instance.setMaxEnergyStored(nbtTagCompound.getInteger("max_energy_stored"));
            }
        }, EnergyReceiver::new);
    }
}
