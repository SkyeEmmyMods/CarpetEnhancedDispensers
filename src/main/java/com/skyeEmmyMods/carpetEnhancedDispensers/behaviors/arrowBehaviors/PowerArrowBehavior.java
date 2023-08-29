package com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.arrowBehaviors;

import com.skyeEmmyMods.carpetEnhancedDispensers.Options;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.ArrowBehavior;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.GenericBehavior;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;

public class PowerArrowBehavior extends GenericBehavior implements ArrowBehavior {

	@Override
	public Enchantment getEnchant() {
		return Enchantments.POWER;
	}

	@Override
	public boolean isEnabled() {
		return Options.powerArrowDispenser;
	}

	public void applyBehavior(ProjectileEntity arrow, ItemStack itemStack) {
		((PersistentProjectileEntity) arrow).setDamage(((PersistentProjectileEntity) arrow).getDamage() + level * 0.5 + 0.5);
	}
}
