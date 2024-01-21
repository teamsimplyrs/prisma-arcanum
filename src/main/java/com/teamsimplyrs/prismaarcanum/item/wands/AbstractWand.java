package com.teamsimplyrs.prismaarcanum.item.wands;

import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWand extends Item {

    private static final Logger LOG = LogManager.getLogger();
    public static final String ITEM_NAME = "Wand";
    public static final Properties ITEM_PROPERTIES = new Properties();

    public static List<SpellBase> listSpells;

    public final String WAND_ELEMENT = "none";

    public int maxAffinity = 100;
    public int currentAffinity;

    public AbstractWand() {
        super(ITEM_PROPERTIES);
        this.currentAffinity = this.maxAffinity;
    }

    public AbstractWand(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }
}
