package com.teamsimplyrs.prismaarcanum.registry;

import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import com.teamsimplyrs.prismaarcanum.block.SpellNexus;
import com.teamsimplyrs.prismaarcanum.block.entity.SpellNexusBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PABlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PrismaArcanum.MODID);

    public static final RegistryObject<BlockEntityType<SpellNexusBlockEntity>> SPELL_NEXUS_BE =
            BLOCK_ENTITIES.register(SpellNexusBlockEntity.BE_NAME,
                    () -> BlockEntityType.Builder.of(SpellNexusBlockEntity::new, PABlockRegistry.SPELL_NEXUS.get())
                            .build(null));
    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}
