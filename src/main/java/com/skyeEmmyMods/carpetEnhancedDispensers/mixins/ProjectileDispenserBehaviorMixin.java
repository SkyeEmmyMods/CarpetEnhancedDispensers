package com.skyeEmmyMods.carpetEnhancedDispensers.mixins;

import com.skyeEmmyMods.carpetEnhancedDispensers.DispenserBlockDuckInterface;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.ArrowBehavior;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.GenericBehavior;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Objects;

@Mixin(ProjectileDispenserBehavior.class)
public class ProjectileDispenserBehaviorMixin {

	@Shadow
	protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
		return null;
	}

	@Shadow
	protected float getVariation() {
		return 6.0F;
	}

	@Shadow
	protected float getForce() {
		return 1.1F;
	}

	/**
	 * @author EmiliaThorsen
	 * @reason temp overwrite should be injected
	 */
	@Overwrite
	public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
		World world = pointer.getWorld();
		Position position = DispenserBlock.getOutputLocation(pointer);
		Direction direction = pointer.getBlockState().get(DispenserBlock.FACING);
		ProjectileEntity projectileEntity = this.createProjectile(world, position, stack);
		projectileEntity.setVelocity(direction.getOffsetX(), (float)direction.getOffsetY() + 0.1F, direction.getOffsetZ(), this.getForce(), this.getVariation());

		List<GenericBehavior> behaviors = ((DispenserBlockDuckInterface) Objects.requireNonNull(world.getBlockEntity(pointer.getPos()))).getDispenserBehaviorManager().getApplicableBehaviors(stack);
		behaviors.forEach(behavior -> ((ArrowBehavior)behavior).applyBehavior(projectileEntity, stack));

		world.spawnEntity(projectileEntity);
		stack.decrement(1);
		return stack;
	}

}
