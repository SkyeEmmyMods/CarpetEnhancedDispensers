package com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.arrowBehaviors;

import com.skyeEmmyMods.carpetEnhancedDispensers.Options;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.ArrowBehavior;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.GenericBehavior;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;

public class PunchArrowBehavior extends GenericBehavior implements ArrowBehavior {

	@Override
	public Enchantment getEnchant() {
		return Enchantments.PUNCH;
	}

	@Override
	public boolean isEnabled() {
		return Options.punchArrowDispenser;
	}

	public void applyBehavior(ProjectileEntity arrow, ItemStack itemStack) {
		((PersistentProjectileEntity) arrow).setPunch(level);
	}
}
