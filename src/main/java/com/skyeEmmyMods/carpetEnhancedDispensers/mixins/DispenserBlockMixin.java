package com.skyeEmmyMods.carpetEnhancedDispensers.mixins;

import com.skyeEmmyMods.carpetEnhancedDispensers.DispenserBlockCommuncation;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DispenserBlock.class)
public abstract class DispenserBlockMixin extends DispenserBlock {
	public DispenserBlockMixin(Settings settings) {
		super(settings);
	}

	@Inject(method = "onPlaced", at = @At("HEAD"))
	public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
		if (itemStack.hasEnchantments()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof DispenserBlockEntity) {
				((DispenserBlockCommuncation) blockEntity).setEnchantments(itemStack.getEnchantments());
			}
		}
	}

	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof DispenserBlockEntity) {
			((DispenserBlockCommuncation) blockEntity).getEnchantments()

		}


		super.onBreak(world, pos, state, player);
	}
}
