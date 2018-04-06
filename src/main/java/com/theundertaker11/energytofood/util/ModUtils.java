package com.theundertaker11.energytofood.util;

import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class ModUtils {

	public static IEnergyStorage getEnergy(ItemStack stack) {
		return stack.getCapability(CapabilityEnergy.ENERGY, null);
	}

	public static int getEnergyStored(ItemStack stack) {
		if (getEnergy(stack) != null) {
			return getEnergy(stack).getEnergyStored();
		}
		return 0;
	}

	public static NBTTagCompound getNBT(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null) {
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
		}
		return tag;
	}

	public static Item getItemByName(String registryName) {
		return Item.REGISTRY.getObject(new ResourceLocation(registryName));
	}
}
