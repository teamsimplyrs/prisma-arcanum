package com.teamsimplyrs.prismaarcanum.block.voxelshapes;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class PAVoxelShapes {
    public static final VoxelShape SPELL_NEXUS_SHAPE = Stream.of(
            Block.box(1, 0, 1, 15, 6, 15),
            Block.box(3, 6, 3, 13, 8, 13),
            Block.box(6, 8, 6, 10, 9.5, 10),
            Block.box(2, 9.5, 2, 14, 10, 14),
            Block.box(1.75, 10, 2, 2, 11, 14),
            Block.box(14, 10, 2, 14.25, 11, 14),
            Block.box(1.75, 10, 1.75, 14.25, 11, 2),
            Block.box(1.75, 10, 14, 14.25, 11, 14.25)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
}
