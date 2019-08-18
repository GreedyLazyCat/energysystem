package ru.greedycat.energysystem.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import ru.greedycat.energysystem.api.EnumParticipantType;
import ru.greedycat.energysystem.capabilities.EnergyHandler;
import ru.greedycat.energysystem.capabilities.EnergyHandlerCap;
import ru.greedycat.energysystem.tile.util.NetParticipant;
import scala.actors.threadpool.Arrays;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.HashSet;

public class EnergyHandlerTile extends NetParticipant {

    private EnergyHandler handler = new EnergyHandler(){
        @Override
        public void onChanges() {
            markDirty();
        }
    };

    private HashSet<EnumFacing> receiver_sides;
    private HashSet<EnumFacing> provider_sides;

    @Override
    public void onLoad() {
        super.onLoad();
        this.TYPE = EnumParticipantType.HANDLER;
        this.connections = new HashSet<>(Arrays.asList(new EnumFacing[]{EnumFacing.EAST, EnumFacing.NORTH}));
        this.provider_sides =  new HashSet<>(Arrays.asList(new EnumFacing[]{EnumFacing.EAST}));
        this.receiver_sides = new HashSet<>(Arrays.asList(new EnumFacing[]{EnumFacing.NORTH}));
    }

    @Override
    public EnumParticipantType getTypeFromSide(EnumFacing facing) {
        if(provider_sides.contains(facing)){
            return EnumParticipantType.PROVIDER;
        }
        if(receiver_sides.contains(facing)){
            return EnumParticipantType.RECEIVER;
        }
        return super.getTypeFromSide(facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == EnergyHandlerCap.ENERGY_HANDLER;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return (capability == EnergyHandlerCap.ENERGY_HANDLER)? (T) this.handler : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound) EnergyHandlerCap.ENERGY_HANDLER.getStorage().writeNBT(EnergyHandlerCap.ENERGY_HANDLER, this.handler, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        EnergyHandlerCap.ENERGY_HANDLER.getStorage().readNBT(EnergyHandlerCap.ENERGY_HANDLER, this.handler, null, nbt);
    }

}
