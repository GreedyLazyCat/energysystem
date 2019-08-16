package ru.greedycat.energysystem.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.greedycat.energysystem.api.EnumParticipantType;
import ru.greedycat.energysystem.tile.util.NetParticipant;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnergyNetworkUtil {

    public static void onNetParticipantPlaced(World world, NetParticipant participant, BlockPos pos){
        EnumParticipantType type = participant.getTYPE();
        switch (type){
            case PROVIDER:
                handleProvider(world, participant, pos);
                break;
            case RECEIVER:
                handleReceiver(world, participant, pos);
                break;
            case HANDLER:
                handleHandler(world, participant, pos);
                break;
            case WIRE:
                handleWire(world, participant, pos);
                break;
        }
    }

    public static void handleProvider(World world, NetParticipant participant, BlockPos pos){

    }

    public static void handleReceiver(World world, NetParticipant participant, BlockPos pos){

    }

    public static void handleHandler(World world, NetParticipant participant, BlockPos pos){

    }

    public static void handleWire(World world, NetParticipant participant, BlockPos pos){

        HashMap<EnumFacing, NetParticipant> withoutId = new HashMap<>();
        HashMap<Integer, Map.Entry<EnumFacing, NetParticipant>> withId = new HashMap<>();

        boolean main_has_network = false;

        for (EnumFacing facing : EnumFacing.values()){
            BlockPos child = pos.offset(facing);
            TileEntity tile = world.getTileEntity(child);

            if(tile != null && tile instanceof NetParticipant){
                NetParticipant child_participant = (NetParticipant) tile;

                if(child_participant.canConnectFromSide(facing)){
                    if(child_participant.hasNetwork()){
                        withId.put(child_participant.getNetworkId(), new AbstractMap.SimpleEntry<>(facing, child_participant));
                    }
                    else{
                        withoutId.put(facing, child_participant);
                    }

                }
            }
        }

        if(withId.size() > 1){

        }
        else if (withId.size() == 1){
            int id = withId.entrySet().iterator().next().getKey();
            participant.setNetworkId(id);
            main_has_network = true;
        }

        if(main_has_network && withoutId.size() > 0){

        }
        else if(withoutId.size() > 0){

        }
    }

}
