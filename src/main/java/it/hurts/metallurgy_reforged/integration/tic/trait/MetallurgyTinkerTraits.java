/*==============================================================================
 = Class: MetallurgyTinkerTraits
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic.trait;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHand;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.tinkering.TinkersItem;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.TagUtil;

public class MetallurgyTinkerTraits {

	public static final AbstractTrait lifeStealTrait = new MetallurgyTraitLifeSteal();
	public static final AbstractTrait duplicationTrait = new MetallurgyTraitDuplication();
	public static final AbstractTrait opistognathusTrait = new MetallurgyTraitOpistognathus();
	
	public static final AbstractTrait explosiveTrait = new MetallurgyTraitExplosive();
	public static final AbstractTrait devouringTrait = new MetallurgyTraitDevouring();
	public static final AbstractTrait egoTrait = new MetallurgyTraitEgo();
	public static final AbstractTrait enderSlayerTrait = new MetallurgyTraitEnderSlayer();
	public static final AbstractTrait precisionTrait = new MetallurgyTraitPrecision();
	public static final AbstractTrait coldTrait = new MetallurgyTraitCold();
	public static final AbstractTrait armorReactiveTrait = new MetallurgyTraitArmorReactive();
	public static final AbstractTrait serialHealerTrait = new MetallurgyTraitSerialHealer();
	public static final AbstractTrait phoenixTrait = new MetallurgyTraitPhoenix();
	public static final AbstractTrait bloodthirstyTrait = new MetallurgyTraitBloodthirsty();
	public static final AbstractTrait umbralLucencyTrait = new MetallurgyTraitUmbralLucency();
	public static final AbstractTrait starbornTrait = new MetallurgyTraitStarborn();
	
	public static boolean isMetallurgyTrait(EntityPlayer player, String traitToCheck)
	{
		boolean flag = false;

		Item item = player.getHeldItem(EnumHand.MAIN_HAND).getItem();

		if (item instanceof TinkersItem)
		{
			NBTTagList list = TagUtil.getTraitsTagList(player.getHeldItem(EnumHand.MAIN_HAND));

			for (int i = 0; i < list.tagCount(); i++)
			{
				ITrait trait = TinkerRegistry.getTrait(list.getStringTagAt(i));

				if (trait instanceof IMetallurgyTrait)
				{
					IMetallurgyTrait metallurgyTrait = (IMetallurgyTrait) trait;

					if (metallurgyTrait.getIdentifier().equals(traitToCheck + "_trait"))
						flag = true;
				}
			}
		}

		return flag;
	}

}
