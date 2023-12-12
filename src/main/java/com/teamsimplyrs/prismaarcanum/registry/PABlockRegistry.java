package com.teamsimplyrs.prismaarcanum.registry;

import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PABlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PrismaArcanum.MODID);



    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
