package ru.greedycat.energysystem.api;

public interface IEnergyProviderCap {

    void setOutput(int output);

    int getOutput();

    /**
     * Removes energy from the storage. Returns quantity of energy that was removed.
     *
     * @param maxExtract
     *            Maximum amount of energy to be extracted.
     * @param simulate
     *            If TRUE, the extraction will only be simulated.
     * @return Amount of energy that was (or would have been, if simulated) extracted from the storage.
     */
    int extractEnergy(int maxExtract, boolean simulate);

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

    void onChanges();
}
