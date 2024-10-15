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
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import javax.annotation.Nullable;

public class MetallurgyTraitEnderSlayer extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitEnderSlayer() { //player deals 1.3x damage if target is enderman, endermite, shulker, ender dragon, or is in the end
		super("enderslayer_trait", 0xFF575000);
		this.register("metallurgy.trait.enderslayer", "metallurgy.trait.enderslayer.tooltip");
	}

	 @Override
	 public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
		if (EntityList.getKey(target).toString().equals("minecraft:enderman") || EntityList.getKey(target).toString().equals("minecraft:endermite") || EntityList.getKey(target).toString().equals("minecraft:shulker") || EntityList.getKey(target).toString().equals("minecraft:ender_dragon"))
			return (newDamage * 1.3f);
		else if (target.world.provider.getDimension() == 1)
			return (newDamage * 1.3f);
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
