/*==============================================================================
 = Class: MetallurgyTraitDecay
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic.trait;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import javax.annotation.Nullable;

public class MetallurgyTraitArmorReactive extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitArmorReactive()
	{
		super("armorreactive_trait", 0xFF575000);
		this.register("metallurgy.trait.armorreactive", "metallurgy.trait.armorreactive.tooltip");
	}

	 public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
		 if (target.getEntityAttribute(SharedMonsterAttributes.ARMOR).getAttributeValue() > 10) {
			 return newDamage * 1.6f;
		 }
		 return newDamage;
	 }

	@Override
	public void register(String name, @Nullable String tooltip)
	{
		Utils.localizeEscapingCustomSequences(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localizeEscapingCustomSequences(String.format(LOC_Name, tooltip));
	}

}
