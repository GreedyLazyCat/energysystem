package ru.greedycat.energysystem.event;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.greedycat.energysystem.EnergySystemMod;

@Mod.EventBusSubscriber(modid = EnergySystemMod.MODID)
public class ModEventHandler {

    @SubscribeEvent
    public static void attachCapabilityToTile(AttachCapabilitiesEvent<TileEntity> event){

    }

}
