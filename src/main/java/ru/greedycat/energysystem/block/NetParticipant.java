package ru.greedycat.energysystem.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;

public class NetParticipant extends Block {

    public static final PropertyInteger NET_ID = PropertyInteger.create("net_id", 0, 2147483647);

    public NetParticipant(Material materialIn) {
        super(materialIn);
        this.setDefaultState(this.blockState.getBaseState().withProperty(NET_ID, 0));
    }
}
