package ru.greedycat.energysystem.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.greedycat.energysystem.block.EnergyProviderBlock;
import ru.greedycat.energysystem.tile.EnergyProviderTile;

public class RegBlocks {

    public static EnergyProviderBlock energyProviderBlock;

    public static void register() {
        energyProviderBlock = new EnergyProviderBlock(Material.ANVIL);

        registerBlock(energyProviderBlock);

        registerTile(energyProviderBlock, EnergyProviderTile.class);
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
