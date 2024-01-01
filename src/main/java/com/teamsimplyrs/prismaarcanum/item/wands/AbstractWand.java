package com.teamsimplyrs.prismaarcanum.item.wands;

import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import net.minecraft.world.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWand extends Item {

    private static final Logger LOG = LogManager.getLogger();
    public static final String ITEM_NAME = "Wand";
    public static final Properties ITEM_PROPERTIES = new Properties();

    public static List<SpellBase> listSpells = new ArrayList<>();

    public AbstractWand() {
        super(ITEM_PROPERTIES);
    }

    public AbstractWand(Properties pProperties)
    {
        super(pProperties);
    }
}
