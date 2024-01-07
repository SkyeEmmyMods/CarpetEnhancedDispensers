package com.skyeEmmyMods.carpetEnhancedDispensers.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.skyeEmmyMods.carpetEnhancedDispensers.DispenserBlockDuckInterface;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.nbt.NbtList;

public class CopyEnchantmentLootFunction extends ConditionalLootFunction {
	protected CopyEnchantmentLootFunction(LootCondition[] conditions) {
		super(conditions);
	}

	@Override
	protected ItemStack process(ItemStack stack, LootContext context) {
		Object object = context.get(LootContextParameters.BLOCK_ENTITY);
		if (object instanceof DispenserBlockDuckInterface dispenser) {
			NbtList enchantmentNbt = dispenser.getEnchantments();
			var enchantments = EnchantmentHelper.fromNbt(enchantmentNbt);
			enchantments.forEach(stack::addEnchantment);
			int repairCost;
			if ((repairCost = dispenser.getRepairCost()) != 0) {
				stack.setRepairCost(repairCost);
			}
		}
		return stack;
	}

	@Override
	public LootFunctionType getType() {
		return LootTableHandling.COPY_ENCHANTMENTS;
	}

	public static class Serializer extends ConditionalLootFunction.Serializer<CopyEnchantmentLootFunction> {
		public Serializer() {
		}

		public void toJson(JsonObject jsonObject, CopyEnchantmentLootFunction copyEnchantmentLootFunction, JsonSerializationContext jsonSerializationContext) {
			super.toJson(jsonObject, copyEnchantmentLootFunction, jsonSerializationContext);
		}

		public CopyEnchantmentLootFunction fromJson(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootCondition[] lootConditions) {
			return new CopyEnchantmentLootFunction(lootConditions);
		}
	}
	public static class Builder extends ConditionalLootFunction.Builder<CopyEnchantmentLootFunction.Builder> {

		protected CopyEnchantmentLootFunction.Builder getThisBuilder() {
			return this;
		}

		public LootFunction build() {
			return new CopyEnchantmentLootFunction(this.getConditions());
		}
	}

}
