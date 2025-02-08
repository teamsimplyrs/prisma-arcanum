package com.teamsimplyrs.prismaarcanum.registry;

import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import com.teamsimplyrs.prismaarcanum.item.spells.IgnisFireball;
import com.teamsimplyrs.prismaarcanum.item.spells.NullSpell;
import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import com.teamsimplyrs.prismaarcanum.item.spells.spellholograms.*;
import com.teamsimplyrs.prismaarcanum.item.wands.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class PAItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PrismaArcanum.MODID);

    public static final RegistryObject<Item> RAW_IGNIS = ITEMS.register("raw_ignis", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_VENTUS = ITEMS.register("raw_ventus", () -> new Item(new Item.Properties()));


    public static final RegistryObject<SpellHologram> SPELL_HOLOGRAM =
            ITEMS.register(SpellHologram.ITEM_NAME, () -> new SpellHologram(new Item.Properties(), new NullSpell()));


    public static final RegistryObject<Item> IGNIS_WAND = ITEMS.register(IgnisWand.ITEM_NAME, () -> new IgnisWand());

    public static final RegistryObject<Item> AQUA_WAND = ITEMS.register(AquaWand.ITEM_NAME, () -> new AquaWand());
    public static final RegistryObject<Item> TERRA_WAND = ITEMS.register(TerraWand.ITEM_NAME, () -> new TerraWand());
    public static final RegistryObject<Item> FULGUR_WAND = ITEMS.register(FulgurWand.ITEM_NAME, () -> new FulgurWand());
    public static final RegistryObject<Item> GELUM_WAND = ITEMS.register(GelumWand.ITEM_NAME, () -> new GelumWand());
    public static final RegistryObject<Item> VENTUS_WAND = ITEMS.register(VentusWand.ITEM_NAME, () -> new VentusWand());
    public static final RegistryObject<Item> NATURA_WAND = ITEMS.register(NaturaWand.ITEM_NAME, () -> new NaturaWand());
    public static final RegistryObject<Item> LUX_WAND = ITEMS.register(LuxWand.ITEM_NAME, () -> new LuxWand());
    public static final RegistryObject<Item> NOX_WAND = ITEMS.register(NoxWand.ITEM_NAME, () -> new NoxWand());

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);

    }

}
