package com.teamsimplyrs.prismaarcanum.events;

import com.teamsimplyrs.prismaarcanum.registry.PAItemRegistry;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class WandColors implements ItemColor {
    @Override
    public int getColor(ItemStack itemStack, int tintIndex) {
        InitWandColorMap();
        if (tintIndex == 1)
        {
            if (wand_color_map.containsKey(itemStack.getItem())) return wand_color_map.get(itemStack.getItem());
        }
        return -1;
    }


    private static Map<Item, Integer> wand_color_map = new HashMap<>();
    private static void InitWandColorMap()
    {
        wand_color_map.put(PAItemRegistry.IGNIS_WAND.get(), 0xff6026);
        wand_color_map.put(PAItemRegistry.AQUA_WAND.get(), 0x2f6beb);
        wand_color_map.put(PAItemRegistry.TERRA_WAND.get(), 0x914b14);
        wand_color_map.put(PAItemRegistry.FULGUR_WAND.get(), 0xc64ed4);
        wand_color_map.put(PAItemRegistry.GELUM_WAND.get(), 0x59fff4);
        wand_color_map.put(PAItemRegistry.NATURA_WAND.get(), 0x51bd33);
        wand_color_map.put(PAItemRegistry.VENTUS_WAND.get(), 0x30ab65);
        wand_color_map.put(PAItemRegistry.LUX_WAND.get(), 0xf3ff8a);
        wand_color_map.put(PAItemRegistry.NOX_WAND.get(), 0x5a0ec4);
    }

    public static Map<Item, Integer> GetWandColorMap()
    {
        InitWandColorMap();
        return wand_color_map;
    }
}
