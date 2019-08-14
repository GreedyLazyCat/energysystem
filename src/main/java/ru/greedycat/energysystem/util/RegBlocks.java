package ru.greedycat.energysystem.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.greedycat.energysystem.block.EnergyHandlerBlock;
import ru.greedycat.energysystem.block.EnergyProviderBlock;
import ru.greedycat.energysystem.block.EnergyReceiverBlock;
import ru.greedycat.energysystem.tile.EnergyHandlerTile;
import ru.greedycat.energysystem.tile.EnergyProviderTile;
import ru.greedycat.energysystem.tile.EnergyReceiverTile;

public class RegBlocks {

    public static EnergyProviderBlock energyProviderBlock;
    public static EnergyReceiverBlock energyReceiverBlock;
    public static EnergyHandlerBlock energyHandlerBlock;

    public static void register() {
        energyProviderBlock = new EnergyProviderBlock(Material.ANVIL);
        energyReceiverBlock = new EnergyReceiverBlock(Material.ANVIL);
        energyHandlerBlock = new EnergyHandlerBlock(Material.ANVIL);

        registerBlock(energyProviderBlock);
        registerBlock(energyReceiverBlock);
        registerBlock(energyHandlerBlock);

        registerTile(energyProviderBlock, EnergyProviderTile.class);
        registerTile(energyReceiverBlock, EnergyReceiverTile.class);
        registerTile(energyHandlerBlock, EnergyHandlerTile.class);
    }

    @SuppressWarnings("deprecation")
    public static void registerTile(Block block, Class tile ) {
        GameRegistry.registerTileEntity(tile, block.getRegistryName().toString());
    }

    public static void registerRender() {
    }
    public static void registerBlock(Block block) {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }
    public static void registerBlockRender(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}
