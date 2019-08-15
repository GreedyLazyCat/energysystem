package ru.greedycat.energysystem.event;

import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.greedycat.energysystem.EnergySystemMod;
import ru.greedycat.energysystem.capabilities.EnergyNetworkList;
import ru.greedycat.energysystem.capabilities.EnergyNetworkListCap;
import ru.greedycat.energysystem.capabilities.EnergyReceiver;
import ru.greedycat.energysystem.tile.EnergyHandlerTile;
import ru.greedycat.energysystem.tile.EnergyProviderTile;
import ru.greedycat.energysystem.tile.EnergyReceiverTile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = EnergySystemMod.MODID)
public class ModEventHandler {

    private static final ResourceLocation PROVIDER_CAP = new ResourceLocation(EnergySystemMod.MODID, "EnergyProvider");
    private static final ResourceLocation RECEIVER_CAP = new ResourceLocation(EnergySystemMod.MODID, "EnergyReceiver");
    private static final ResourceLocation HANDLER_CAP = new ResourceLocation(EnergySystemMod.MODID, "EnergyHandler");
    private static final ResourceLocation WORLD_CAP = new ResourceLocation(EnergySystemMod.MODID, "EnergyNetworkList");

    @SubscribeEvent
    public static void attachCapabilityToTile(AttachCapabilitiesEvent<TileEntity> event){
        if(event.getObject() instanceof EnergyProviderTile){
            event.addCapability(PROVIDER_CAP, event.getObject());
        }
        else if(event.getObject() instanceof EnergyReceiverTile){
            event.addCapability(RECEIVER_CAP, event.getObject());
        }
        else if(event.getObject() instanceof EnergyHandlerTile){
            event.addCapability(HANDLER_CAP, event.getObject());
        }
    }

    @SubscribeEvent
    public static void attachCapabilityToWorld(AttachCapabilitiesEvent<World> event){
        event.addCapability(WORLD_CAP, new ICapabilitySerializable<NBTTagList>() {

            private final EnergyNetworkList energyNetworkList = new EnergyNetworkList();

            @Override
            public NBTTagList serializeNBT() {
                return (NBTTagList) EnergyNetworkListCap.ENERGY_NETWORK_LIST.getStorage()
                        .writeNBT(EnergyNetworkListCap.ENERGY_NETWORK_LIST, energyNetworkList, null);
            }

            @Override
            public void deserializeNBT(NBTTagList nbt) {
                EnergyNetworkListCap.ENERGY_NETWORK_LIST.getStorage()
                        .readNBT(EnergyNetworkListCap.ENERGY_NETWORK_LIST, energyNetworkList, null, nbt);
            }

            @Override
            public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
                return capability == EnergyNetworkListCap.ENERGY_NETWORK_LIST;
            }

            @Nullable
            @Override
            public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
                return (capability == EnergyNetworkListCap.ENERGY_NETWORK_LIST)? (T) this.energyNetworkList : null;
            }
        });
    }

}
