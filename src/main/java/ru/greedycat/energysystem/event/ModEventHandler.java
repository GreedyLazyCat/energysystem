package ru.greedycat.energysystem.event;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.greedycat.energysystem.EnergySystemMod;
import ru.greedycat.energysystem.capabilities.EnergyReceiver;
import ru.greedycat.energysystem.tile.EnergyHandlerTile;
import ru.greedycat.energysystem.tile.EnergyProviderTile;
import ru.greedycat.energysystem.tile.EnergyReceiverTile;

@Mod.EventBusSubscriber(modid = EnergySystemMod.MODID)
public class ModEventHandler {

    private static final ResourceLocation PROVIDER_CAP = new ResourceLocation(EnergySystemMod.MODID, "EnergyProvider");
    private static final ResourceLocation RECEIVER_CAP = new ResourceLocation(EnergySystemMod.MODID, "EnergyReceiver");
    private static final ResourceLocation HANDLER_CAP = new ResourceLocation(EnergySystemMod.MODID, "EnergyHandler");

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

}
