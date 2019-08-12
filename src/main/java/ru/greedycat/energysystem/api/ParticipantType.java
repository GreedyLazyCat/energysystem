package ru.greedycat.energysystem.api;


public enum ParticipantType {
    /**
     * Поставляет, но не может получать энергию
     */
    PROVIDER,

    /**
     * Может получать энергию, но не отдвать
     */
    RECEIVER,

    /**
     * Может как получать энергию, так и отдавать
     */
    HANDLER,

    /**
     * Провод
     */
    WIRE
}
