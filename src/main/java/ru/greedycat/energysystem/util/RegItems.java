package ru.greedycat.energysystem.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import ru.greedycat.energysystem.item.DebugItem;

public class RegItems {

    public static DebugItem debugItem;

    public static void register() {

        debugItem = new DebugItem();

        registerItems(debugItem);

    }
    public static void registerRender() {
    }
    private static void registerItems(Item item) {
        ForgeRegistries.ITEMS.register(item);
    }
    private static void registerItemsRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
