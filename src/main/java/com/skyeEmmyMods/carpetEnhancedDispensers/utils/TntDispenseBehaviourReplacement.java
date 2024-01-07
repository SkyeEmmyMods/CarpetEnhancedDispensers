package com.skyeEmmyMods.carpetEnhancedDispensers.utils;

import com.skyeEmmyMods.carpetEnhancedDispensers.DispenserBlockDuckInterface;
import com.skyeEmmyMods.carpetEnhancedDispensers.TntEntityDuckInterface;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.ArrowBehavior;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.GenericBehavior;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.TntBehavior;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.Objects;

public class TntDispenseBehaviourReplacement {

    public static ItemDispenserBehavior tntBehaviour = new ItemDispenserBehavior(){
        @Override
        protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
            ServerWorld world = pointer.getWorld();
            BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
            TntEntity tntEntity = new TntEntity(world, (double)blockPos.getX() + 0.5, blockPos.getY(), (double)blockPos.getZ() + 0.5, null);

            List<GenericBehavior> behaviors = ((DispenserBlockDuckInterface) Objects.requireNonNull(world.getBlockEntity(pointer.getPos()))).getDispenserBehaviorManager().getApplicableBehaviors(stack);
            behaviors.forEach(behavior -> ((TntBehavior)behavior).applyBehavior(tntEntity, stack));

            world.spawnEntity(tntEntity);
            world.playSound(null, tntEntity.getX(), tntEntity.getY(), tntEntity.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.emitGameEvent(null, GameEvent.ENTITY_PLACE, blockPos);
            stack.decrement(1);
            return stack;
        }
    };

}
