package ru.greedycat.energysystem.capabilities;

import ru.greedycat.energysystem.api.IEnergyNetworkListCap;

import java.util.HashMap;
import java.util.Set;

public class EnergyNetworkList implements IEnergyNetworkListCap {
    public HashMap<Integer, EnergyNetwork> networks = new HashMap<>();

    public EnergyNetwork getNetwork(int id){
        return networks.get(id);
    }

    public Set<Integer> getIds(){
        return networks.keySet();
    }

    public int getNextId() {
        Set<Integer> keys = networks.keySet();
        int j = 0;

        for(Integer i : keys) {
            if(i > j) {
                j = i;
            }
        }
        int res = ++j;
        return res;
    }

    public void removeNetwork(int id){
        networks.remove(id);
    }

    public int addNetwork(EnergyNetwork network){
        int id = getNextId();
        network.setId(id);
        networks.put(id, network);
        return id;
    }

    public void setNetworks(HashMap<Integer, EnergyNetwork> networks) {
        this.networks = networks;
    }

    public HashMap<Integer, EnergyNetwork> getNetworks() {
        return networks;
    }
}
