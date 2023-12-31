package com.skyeEmmyMods.carpetEnhancedDispensers.mixins;

import com.skyeEmmyMods.carpetEnhancedDispensers.DispenserBlockDuckInterface;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DispenserBlock.class)
public abstract class DispenserBlockMixin extends BlockWithEntity {

	protected DispenserBlockMixin(Settings settings) {
		super(settings);
	}

	@Inject(method = "onPlaced", at = @At("HEAD"))
	public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
		if (itemStack.hasEnchantments()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof DispenserBlockDuckInterface dispenser) {
				dispenser.setEnchantments(itemStack.getEnchantments());
				dispenser.setRepairCost(itemStack.getRepairCost());
			}
		}
	}

}
