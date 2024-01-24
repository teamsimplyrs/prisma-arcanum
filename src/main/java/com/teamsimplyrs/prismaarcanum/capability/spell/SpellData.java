package com.teamsimplyrs.prismaarcanum.capability.spell;

import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import com.teamsimplyrs.prismaarcanum.registry.PAItemRegistry;
import com.teamsimplyrs.prismaarcanum.registry.PASpellRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SpellData implements Comparable<SpellData>{
    private final SpellBase SPELL;
    public static final String PA_SPELL = "PA_spell";
    public static final String NAME = "spell_name";
    public static final String ELEMENT = "spell_element";
    public static final String SCHOOL = "spell_school";
    public static final String TIER = "spell_tier";
    public List<SpellBase> listEvolutions = new ArrayList<>(3);
    private MutableComponent displayName;

    public static final SpellData NULL_SPELL = new SpellData(PASpellRegistry.getSpell("null"));


    public SpellData(SpellBase spell)
    {
        this.SPELL = spell;
    }

    public static SpellData getSpellData(ItemStack itemStack)
    {
        CompoundTag tag = itemStack.getTagElement(PA_SPELL);

        // If provided ItemStack has spell NBT, return Spell using SpellName- else return Null Spell;
        return tag != null ?
                new SpellData(PASpellRegistry.getSpell(tag.getString(NAME))) :
                NULL_SPELL;
    }

    public static void setSpellData(ItemStack itemStack, SpellBase spell)
    {
        // Set Spell NBT data for provided ItemStack

        CompoundTag tag = new CompoundTag();
        tag.putString(NAME, spell.spellName);
        tag.putString(ELEMENT, spell.spellElement);
        tag.putString(SCHOOL, spell.spellSchool);
        tag.putString(TIER, spell.spellTier);
        itemStack.addTagElement(PA_SPELL, tag);
    }

    public SpellBase getSpell()
    {
        return this.SPELL == null ? PASpellRegistry.getSpell("null") : this.SPELL;
    }

    public Component getDisplayName()
    {
        // For testing. Replace with actual code later


        return Component.translatable(PAItemRegistry.IGNIS_SPELL_HOLOGRAM.get().getDescriptionId());
    }
    @Override
    public int compareTo(@NotNull SpellData other) {
        return 0;
    }


}
