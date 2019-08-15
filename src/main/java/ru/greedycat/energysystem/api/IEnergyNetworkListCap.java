package ru.greedycat.energysystem.api;

import ru.greedycat.energysystem.capabilities.EnergyNetwork;

import java.util.HashMap;
import java.util.Set;

public interface IEnergyNetworkListCap {
    HashMap<Integer, EnergyNetwork> networks = new HashMap<>();

    EnergyNetwork getNetwork(int id);

    Set<Integer> getIds();

    int getNextId();

    void removeNetwork(int id);

    int addNetwork(EnergyNetwork network);

    void setNetworks(HashMap<Integer, EnergyNetwork> networks);

    HashMap<Integer, EnergyNetwork> getNetworks();
}
