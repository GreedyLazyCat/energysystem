package ru.greedycat.energysystem.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import ru.greedycat.energysystem.api.IEnergyHandlerCap;
import ru.greedycat.energysystem.api.IEnergyProviderCap;
import ru.greedycat.energysystem.api.IEnergyReceiverCap;
import ru.greedycat.energysystem.capabilities.EnergyHandlerCap;
import ru.greedycat.energysystem.capabilities.EnergyProviderCap;
import ru.greedycat.energysystem.capabilities.EnergyReceiverCap;
import ru.greedycat.energysystem.tile.util.NetParticipant;

public class DebugItem extends Item{
    public DebugItem(){
        this.setRegistryName("debugitem");
        this.setUnlocalizedName("debugitem");
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        //Быстро склепаный предмет для типо дебага
        if(!worldIn.isRemote){
            System.out.println("work");
            TileEntity tile = worldIn.getTileEntity(pos);
            if(tile != null && tile instanceof NetParticipant){
                NetParticipant participant = (NetParticipant) tile;
                if(participant.hasCapability(EnergyHandlerCap.ENERGY_HANDLER, null)){
                    IEnergyHandlerCap energyHandlerCap = participant.getCapability(EnergyHandlerCap.ENERGY_HANDLER, null);
                    player.sendMessage(new TextComponentString("Energy stored: " + energyHandlerCap.getEnergyStored() + "\n Type: " + participant.getTYPE()));
                }
                else if(participant.hasCapability(EnergyProviderCap.ENERGY_PROVIDER, null)){
                    IEnergyProviderCap energyProviderCap = participant.getCapability(EnergyProviderCap.ENERGY_PROVIDER, null);
                    player.sendMessage(new TextComponentString("Energy stored: " + energyProviderCap.getEnergyStored() + "\n Type: " + participant.getTYPE()));
                }
                else if(participant.hasCapability(EnergyReceiverCap.ENERGY_RECEIVER, null)) {
                    IEnergyReceiverCap energyReceiverCap = participant.getCapability(EnergyReceiverCap.ENERGY_RECEIVER, null);
                    player.sendMessage(new TextComponentString("Energy stored: " + energyReceiverCap.getEnergyStored() + "\n Type: " + participant.getTYPE()));
                }
            }
        }

        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
