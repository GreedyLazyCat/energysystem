package ru.greedycat.energysystem.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import ru.greedycat.energysystem.api.IEnergyHandlerCap;

import javax.annotation.Nullable;

public class EnergyHandlerCap {
    @CapabilityInject(IEnergyHandlerCap.class)
    public static Capability<IEnergyHandlerCap> ENERGY_HANDLER = null;
    public static void register(){
        CapabilityManager.INSTANCE.register(IEnergyHandlerCap.class, new Capability.IStorage<IEnergyHandlerCap>() {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<IEnergyHandlerCap> capability, IEnergyHandlerCap instance, EnumFacing side) {
                NBTTagCompound nbtTagList = new NBTTagCompound();

                nbtTagList.setInteger("energy", instance.getEnergyStored());
                nbtTagList.setInteger("max_energy_stored", instance.getMaxEnergyStored());
                nbtTagList.setBoolean("connected_as_receiver", instance.getConnectedAsReceiver());

                return nbtTagList;
            }

            @Override
            public void readNBT(Capability<IEnergyHandlerCap> capability, IEnergyHandlerCap instance, EnumFacing side, NBTBase nbt) {
                NBTTagCompound nbtTagCompound = (NBTTagCompound) nbt;
                instance.setEnergyStored(nbtTagCompound.getInteger("energy"));
                instance.setMaxEnergyStored(nbtTagCompound.getInteger("max_energy_stored"));
                instance.setConnectedAsReceiver(nbtTagCompound.getBoolean("connected_as_receiver"));
            }
        }, EnergyHandler::new);
    }
}
