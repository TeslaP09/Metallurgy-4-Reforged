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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class MetallurgyTraitWindBurst extends AbstractTrait implements IMetallurgyTrait { //hits cause a burst of wind

	public MetallurgyTraitWindBurst()
	{
		super("windburst_trait", 0xFF575000);
		this.register("metallurgy.trait.windburst", "metallurgy.trait.windburst.tooltip");
	}

	private static final int RANGE = 4;
	
	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) 
	{
		if (((EntityPlayer) player).getCooledAttackStrength(1f) > 0.75) {
			World world = player.world;
			Vec3d center = target.getPositionVector().subtract(new Vec3d(target.posX - player.posX, target.posY - player.posY, target.posZ - player.posZ).normalize());
			AxisAlignedBB box = new AxisAlignedBB(center.x, center.y, center.z, center.x, center.y, center.z).grow(RANGE);
			for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(player, box))
			{	
				Vec3d motionVector = new Vec3d(entity.posX - center.x, 0.6D, entity.posZ - center.z).normalize();
				if (!world.isRemote)
				{
					entity.motionX += motionVector.x * 0.5D;
					entity.motionY += motionVector.y;
					entity.motionZ += motionVector.z * 0.5D;
					entity.velocityChanged = true;
				}
			}
			if (!world.isRemote)
				world.playSound(null, center.x, center.y, center.z, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.AMBIENT, 1F, 0.75F);
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
