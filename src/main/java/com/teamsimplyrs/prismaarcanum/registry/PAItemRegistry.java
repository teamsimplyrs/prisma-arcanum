package com.teamsimplyrs.prismaarcanum.registry;

import com.teamsimplyrs.prismaarcanum.PrismaArcanum;
import com.teamsimplyrs.prismaarcanum.capability.spell.SpellData;
import com.teamsimplyrs.prismaarcanum.item.spells.interitus.IgnisFireball;
import com.teamsimplyrs.prismaarcanum.item.spells.spellholograms.*;
import com.teamsimplyrs.prismaarcanum.item.wands.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PAItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PrismaArcanum.MODID);

    public static final RegistryObject<Item> RAW_IGNIS = ITEMS.register("raw_ignis", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_VENTUS = ITEMS.register("raw_ventus", () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> IGNIS_SPELL_HOLOGRAM = ITEMS.register(IgnisSpellHologram.ITEM_NAME, () -> new IgnisSpellHologram(new Item.Properties(), SpellData.NULL_SPELL));
    public static final RegistryObject<Item> IGNIS_SPELL_HOLOGRAM_FIREBALL = ITEMS.register(IgnisSpellHologram.ITEM_NAME, () -> new IgnisSpellHologram(new Item.Properties(), new SpellData(new IgnisFireball())));
    public static final RegistryObject<Item> AQUA_SPELL_HOLOGRAM = ITEMS.register(AquaSpellHologram.ITEM_NAME, () -> new AquaSpellHologram(new Item.Properties(), SpellData.NULL_SPELL));
    public static final RegistryObject<Item> TERRA_SPELL_HOLOGRAM = ITEMS.register(TerraSpellHologram.ITEM_NAME, () -> new TerraSpellHologram(new Item.Properties(), SpellData.NULL_SPELL));
    public static final RegistryObject<Item> FULGUR_SPELL_HOLOGRAM = ITEMS.register(FulgurSpellHologram.ITEM_NAME, () -> new FulgurSpellHologram(new Item.Properties(), SpellData.NULL_SPELL));
    public static final RegistryObject<Item> GELUM_SPELL_HOLOGRAM = ITEMS.register(GelumSpellHologram.ITEM_NAME, () -> new GelumSpellHologram(new Item.Properties(), SpellData.NULL_SPELL));
    public static final RegistryObject<Item> VENTUS_SPELL_HOLOGRAM = ITEMS.register(VentusSpellHologram.ITEM_NAME, () -> new VentusSpellHologram(new Item.Properties(), SpellData.NULL_SPELL));
    public static final RegistryObject<Item> NATURA_SPELL_HOLOGRAM = ITEMS.register(NaturaSpellHologram.ITEM_NAME, () -> new NaturaSpellHologram(new Item.Properties(), SpellData.NULL_SPELL));
    public static final RegistryObject<Item> LUX_SPELL_HOLOGRAM = ITEMS.register(LuxSpellHologram.ITEM_NAME, () -> new LuxSpellHologram(new Item.Properties(), SpellData.NULL_SPELL));
    public static final RegistryObject<Item> NOX_SPELL_HOLOGRAM = ITEMS.register(NoxSpellHologram.ITEM_NAME, () -> new NoxSpellHologram(new Item.Properties(), SpellData.NULL_SPELL));



    public static final RegistryObject<Item> IGNIS_WAND = ITEMS.register(IgnisWand.ITEM_NAME, () -> new IgnisWand());

    public static final RegistryObject<Item> AQUA_WAND = ITEMS.register(AquaWand.ITEM_NAME, () -> new AquaWand());
    public static final RegistryObject<Item> TERRA_WAND = ITEMS.register(TerraWand.ITEM_NAME, () -> new TerraWand());
    public static final RegistryObject<Item> FULGUR_WAND = ITEMS.register(FulgurWand.ITEM_NAME, () -> new FulgurWand());
    public static final RegistryObject<Item> GELUM_WAND = ITEMS.register(GelumWand.ITEM_NAME, () -> new GelumWand());
    public static final RegistryObject<Item> VENTUS_WAND = ITEMS.register(VentusWand.ITEM_NAME, () -> new VentusWand());
    public static final RegistryObject<Item> NATURA_WAND = ITEMS.register(NaturaWand.ITEM_NAME, () -> new NaturaWand());
    public static final RegistryObject<Item> LUX_WAND = ITEMS.register(LuxWand.ITEM_NAME, () -> new LuxWand());
    public static final RegistryObject<Item> NOX_WAND = ITEMS.register(NoxWand.ITEM_NAME, () -> new NoxWand());


    public static void initSpellHolograms()
    {
        SpellData.setSpellData(IGNIS_SPELL_HOLOGRAM_FIREBALL.get().getDefaultInstance(), new IgnisFireball());

    }
    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);

    }

}
