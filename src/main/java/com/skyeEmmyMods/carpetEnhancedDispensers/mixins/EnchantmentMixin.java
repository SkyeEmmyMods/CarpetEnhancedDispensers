package com.skyeEmmyMods.carpetEnhancedDispensers.mixins;

import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.Behaviors;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.GenericBehavior;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;


@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {
	
	@Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
	public void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
		System.out.println(this);
		Map<Item, List<GenericBehavior>> behaviorMap = Behaviors.behaviorMap;
		for (List<GenericBehavior> behaviors : behaviorMap.values()) {
			for (GenericBehavior behavior : behaviors) {
				if (behavior.getEnchant().equals(this) && behavior.isEnabled()) {
					System.out.println(behavior.getEnchant());
					cir.setReturnValue(true);
				}
			}
		}
	}
	
}
