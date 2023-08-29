package com.skyeEmmyMods.carpetEnhancedDispensers.utils;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;

public class LootTableHandling {
	
	private static LootFunctionType register(String id, JsonSerializer<? extends LootFunction> jsonSerializer) {
		return Registry.register(Registries.LOOT_FUNCTION_TYPE, new Identifier(id), new LootFunctionType(jsonSerializer));
	}
	
	private static final Identifier DISPENSER_LOOT_TABLE_ID = Blocks.DISPENSER.getLootTableId();
	
	public static final LootFunctionType COPY_ENCHANTMENTS = register("copy_enchantments", new CopyEnchantmentLootFunction.Serializer());
	
	public static void registerDispenserCopyEnchants() {
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if (source.isBuiltin() && DISPENSER_LOOT_TABLE_ID.equals(id)) {
				CopyEnchantmentLootFunction.Builder functionBuilder = new CopyEnchantmentLootFunction.Builder();
				tableBuilder.modifyPools(
						builder -> {
							builder.apply(functionBuilder);
						}
				);
			}
		});
	}
	
}
