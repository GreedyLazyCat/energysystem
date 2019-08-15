package ru.greedycat.energysystem.api;

public interface IEnergyHandlerCap  extends IEnergyReceiverCap{

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

    boolean getConnectedAsReceiver();

    void setConnectedAsReceiver(boolean bool);
}
