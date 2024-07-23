/*==============================================================================
 = Class: MetallurgyTraitDecay
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic.trait;

import javax.annotation.Nullable;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class MetallurgyTraitStarborn extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitStarborn() {
		super("starborn_trait", 0xFF575000);
		this.register("metallurgy.trait.starborn", "metallurgy.trait.starborn.tooltip");
	}

	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) 
	{
		float minMult = 1f;
		float maxMult = 1.25f;

		float minY = 70f;
		float maxY = 150f;

		float slope = (maxMult - minMult) / (maxY - minY);
		float playerY = (float) player.posY;

		float unclamped = slope * (playerY - minY) + minMult;
		float mult = Math.max(Math.min(unclamped, maxMult), minMult);
		if (player.world.provider.getDimension() == 1 || !player.world.isDaytime())
		{
			if (mult != 1.0f)
				mult = mult * 1.2f;
		}
		System.out.println("mult: " + mult);
		return newDamage * mult;
	}

	@Override
	public void register(String name, @Nullable String tooltip) {
		Utils.localizeEscapingCustomSequences(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localizeEscapingCustomSequences(String.format(LOC_Name, tooltip));
	}

}
