package ru.greedycat.energysystem.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.Constants;
import ru.greedycat.energysystem.api.IEnergyNetworkListCap;

import javax.annotation.Nullable;
import java.util.*;

public class EnergyNetworkListCap {
    @CapabilityInject(IEnergyNetworkListCap.class)
    public static Capability<IEnergyNetworkListCap> ENERGY_NETWORK_LIST = null;

    public static void register(){
        CapabilityManager.INSTANCE.register(IEnergyNetworkListCap.class, new Capability.IStorage<IEnergyNetworkListCap>() {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<IEnergyNetworkListCap> capability, IEnergyNetworkListCap instance, EnumFacing side) {

                NBTTagList mainList = new NBTTagList();

                Iterator<Map.Entry<Integer, EnergyNetwork>> iterator = instance.getNetworks().entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer, EnergyNetwork> network = iterator.next();

                    NBTTagList receivers = new NBTTagList();
                    NBTTagCompound compound = new NBTTagCompound();

                    compound.setInteger("id", network.getKey());

                    for(BlockPos pos : network.getValue().getReceivers()){
                        receivers.appendTag(NBTUtil.createPosTag(pos));
                    }

                    compound.setTag("receivers", receivers);

                    mainList.appendTag(compound);
                }

                return mainList;
            }

            @Override
            public void readNBT(Capability<IEnergyNetworkListCap> capability, IEnergyNetworkListCap instance, EnumFacing side, NBTBase nbt) {
                NBTTagList mainList = (NBTTagList) nbt;
                HashMap<Integer, EnergyNetwork> networks = new HashMap<>();

                for (int i = 0; i < mainList.tagCount(); i++) {
                    NBTTagCompound compound = mainList.getCompoundTagAt(i);
                    ArrayList<BlockPos> receivers = new ArrayList();

                    NBTTagList list = compound.getTagList("participants", Constants.NBT.TAG_COMPOUND);
                    for (int j = 0; j < list.tagCount(); j++) {
                        NBTTagCompound tag = list.getCompoundTagAt(j);
                        receivers.add(NBTUtil.getPosFromTag(tag));
                    }

                    EnergyNetwork network = new EnergyNetwork(receivers);

                    networks.put(compound.getInteger("id"), network);
                }
                instance.setNetworks(networks);
            }
        }, EnergyNetworkList::new);
    }
}
