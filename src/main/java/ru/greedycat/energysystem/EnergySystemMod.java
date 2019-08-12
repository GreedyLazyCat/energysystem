package ru.greedycat.energysystem;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.greedycat.energysystem.proxy.CommonProxy;

@Mod(modid = EnergySystemMod.MODID, name = EnergySystemMod.NAME, version = EnergySystemMod.VERSION)
public class EnergySystemMod {
    public static final String MODID = "energysystem";
    public static final String NAME = "Energy System Mod";
    public static final String VERSION = "1.0";

    @Mod.Instance(MODID)
    public static EnergySystemMod INSTANCE;

    @SidedProxy(clientSide = "ru.greedycat.energysystem.proxy.ClientProxy", serverSide = "ru.greedycat.energysystem.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
