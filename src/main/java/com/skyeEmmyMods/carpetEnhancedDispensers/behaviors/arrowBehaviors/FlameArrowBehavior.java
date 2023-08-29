package com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.arrowBehaviors;

import com.skyeEmmyMods.carpetEnhancedDispensers.Options;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.ArrowBehavior;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.GenericBehavior;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.projectile.ProjectileEntity;

public class FlameArrowBehavior extends GenericBehavior implements ArrowBehavior {
	@Override
	public Enchantment getEnchant() {
		return Enchantments.FLAME;
	}

	@Override
	public boolean isEnabled() {
		return Options.flameArrowDispenser;
	}

	public void applyBehavior(ProjectileEntity arrow) {
		arrow.setOnFireFor(100);
	}
}
