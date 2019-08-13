package ru.greedycat.energysystem.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import ru.greedycat.energysystem.api.EnumParticipantType;
import scala.actors.threadpool.Arrays;

import java.util.HashSet;

public class NetParticipant extends TileEntity {

    protected EnumParticipantType TYPE;
    protected HashSet<EnumFacing> connections;

    public NetParticipant(){
        TYPE = EnumParticipantType.RECEIVER;
        connections = new HashSet<>(Arrays.asList(EnumFacing.values()));
    }

    public EnumParticipantType getTYPE() {
        return TYPE;
    }

    public boolean canConnectFromSide(EnumFacing facing){
        return connections.contains(facing);
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
