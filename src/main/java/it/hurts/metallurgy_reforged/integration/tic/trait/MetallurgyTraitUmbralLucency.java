/*==============================================================================
 = Class: MetallurgyTraitDecay
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic.trait;

import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class MetallurgyTraitUmbralLucency extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitUmbralLucency() {
		super("umbrallucency_trait", 0xFF575000);
		this.register("metallurgy.trait.umbrallucency", "metallurgy.trait.umbrallucency.tooltip");
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static final UUID ATK_SPEED = UUID.fromString("0190d367-9c2d-737b-9429-e968d9e13441");

	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical)
	{
		if (player.world.getLight(player.getPosition()) >= 10) {
			return newDamage * 1.25f;
		}
		return newDamage;
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit)
	{
		NBTTagCompound comp = tool.getOrCreateSubCompound(this.getModifierIdentifier());
		comp.setInteger("Light", player.world.getLight(player.getPosition()));
	}
	
	@Override
	public void getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack, Multimap<String, AttributeModifier> attributeMap)
	{
		NBTTagCompound comp = stack.getOrCreateSubCompound(this.getModifierIdentifier());
		if (slot != EntityEquipmentSlot.MAINHAND || comp.getInteger("Light") > 5)
            return;
        attributeMap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
                new AttributeModifier(ATK_SPEED, "ATK Speed Buff", 0.4, 2));
	}
	
	@Override
	public void register(String name, @Nullable String tooltip) {
		Utils.localizeEscapingCustomSequences(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localizeEscapingCustomSequences(String.format(LOC_Name, tooltip));
	}

}
