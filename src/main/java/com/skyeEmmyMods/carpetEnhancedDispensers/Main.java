package com.skyeEmmyMods.carpetEnhancedDispensers;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.skyeEmmyMods.carpetEnhancedDispensers.utils.LootTableHandling;
import com.skyeEmmyMods.carpetEnhancedDispensers.utils.TntDispenseBehaviourReplacement;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.util.Identifier;
import utils.UtilsKt;

import java.util.Map;

public class Main implements ModInitializer, CarpetExtension {

	public static final String MOD_ID = "carpet_enhanced_dispensers";

	public static final String ENHANCED_DISPENSER = "enhanced-dispenser";

	public static final LootContextParameter<Map<Enchantment, Integer>> enchantmentParameter = new LootContextParameter<>(new Identifier(MOD_ID, "enchantments"));

	public static final LootContextType modifiedEntityLootContextType = LootContextType.create().require(LootContextParameters.THIS_ENTITY).require(LootContextParameters.ORIGIN).require(LootContextParameters.DAMAGE_SOURCE).allow(LootContextParameters.KILLER_ENTITY).allow(LootContextParameters.DIRECT_KILLER_ENTITY).allow(LootContextParameters.LAST_DAMAGE_PLAYER).allow(enchantmentParameter).build();

	@Override
	public void onInitialize() {
		CarpetServer.manageExtension(this);
	}

	@Override
	public String version() {
		return MOD_ID;
	}

	@Override
	public Map<String, String> canHasTranslations(String lang) {
		return UtilsKt.getTranslationFromResourcePath(lang, this.getClass().getClassLoader());
	}

	@Override
	public void onGameStarted() {
		CarpetServer.settingsManager.parseSettingsClass(Options.class);
		LootTableHandling.registerDispenserCopyEnchants();
		DispenserBlock.BEHAVIORS.remove(Blocks.TNT.asItem());
		DispenserBlock.BEHAVIORS.put(Blocks.TNT.asItem(), TntDispenseBehaviourReplacement.tntBehaviour);
	}
}
