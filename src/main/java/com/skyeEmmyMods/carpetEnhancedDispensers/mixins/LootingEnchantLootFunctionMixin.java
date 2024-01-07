package com.skyeEmmyMods.carpetEnhancedDispensers.mixins;

import com.skyeEmmyMods.carpetEnhancedDispensers.Main;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.provider.number.LootNumberProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LootingEnchantLootFunction.class)
public abstract class LootingEnchantLootFunctionMixin {

    @Shadow @Final private int limit;

    @Shadow abstract boolean hasLimit();

    @Shadow @Final private LootNumberProvider countRange;

    @Inject(method = "process", at = @At("RETURN"))
    public void addLooting(ItemStack stack, LootContext context, CallbackInfoReturnable<ItemStack> cir) {
        if (context.hasParameter(Main.enchantmentParameter) && context.get(Main.enchantmentParameter).containsKey(Enchantments.LOOTING)) {
            int i = context.get(Main.enchantmentParameter).get(Enchantments.LOOTING);
            if (i == 0) {
                return;
            }
            float f = (float)i * this.countRange.nextFloat(context);
            stack.increment(Math.round(f));
            if (this.hasLimit() && stack.getCount() > this.limit) {
                stack.setCount(this.limit);
            }
        }
    }


}
