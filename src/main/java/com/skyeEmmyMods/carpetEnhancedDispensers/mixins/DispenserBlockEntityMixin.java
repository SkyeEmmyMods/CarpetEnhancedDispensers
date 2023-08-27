package com.skyeEmmyMods.carpetEnhancedDispensers.mixins;

import com.skyeEmmyMods.carpetEnhancedDispensers.DispenserBlockCommuncation;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DispenserBlockEntity.class)
public abstract class DispenserBlockEntityMixin extends LootableContainerBlockEntity implements DispenserBlockCommuncation {

	protected DispenserBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
		super(blockEntityType, blockPos, blockState);
	}

	private NbtList enchantments = new NbtList();

	@Override
	public NbtList getEnchantments() {
		return enchantments;
	}

	@Override
	public void setEnchantments(NbtList enchantments) {
		this.enchantments = enchantments;
	}

	@Inject(method = "writeNbt", at = @At("HEAD"))
	public void writeNbtInject(NbtCompound nbt, CallbackInfo ci) {
		nbt.put("Enchantments", enchantments);
	}

	@Inject(method = "readNbt", at = @At("TAIL"))
	public void readNbtInject(NbtCompound nbt, CallbackInfo ci) {
		enchantments = (NbtList) nbt.get("Enchantments");
	}


}
