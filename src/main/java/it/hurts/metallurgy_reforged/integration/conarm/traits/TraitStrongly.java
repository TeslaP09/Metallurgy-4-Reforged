package it.hurts.metallurgy_reforged.integration.conarm.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitStrongly extends AbstractArmorTrait implements IConarmMetallurgyTrait {

    public TraitStrongly() //gives player strength 1 if at least one piece is worn, gives strength 2 if all four pieces are worn
    {
        super("strongly", TextFormatting.GREEN);
    }

    int level = 0;

    @SubscribeEvent
    public void onArmorTick(PlayerTickEvent event)
    {
        int amount = getTotalPieces(event.player);
        if (amount  > 0)
            event.player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 40, amount < 4 ? 0 : 1, false, false));
    }

    private int getTotalPieces(EntityPlayer player)
    {
        int amount = 0;
        for (ItemStack armorPiece : player.getArmorInventoryList())
        {
            if (this.isToolWithTrait(armorPiece) && !ToolHelper.isBroken(armorPiece))
                amount++;
        }

        return amount;
    }

}