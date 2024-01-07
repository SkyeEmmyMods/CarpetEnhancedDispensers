package com.skyeEmmyMods.carpetEnhancedDispensers.mixins;

import com.skyeEmmyMods.carpetEnhancedDispensers.Options;
import com.skyeEmmyMods.carpetEnhancedDispensers.TntEntityDuckInterface;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.arrowBehaviors.InfinityArrowBehavior;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TntEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Map;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {

    @Mutable
    @Final
    @Shadow
    private final Entity entity;

    protected ExplosionMixin(Entity entity) {
        this.entity = entity;
    }


    @ModifyArg(method = "affectWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/loot/context/LootContext$Builder;parameter(Lnet/minecraft/loot/context/LootContextParameter;Ljava/lang/Object;)Lnet/minecraft/loot/context/LootContext$Builder;"), index = 1)
    private <T> T replaceItem(T value) {
        if(value instanceof ItemStack) {
            ItemStack item = new ItemStack(Items.TNT);
            if(entity instanceof TntEntityDuckInterface tnt) {
                Map<Enchantment, Integer> enchantments = EnchantmentHelper.fromNbt(tnt.getEnchantmentNBT());
                if(Options.silkTouchTntDispenser && enchantments.containsKey(Enchantments.SILK_TOUCH)) {
                    item.addEnchantment(Enchantments.SILK_TOUCH, 1);
                }
                if(Options.fortuneTntDispenser && enchantments.containsKey(Enchantments.FORTUNE)) {
                    item.addEnchantment(Enchantments.FORTUNE, enchantments.get(Enchantments.FORTUNE));
                }

            }
            return (T) item;
        }
        return value;
    }




}
