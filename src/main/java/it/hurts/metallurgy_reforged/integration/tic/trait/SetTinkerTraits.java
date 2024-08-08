/*==============================================================================
 = Class: SetTinkerTraits
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic.trait;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.material.Metal;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.tools.TinkerTraits;

import static slimeknights.tconstruct.library.materials.MaterialTypes.*;
import static slimeknights.tconstruct.tools.TinkerTraits.*;

public class SetTinkerTraits {

	public static void addTraits(Metal metal, Material material)
	{

		switch (metal.getStats().getName())
		{
			//		TraitSuperheat = Increased damage to enemies that are on fire
			
			case "amordrine":
				material.addTrait(holy, HEAD);
				addToEveryPart(material, dense);
			break;
			case "haderoth":
				material.addTrait(MetallurgyTinkerTraits.phoenixTrait, HEAD);
				addToEveryPart(material, duritos);
			break;
			case "platinum":
				material.addTrait(holy, HEAD);
				material.addTrait(dense);
			break;
			case "vulcanite":
				material.addTrait(MetallurgyTinkerTraits.explosiveTrait, HEAD);
				material.addTrait(aridiculous);
			break;
			case "ignatius":
				material.addTrait(superheat, HEAD);
				material.addTrait(autosmelt);
			break;
			case "etherium":
				addToEveryPart(material, MetallurgyTinkerTraits.lifeStealTrait);
			break;
			case "quicksilver":
				material.addTrait(momentum, HEAD);
				material.addTrait(lightweight);
			break;
			case "brass":
				addToEveryPart(material, writable);
			break;
			case "astral_silver":
				material.addTrait(MetallurgyTinkerTraits.starbornTrait, HEAD);
				material.addTrait(unnatural);
			break;
			case "hepatizon":
				material.addTrait(dense, HEAD);
			break;
			case "eximite":
				material.addTrait(MetallurgyTinkerTraits.enderSlayerTrait, HEAD);
				material.addTrait(enderference);
			break;
			case "sanguinite":
				material.addTrait(MetallurgyTinkerTraits.devouringTrait, HEAD);
				material.addTrait(insatiable);
			break;
			case "desichalkos":
				material.addTrait(dense, HEAD);
				material.addTrait(dense);
			break;
			case "celenegil":
				material.addTrait(MetallurgyTinkerTraits.egoTrait, HEAD);
				material.addTrait(coldblooded);
			break;
			case "shadow_iron":
				addToEveryPart(material, MetallurgyTinkerTraits.precisionTrait);
			break;
			case "mithril":
				material.addTrait(dense, HEAD);
				material.addTrait(coldblooded);
				addToEveryPart(material, writable2);
			break;
			case "carmot":
				addToEveryPart(material, dense);
			break;
			case "ceruclase":
				addToEveryPart(material, MetallurgyTinkerTraits.coldTrait);
			break;
			case "deep_iron":
				material.addTrait(aquadynamic, HEAD);
				addToEveryPart(material, MetallurgyTinkerTraits.opistognathusTrait);
			break;
			case "angmallen":
				addToEveryPart(material, MetallurgyTinkerTraits.armorReactiveTrait);
			break;
			case "kalendrite":
				addToEveryPart(material, MetallurgyTinkerTraits.serialHealerTrait);
			break;
			case "damascus_steel":
				material.addTrait(sharp, HEAD);
				material.addTrait(stiff);
				addToEveryPart(material, established);
			break;
			case "prometheum":
				addToEveryPart(material, ecological);
			break;
			case "adamantine":
				addToEveryPart(material, MetallurgyTinkerTraits.symbiosisTrait);
			break;
			case "tartarite":
				addToEveryPart(material, writable2);
			break;
			case "atlarus":
				addToEveryPart(material, dense);
			break;
			case "vyroxeres":
				addToEveryPart(material, poisonous);
			break;
			case "black_steel":
				addToEveryPart(material, MetallurgyTinkerTraits.reinforcedTrait);
			break;
			case "osmium":
			break;
			case "oureclase":
				addToEveryPart(material, coldblooded);
			break;
			case "inolashite":
				addToEveryPart(material, dense);
			break;
			case "orichalcum":
				addToEveryPart(material, MetallurgyTinkerTraits.bloodthirstyTrait);
			break;
			case "shadow_steel":
				addToEveryPart(material, MetallurgyTinkerTraits.umbralLucencyTrait);
			break;
			case "midasium":
				material.addTrait(dense, HEAD);
				
				if (GeneralConfig.enableDuplicationTrait)
					material.addTrait(MetallurgyTinkerTraits.duplicationTrait);
			break;
			case "krik":
				addToEveryPart(material, MetallurgyTinkerTraits.aerialBaneTrait);
			break;
			default:
		}
	}

	private static void addToEveryPart(Material material, AbstractTrait trait)
	{
		material.addTrait(trait, HEAD);
		material.addTrait(trait, HANDLE);
		material.addTrait(trait, EXTRA);
	}

}
