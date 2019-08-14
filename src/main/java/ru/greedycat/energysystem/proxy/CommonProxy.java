package ru.greedycat.energysystem.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.greedycat.energysystem.capabilities.EnergyHandlerCap;
import ru.greedycat.energysystem.capabilities.EnergyProviderCap;
import ru.greedycat.energysystem.capabilities.EnergyReceiverCap;
import ru.greedycat.energysystem.util.RegBlocks;
import ru.greedycat.energysystem.util.RegItems;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event)
    {
        RegBlocks.register();
        RegItems.register();

        EnergyHandlerCap.register();
        EnergyProviderCap.register();
        EnergyReceiverCap.register();
    }

    public void init(FMLInitializationEvent event)
    {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }

}
