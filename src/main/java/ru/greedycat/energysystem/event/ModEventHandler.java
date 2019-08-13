package ru.greedycat.energysystem.event;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.greedycat.energysystem.EnergySystemMod;
import ru.greedycat.energysystem.tile.EnergyProviderTile;

@Mod.EventBusSubscriber(modid = EnergySystemMod.MODID)
public class ModEventHandler {

    private static final ResourceLocation PROVIDER_CAP = new ResourceLocation(EnergySystemMod.MODID, "EnergyProvider");

    @SubscribeEvent
    public static void attachCapabilityToTile(AttachCapabilitiesEvent<TileEntity> event){
        if(event.getObject() instanceof EnergyProviderTile){
            event.addCapability(PROVIDER_CAP, event.getObject());
        }
    }

}
