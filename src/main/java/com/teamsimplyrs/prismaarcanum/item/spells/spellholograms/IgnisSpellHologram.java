package com.teamsimplyrs.prismaarcanum.item.spells.spellholograms;

import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;

public class IgnisSpellHologram extends AbstractSpellHologram {

    public static final String ITEM_NAME = "ignis_spell_hologram";
    public SpellBase spell;
    public IgnisSpellHologram(Properties pProperties, SpellBase spellBound) {
        super(pProperties);
        this.spell = spellBound;
    }

    public IgnisSpellHologram(Properties properties)
    {
        super(properties);
        this.spell = null;
    }
}
