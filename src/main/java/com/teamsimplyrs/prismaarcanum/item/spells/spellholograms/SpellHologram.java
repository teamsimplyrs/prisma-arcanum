package com.teamsimplyrs.prismaarcanum.item.spells.spellholograms;

import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import com.teamsimplyrs.prismaarcanum.registry.PAItemRegistry;
import com.teamsimplyrs.prismaarcanum.registry.PASpellRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpellHologram extends Item {
    public static final String ITEM_NAME = "spell_hologram";
    public SpellBase spell;
    public SpellHologram(Properties pProperties, SpellBase spell) {
        super(pProperties);
        this.spell = spell;
    }

    public static ItemStack createSpellHologram(SpellBase spell) {
        ItemStack stack = new ItemStack(PAItemRegistry.SPELL_HOLOGRAM.get());
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString("spell_id", spell.spellName);
        tag.putString("spell_element", spell.spellElement);
        tag.putInt("spell_element_id", PASpellRegistry.getSpellElementID(spell.spellElement));
        tag.putString("spell_school", spell.spellSchool);
        tag.putString("spell_tier", spell.spellTier);
        return stack;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    public static String getSpellId(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null && tag.contains("spell_id") ? tag.getString("spell_id") : "unknown";
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        String spellId = getSpellId(stack);
        tooltip.add(Component.literal("Spell: " + spellId).withStyle(ChatFormatting.GRAY));
    }


}
