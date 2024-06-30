package com.teamsimplyrs.prismaarcanum.item.spells.spellholograms;

import com.teamsimplyrs.prismaarcanum.capability.spell.SpellData;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractSpellHologram extends Item {
    public final String ITEM_NAME = "spell_hologram";
    public SpellData SPELL_DATA;
    public AbstractSpellHologram(Properties pProperties, SpellData spellData) {
        super(pProperties);
        this.SPELL_DATA = spellData;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(Component.translatable(SPELL_DATA.NAME));
    }


}
