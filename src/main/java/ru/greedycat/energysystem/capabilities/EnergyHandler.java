package ru.greedycat.energysystem.capabilities;

import ru.greedycat.energysystem.api.IEnergyHandlerCap;

public class EnergyHandler implements IEnergyHandlerCap {

    protected int energy;
    protected int max_energy_stored;

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        // TODO Auto-generated method stub
        int energyReceived = Math.min(max_energy_stored - energy,maxReceive);

        if (!simulate) {
            energy += energyReceived;
        }
        onChanges();
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int energyExtracted = Math.min(energy,  maxExtract);

        if (!simulate) {
            energy -= energyExtracted;
        }
        onChanges();
        return energyExtracted;
    }

    @Override
    public int getMaxEnergyStored() {
        return max_energy_stored;
    }

    @Override
    public int getEnergyStored() {
        return energy;
    }

    @Override
    public void setEnergyStored(int energy) {
        this.energy = energy;
    }

    @Override
    public void setMaxEnergyStored(int energy) {
        this.max_energy_stored = energy;
    }

    @Override
    public void onChanges() {

    }
}
