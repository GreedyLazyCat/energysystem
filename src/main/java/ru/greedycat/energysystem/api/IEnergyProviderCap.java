package ru.greedycat.energysystem.api;

public interface IEnergyProviderCap {

    void setOutput(int output);

    int getOutput();

    /**
     * Returns the maximum amount of energy that can be stored.
     */
    int getMaxEnergyStored();

    /**
     * Returns the amount of energy currently stored.
     */
    int getEnergyStored();

    /**
     *  Sets the current amount of energy. Mostly used in capabilities after loading from NBT
     * @param energy
     */
    void setEnergyStored(int energy);

    /**
     *  Sets the maximum amount of energy. Mostly used in capabilities after loading from NBT
     * @param energy
     */
    void setMaxEnergyStored(int energy);
}
