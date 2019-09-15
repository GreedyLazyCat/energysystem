package ru.greedycat.energysystem.util;

import net.minecraft.util.math.BlockPos;

import java.util.Comparator;

public class BlockPosComparator implements Comparator<BlockPos> {

    private BlockPos goal;

    public BlockPosComparator(BlockPos goal){
        this.goal = goal;
    }

    @Override
    public int compare(BlockPos o1, BlockPos o2) {
        int first = EnergyNetworkUtil.minManhDistance(o1, goal);
        int second =  EnergyNetworkUtil.minManhDistance(o2, goal);
        return first - second;
    }
}
