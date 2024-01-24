package com.teamsimplyrs.prismaarcanum.item.spells.spellholograms;

import com.teamsimplyrs.prismaarcanum.capability.spell.SpellData;
import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;

public class IgnisSpellHologram extends AbstractSpellHologram {

    public static final String ITEM_NAME = "ignis_spell_hologram";
    public SpellBase spell;
    public IgnisSpellHologram(Properties pProperties, SpellData spellData) {
        super(pProperties, spellData);
    }
}
