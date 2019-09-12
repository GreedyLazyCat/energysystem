package ru.greedycat.energysystem.capabilities;

import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class EnergyNetwork extends ArrayList<BlockPos> {
    public ArrayList<BlockPos> receivers;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnergyNetwork(ArrayList<BlockPos> receivers) {
        this.receivers = receivers;
    }
    //add
    public void addReceiver(BlockPos pos){
        receivers.add(pos);
    }

    public void addReceivers(ArrayList<BlockPos> receivers){
        for(BlockPos pos : receivers){
            this.receivers.add(pos);
        }
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
