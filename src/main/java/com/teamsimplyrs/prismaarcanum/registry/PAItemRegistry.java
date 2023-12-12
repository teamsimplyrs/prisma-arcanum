package com.teamsimplyrs.prismaarcanum.registry;

import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import com.teamsimplyrs.prismaarcanum.item.IgnisWand;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PAItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PrismaArcanum.MODID);

    public static final RegistryObject<Item> RAW_IGNIS = ITEMS.register("raw_ignis", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IGNIS_WAND = ITEMS.register("ignis_wand", () -> new IgnisWand(IgnisWand.itemProperties));
    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }

}
