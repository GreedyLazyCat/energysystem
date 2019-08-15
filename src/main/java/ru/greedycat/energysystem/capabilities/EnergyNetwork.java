package ru.greedycat.energysystem.capabilities;

import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class EnergyNetwork {
    public ArrayList<BlockPos> receivers;

    public EnergyNetwork(ArrayList<BlockPos> receivers) {
        this.receivers = receivers;
    }
    //add
    public void addReceiver(BlockPos pos){
        receivers.add(pos);
    }
    //removers
    public void removeReceiver(int index){
        receivers.remove(index);
    }
    //getters

    public ArrayList<BlockPos> getReceivers() {
        return receivers;
    }
    //setter
    public void setReceivers(ArrayList<BlockPos> receivers) {
        this.receivers = receivers;
    }
}
