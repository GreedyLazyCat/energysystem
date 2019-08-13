package ru.greedycat.energysystem.api;


public enum EnumParticipantType {
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
    WIRE;

    public static EnumParticipantType getTypeByString(String name){
        EnumParticipantType[] values = EnumParticipantType.values();
        for (EnumParticipantType type : values) {
            if (type.name().toLowerCase() == name.toLowerCase())
                return type;
        }
        return null;
    }
}
