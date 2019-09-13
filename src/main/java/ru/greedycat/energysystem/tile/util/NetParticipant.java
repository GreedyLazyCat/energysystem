package ru.greedycat.energysystem.tile.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import ru.greedycat.energysystem.api.EnumParticipantType;
import ru.greedycat.energysystem.capabilities.EnergyNetworkList;
import scala.actors.threadpool.Arrays;

import java.util.HashSet;

public class NetParticipant extends TileEntity {

    protected EnumParticipantType TYPE;
    protected HashSet<EnumFacing> connections;
    protected int network_id;
    protected boolean has_network;

    @Override
    public void onLoad() {
        TYPE = EnumParticipantType.RECEIVER;
        connections = new HashSet<>(Arrays.asList(EnumFacing.values()));
        System.out.println("onLoad Tile");
        super.onLoad();
    }

    public EnumParticipantType getTYPE() {
        return TYPE;
    }

    public boolean canConnectFromSide(EnumFacing facing){
        return connections.contains(facing);
    }

    public EnumParticipantType getTypeFromSide(EnumFacing facing){
        if(this.canConnectFromSide(facing)) {
            return this.getTYPE();
        }
        return EnumParticipantType.NONE;
    }

    public boolean hasNetwork(){
        return has_network;
    }

    public void hasNetwork(boolean bool){
        has_network = bool;
    }

    public int getNetworkId() {
        return network_id;
    }

    public void setNetworkId(int network_id) {
        this.network_id = network_id;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        compound.setString("ptype", TYPE.name());
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.TYPE = EnumParticipantType.getTypeByString(compound.getString("ptype"));
        super.readFromNBT(compound);
    }
}
