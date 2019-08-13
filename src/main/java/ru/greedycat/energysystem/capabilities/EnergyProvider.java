package ru.greedycat.energysystem.capabilities;

import ru.greedycat.energysystem.api.IEnergyProviderCap;

public class EnergyProvider implements IEnergyProviderCap{

    public int energy;
    protected int max_energy_stored;

    public EnergyProvider(){
        this.max_energy_stored = 1000;
        this.energy = 10;
    }

    @Override
    public void setOutput(int output) {

    }

    @Override
    public int getOutput() {
        return 0;
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
