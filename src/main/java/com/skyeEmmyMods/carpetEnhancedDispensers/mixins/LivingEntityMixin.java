package com.skyeEmmyMods.carpetEnhancedDispensers.mixins;

import com.skyeEmmyMods.carpetEnhancedDispensers.Main;
import com.skyeEmmyMods.carpetEnhancedDispensers.Options;
import com.skyeEmmyMods.carpetEnhancedDispensers.TntEntityDuckInterface;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.HashMap;
import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Redirect(method = "dropLoot", at = @At(value = "INVOKE", target = "Lnet/minecraft/loot/context/LootContext$Builder;build(Lnet/minecraft/loot/context/LootContextType;)Lnet/minecraft/loot/context/LootContext;"))
    public LootContext addEnchantmentsToContext(LootContext.Builder builder, LootContextType type, DamageSource source) {
        if (source.getSource() instanceof TntEntityDuckInterface tnt && !tnt.getEnchantmentNBT().isEmpty()) {
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.fromNbt(tnt.getEnchantmentNBT());
            builder.parameter(Main.enchantmentParameter, enchantments);
        }
        return builder.build(Main.modifiedEntityLootContextType);
    }


}
