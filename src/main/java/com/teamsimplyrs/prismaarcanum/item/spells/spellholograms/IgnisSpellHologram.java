package com.teamsimplyrs.prismaarcanum.item.spells.spellholograms;

import com.teamsimplyrs.prismaarcanum.capability.spell.SpellData;
import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import com.teamsimplyrs.prismaarcanum.registry.PAItemRegistry;
import net.minecraft.world.item.ItemStack;

public class IgnisSpellHologram extends AbstractSpellHologram {

    public static final String ITEM_NAME = "ignis_spell_hologram";
    public SpellBase spell;
    public IgnisSpellHologram(Properties pProperties, SpellData spellData) {
        super(pProperties, spellData);
    }

    public IgnisSpellHologram setSpellData(SpellData data){
        this.SPELL_DATA = data;
        return this;
    }

    public static ItemStack create(SpellData data) {
        ItemStack itemstack = new ItemStack(PAItemRegistry.IGNIS_SPELL_HOLOGRAM.get().setSpellData(data));
        return itemstack;
    }
}
