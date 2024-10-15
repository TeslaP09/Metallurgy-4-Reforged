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

public class MetallurgyTraitBackstab extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitBackstab()
	{
		super("backstab_trait", 0xFF575000);
		this.register("metallurgy.trait.backstab", "metallurgy.trait.backstab.tooltip");
	}

	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
		System.out.println(player.rotationYaw + " " + target.rotationYaw);
		double playerRot = player.rotationYaw;
		double targetRot = target.rotationYaw;

		while (playerRot  < 0) {
		    playerRot += 360;
		}

		while (targetRot < 0) {
		    targetRot += 360;
		}

		playerRot  %= 360;
		targetRot %= 360;
		
		if (Math.abs(playerRot - targetRot) <= 45) {
			System.out.println("deez");
			return newDamage * 1.5f;
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
