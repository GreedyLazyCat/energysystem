package ru.greedycat.energysystem.util;

import net.minecraft.util.math.BlockPos;

import java.util.Comparator;

public class BlockPosComparator implements Comparator<BlockPos> {

    private BlockPos goal;
    private BlockPos start;

    public BlockPosComparator(BlockPos goal, BlockPos start){
        this.goal = goal;
        this.start = start;
    }

    @Override
    public int compare(BlockPos o1, BlockPos o2) {
        double f1 = o1.getDistance(goal.getX(), goal.getY(), goal.getZ()) + EnergyNetworkUtil.minManhDistance(o1, start);
        double f2 = o2.getDistance(goal.getX(), goal.getY(), goal.getZ()) + EnergyNetworkUtil.minManhDistance(o2, start);
        return Double.compare(f1, f2);
    }
}
