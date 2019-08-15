package ru.greedycat.energysystem.tile;

import ru.greedycat.energysystem.api.EnumParticipantType;
import ru.greedycat.energysystem.tile.util.NetParticipant;

public class EnergyWireTile extends NetParticipant {

    @Override
    public void onLoad() {
        super.onLoad();
        this.TYPE = EnumParticipantType.WIRE;
    }
}
