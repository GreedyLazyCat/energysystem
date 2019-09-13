package ru.greedycat.energysystem.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.greedycat.energysystem.api.EnumParticipantType;
import ru.greedycat.energysystem.api.IEnergyNetworkListCap;
import ru.greedycat.energysystem.capabilities.EnergyNetwork;
import ru.greedycat.energysystem.capabilities.EnergyNetworkListCap;
import ru.greedycat.energysystem.tile.util.NetParticipant;

import java.util.*;

public class EnergyNetworkUtil {
    /*
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
    }*/

    //public static void handleProvider(World world, NetParticipant start_part, BlockPos pos){ }

    //public static void handleReceiver(World world, NetParticipant start_part, BlockPos pos){ }

    //public static void handleHandler(World world, NetParticipant start_part, BlockPos pos){ }
    //Это бдует вместо onNetParticipantPlaced

    public static void onNetParticipantPlaced(World world, NetParticipant start_part, BlockPos pos){

        HashMap<EnumFacing, NetParticipant> withoutId = new HashMap<>(); // Список участников без id
        HashMap<Integer, Map.Entry<EnumFacing, NetParticipant>> withId = new HashMap<>();// Список участников с id

        boolean main_has_network = false; // есть ли у участника выполняющего проверку сеть

        //проверяем блоки во всех направлениях
        for (EnumFacing facing : EnumFacing.values()){
            //просто получаем tile
            BlockPos child = pos.offset(facing);
            TileEntity tile = world.getTileEntity(child);

            //здесь важно проверить, что к стартовому блоку тоже можно подключиться
            if(start_part.canConnectFromSide(facing.getOpposite()) && tile != null && tile instanceof NetParticipant){
                NetParticipant child_participant = (NetParticipant) tile;

                //сохраняем тип стартового блока и дочернего
                EnumParticipantType type = start_part.getTypeFromSide(facing.getOpposite());
                EnumParticipantType child_type = child_participant.getTypeFromSide(facing);

                //если стартовый не receiver и дочерний блок receiver - добавляем в соответствующий список с id или без
                if(!type.eq(EnumParticipantType.RECEIVER) && child_type.eq(EnumParticipantType.RECEIVER)){
                    if(child_participant.hasNetwork())
                        withId.put(child_participant.getNetworkId(), new AbstractMap.SimpleEntry<>(facing, child_participant));
                    else
                        withoutId.put(facing, child_participant);
                }
                //анлогично и для provider
                if(!type.eq(EnumParticipantType.PROVIDER) && child_type.eq(EnumParticipantType.PROVIDER)){
                    if(child_participant.hasNetwork())
                        withId.put(child_participant.getNetworkId(), new AbstractMap.SimpleEntry<>(facing, child_participant));
                    else
                        withoutId.put(facing, child_participant);
                }
            }
        }

        //если участников с id нашлось больше одного
        if(withId.size() > 1){
            //создаем сеть, которую набьем участиниками сетей найденных блоков тем самым объеденив их
            EnergyNetwork network = new EnergyNetwork(new ArrayList<>());
            IEnergyNetworkListCap list = getEnergyNetworkList(world);

            //добавляем пока пустую сеть в главный список и сохраняем ее id 
            int net_id = list.addNetwork(network);

            for(Map.Entry<Integer, Map.Entry<EnumFacing, NetParticipant>> entry : withId.entrySet()){
                //добавление стартового участника в сеть если он receiver
                if(!main_has_network){
                    start_part.hasNetwork(true);
                    start_part.setNetworkId(net_id);
                    main_has_network = true;

                    if(start_part.getTypeFromSide(entry.getValue().getKey().getOpposite()) == EnumParticipantType.RECEIVER){
                        EnergyNetwork net = list.getNetwork(start_part.getNetworkId());
                        if(net != null){
                            net.addReceiver(start_part.getPos());
                        }
                    }
                }

                EnergyNetwork sub_net = list.getNetwork(entry.getKey());

                network.addReceivers(sub_net.getReceivers());

                list.removeNetwork(entry.getKey());

                setId(world, entry.getValue().getValue().getPos(), entry.getValue().getKey(), net_id, false);
            }
        }
        else if (withId.size() == 1){
            Map.Entry<Integer, Map.Entry<EnumFacing, NetParticipant>> entry = withId.entrySet().iterator().next();
            int id = entry.getKey();
            start_part.setNetworkId(id);
            start_part.hasNetwork(true);
            main_has_network = true;

            EnumFacing facing = entry.getValue().getKey();

            //добавление стартового участника в сеть если он receiver
            if(start_part.getTypeFromSide(facing.getOpposite()) == EnumParticipantType.RECEIVER){
                IEnergyNetworkListCap list = getEnergyNetworkList(world);
                EnergyNetwork net = list.getNetwork(start_part.getNetworkId());
                if(net != null){
                    net.addReceiver(start_part.getPos());
                }
            }
        }

        if(main_has_network && withoutId.size() > 0){
            int net_id = start_part.getNetworkId();

            for(Map.Entry<EnumFacing, NetParticipant> entry : withoutId.entrySet()){
                NetParticipant part = entry.getValue();

                if(part.getTYPE() == EnumParticipantType.WIRE){

                }
                if(part.getTypeFromSide(entry.getKey()) == EnumParticipantType.RECEIVER){
                    if(world.hasCapability(EnergyNetworkListCap.ENERGY_NETWORK_LIST, null)){
                        IEnergyNetworkListCap net_list = world.getCapability(EnergyNetworkListCap.ENERGY_NETWORK_LIST, null);
                        EnergyNetwork network = net_list.getNetwork(net_id);

                        network.getReceivers().add(entry.getValue().getPos());

                        setId(world, part.getPos(), entry.getKey(), net_id,true);
                        part.setNetworkId(net_id);
                        part.hasNetwork(true);
                    }
                }
                if(part.getTypeFromSide(entry.getKey()) == EnumParticipantType.PROVIDER){
                    setId(world, part.getPos(), entry.getKey(), net_id,true);
                    part.setNetworkId(net_id);
                    part.hasNetwork(true);
                }/*
                if(part.getTYPE() == EnumParticipantType.HANDLER){
                    if(part.getTypeFromSide(entry.getKey()) == EnumParticipantType.RECEIVER){
                        if(world.hasCapability(EnergyNetworkListCap.ENERGY_NETWORK_LIST, null)) {
                            IEnergyNetworkListCap net_list = world.getCapability(EnergyNetworkListCap.ENERGY_NETWORK_LIST, null);
                            EnergyNetwork network = net_list.getNetwork(net_id);

                            network.getReceivers().add(part.getPos());
                            if (part.hasCapability(EnergyHandlerCap.ENERGY_HANDLER, null))
                                part.getCapability(EnergyHandlerCap.ENERGY_HANDLER, null).setConnectedAsReceiver(true);
                        }
                    }

                    entry.getValue().setNetworkId(net_id);
                    entry.getValue().hasNetwork(true);
                }*/
            }
        }
        else if(withoutId.size() > 0){
            for(Map.Entry<EnumFacing, NetParticipant> entry : withoutId.entrySet()){
                NetParticipant part = entry.getValue();

                if(main_has_network){
                    if(part.getTypeFromSide(entry.getKey()) == EnumParticipantType.RECEIVER){
                        IEnergyNetworkListCap list = getEnergyNetworkList(world);
                        EnergyNetwork net = list.getNetwork(start_part.getNetworkId());
                        if(net != null){
                            net.addReceiver(part.getPos());
                        }
                    }

                    setId(world, part.getPos(), entry.getKey(), start_part.getNetworkId(),true);
                }
                else if(part.getTypeFromSide(entry.getKey()) == EnumParticipantType.PROVIDER){
                    IEnergyNetworkListCap list = getEnergyNetworkList(world);
                    EnergyNetwork network = new EnergyNetwork(new ArrayList<>());
                    int net_id = list.addNetwork(network);

                    setId(world, part.getPos(), entry.getKey(), net_id,true);
                }

            }
        }
    }

    /**
     *
     * @param world Мир
     * @param start Стартовая позиция
     * @param start_facing Стартовое направление
     * @param id id сети, который нужно выставить
     * @param add_to_network нужно ли добавлять участников в сеть
     */
    public static void setId(World world, BlockPos start, EnumFacing start_facing, int id, boolean add_to_network){
        HashSet<BlockPos> checked = new HashSet<>(); //список проверенных блоков
        ArrayDeque<AbstractMap.SimpleEntry<EnumFacing, BlockPos>> queue = new ArrayDeque<>(100);//Очередь, это особенность реализации алгоритма поиска в ширину.
        IEnergyNetworkListCap list = getEnergyNetworkList(world);

        queue.offer(new AbstractMap.SimpleEntry<>(start_facing, start));
        checked.add(start);
        while (!queue.isEmpty()) {
            Map.Entry entry = queue.poll();
            BlockPos nPos = (BlockPos) entry.getValue();

            TileEntity tile = world.getTileEntity(nPos);
            if(tile != null && tile instanceof NetParticipant){
                NetParticipant participant = (NetParticipant) tile;
                EnumParticipantType type = participant.getTypeFromSide((EnumFacing) entry.getKey());

                if(list != null){
                    if(type.eq(EnumParticipantType.PROVIDER)){
                        participant.setNetworkId(id);
                        participant.hasNetwork(true);
                    }
                    if(type.eq(EnumParticipantType.RECEIVER)){
                        if(add_to_network && world.hasCapability(EnergyNetworkListCap.ENERGY_NETWORK_LIST, null)) {
                            IEnergyNetworkListCap net_list = world.getCapability(EnergyNetworkListCap.ENERGY_NETWORK_LIST, null);
                            EnergyNetwork network = net_list.getNetwork(id);

                            network.getReceivers().add(participant.getPos());
                        }

                        participant.setNetworkId(id);
                        participant.hasNetwork(true);
                    }
                }

                for (EnumFacing face : EnumFacing.VALUES) {
                    BlockPos child = nPos.offset(face);//эта функция возвращает позицию со сдвигом в данном направлении
                    TileEntity child_tile = world.getTileEntity(child);

                    if(child_tile != null && child_tile instanceof NetParticipant){
                        NetParticipant child_participant = (NetParticipant) child_tile;

                        if(!checked.contains(child) && child_participant.canConnectFromSide(face)){
                            EnumParticipantType child_type = child_participant.getTypeFromSide(face);

                            if(!type.eq(EnumParticipantType.RECEIVER) && !type.eq(EnumParticipantType.PROVIDER) && child_type.eq(EnumParticipantType.WIRE)){
                                checked.add(child);
                                queue.addLast(new AbstractMap.SimpleEntry<>(face, child));
                            }
                            if(!type.eq(EnumParticipantType.RECEIVER) && child_type.eq(EnumParticipantType.RECEIVER)){
                                checked.add(child);
                                queue.addLast(new AbstractMap.SimpleEntry<>(face, child));
                            }
                            if(!type.eq(EnumParticipantType.PROVIDER) && child_type.eq(EnumParticipantType.PROVIDER)){
                                checked.add(child);
                                queue.addLast(new AbstractMap.SimpleEntry<>(face, child));
                            }
                        }
                    }
                }
            }
        }
    }

    public static IEnergyNetworkListCap getEnergyNetworkList(World world) {
        if(world.hasCapability(EnergyNetworkListCap.ENERGY_NETWORK_LIST, null)) {
            IEnergyNetworkListCap list = world.getCapability(EnergyNetworkListCap.ENERGY_NETWORK_LIST, null);
            return list;
        }
        return null;
    }
}
