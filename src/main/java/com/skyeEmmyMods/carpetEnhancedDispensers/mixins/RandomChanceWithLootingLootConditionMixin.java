package com.skyeEmmyMods.carpetEnhancedDispensers.mixins;

import com.skyeEmmyMods.carpetEnhancedDispensers.Main;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.context.LootContext;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(RandomChanceWithLootingLootCondition.class)
public abstract class RandomChanceWithLootingLootConditionMixin {


    @Shadow @Final private float chance;

    @Shadow @Final private float lootingMultiplier;

    @Inject(method = "test(Lnet/minecraft/loot/context/LootContext;)Z", at = @At("HEAD"), cancellable = true)
    public void changeLootingLevel(LootContext lootContext, CallbackInfoReturnable<Boolean> cir) {
        if (lootContext.hasParameter(Main.enchantmentParameter) && lootContext.get(Main.enchantmentParameter).containsKey(Enchantments.LOOTING)) {
            int i = lootContext.get(Main.enchantmentParameter).get(Enchantments.LOOTING);
            cir.setReturnValue(lootContext.getRandom().nextFloat() < this.chance + (float)i * this.lootingMultiplier);
            cir.cancel();
        }
    }


}
