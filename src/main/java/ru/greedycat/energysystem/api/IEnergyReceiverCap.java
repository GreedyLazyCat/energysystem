package ru.greedycat.energysystem.api;

public interface IEnergyReceiverCap {

    int receiveEnergy(int maxReceive, boolean simulate);

    /**
     * Returns the amount of energy currently stored.
     */
    int getEnergyStored();

    /**
     * Returns the maximum amount of energy that can be stored.
     */
    int getMaxEnergyStored();


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
