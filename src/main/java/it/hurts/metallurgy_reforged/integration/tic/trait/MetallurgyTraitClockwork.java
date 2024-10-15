/*==============================================================================
 = Class: MetallurgyTraitDecay
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic.trait;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;

public class MetallurgyTraitClockwork extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitClockwork() {
		super("clockwork_trait", 0xFF575000);
		this.register("metallurgy.trait.clockwork", "metallurgy.trait.clockwork.tooltip");
	}

	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        NBTTagList tagList = TagUtil.getModifiersTagList(tool);
        float dmgBoost = tagList.tagCount() * 0.5f;
		return newDamage + dmgBoost;
	}

	@Override
	public void register(String name, @Nullable String tooltip) {
		Utils.localizeEscapingCustomSequences(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localizeEscapingCustomSequences(String.format(LOC_Name, tooltip));
	}
	
    @Override
    public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag) {
    	NBTTagList tagList = TagUtil.getModifiersTagList(tool);
        float dmgBoost = tagList.tagCount() * 0.5f;
        String loc = String.format(LOC_Extra, getModifierIdentifier());
        return ImmutableList.of(Util.translateFormatted(loc, Util.df.format(dmgBoost)));
    }

}
