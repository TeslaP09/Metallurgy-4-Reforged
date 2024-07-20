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
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import javax.annotation.Nullable;

public class MetallurgyTraitSerialHealer extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitSerialHealer()
	{
		super("serialhealer_trait", 0xFF575000);
		this.register("metallurgy.trait.serialhealer", "metallurgy.trait.serialhealer.tooltip");
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) 
	{
		if (target.getHealth() <= 0) {
			player.heal(target.getMaxHealth() * 0.2f);
		}
	}

	@Override
	public void register(String name, @Nullable String tooltip)
	{
		Utils.localizeEscapingCustomSequences(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localizeEscapingCustomSequences(String.format(LOC_Name, tooltip));
	}

}
