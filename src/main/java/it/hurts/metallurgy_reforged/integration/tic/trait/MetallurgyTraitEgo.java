/*==============================================================================
 = Class: MetallurgyTraitDuplication
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic.trait;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import java.util.Set;

import javax.annotation.Nullable;

public class MetallurgyTraitEgo extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitEgo()
	{
		super("ego_trait", 0xFF575000);
		this.register("metallurgy.trait.ego", "metallurgy.trait.ego.tooltip");
	}

	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) 
	{
		if (target.getHealth() >= target.getMaxHealth()) 
		{
			target.addTag("ego_fullhp");
		}
	}
	
	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) 
	{
		if (!(target.getTags().contains("ego_hashit")))
		{
			
			if (target.getHealth() <= 0 && (target.getTags().contains("ego_fullhp")))
			{
				player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 200, 0, false, false));
				player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 200, 0, false, false));
			    }
		 target.addTag("ego_hashit");
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
