package com.skyeEmmyMods.carpetEnhancedDispensers.mixins;

import com.skyeEmmyMods.carpetEnhancedDispensers.TntEntityDuckInterface;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(TntEntity.class)
public abstract class TntEntityMixin extends Entity implements TntEntityDuckInterface {
    @Unique
    NbtList enchantments = new NbtList();

    public TntEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public void setEnchantmentNBT(NbtList enchantmentNBT) {
        enchantments = enchantmentNBT;
    }

    @Override
    public NbtList getEnchantmentNBT() {
        return enchantments;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        enchantments = (NbtList) nbt.get("Enchantments");
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.put("Enchantments", enchantments);
        return nbt;
    }
}
