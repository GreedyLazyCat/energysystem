package ru.greedycat.energysystem.api;

public interface IEnergyReceiverCap {
    /**
     * Adds energy to the storage. Returns quantity of energy that was accepted.
     *
     * @param maxReceive
     *            Maximum amount of energy to be inserted.
     * @param simulate
     *            If TRUE, the insertion will only be simulated.
     * @return Amount of energy that was (or would have been, if simulated) accepted by the storage.
     */
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

    void onChanges();
}
