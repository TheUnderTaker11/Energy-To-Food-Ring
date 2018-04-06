package com.theundertaker11.energytofood.item;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.theundertaker11.energytofood.EnergyToFood;
import com.theundertaker11.energytofood.render.IItemModelProvider;
import com.theundertaker11.energytofood.util.ModUtils;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@Optional.Interface(iface = "baubles.api.IBauble", modid = "baubles")
public class ItemFoodRing extends Item implements IItemModelProvider, IBauble {

	public static final int Capacity = 50000;
	private static String name;

	@CapabilityInject(IEnergyStorage.class)
	public EnergyStorage energyStorage = new EnergyStorage(Capacity);

	public ItemFoodRing(String name) {
		super();
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(CreativeTabs.MISC);
		this.setMaxStackSize(1);
		this.name = name;
	}

	@Override
	public void registerItemModel(Item item) {
		EnergyToFood.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.TRINKET;
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayer && !entityIn.getEntityWorld().isRemote) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if (ModUtils.getEnergyStored(stack) == this.Capacity && player.getFoodStats().needFood()) {
				ModUtils.getEnergy(stack).extractEnergy(this.Capacity, false);
				player.getFoodStats().addStats(1, 2);
			}
		}
	}

	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase entityIn) {
		if (entityIn instanceof EntityPlayer && !entityIn.getEntityWorld().isRemote) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if (ModUtils.getEnergyStored(stack) == this.Capacity && player.getFoodStats().needFood()) {
				ModUtils.getEnergy(stack).extractEnergy(this.Capacity, false);
				player.getFoodStats().addStats(1, 2);
			}
		}
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
		return new ICapabilityProvider() {
			@Override
			public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
				return capability == CapabilityEnergy.ENERGY;
			}

			@Nullable
			@Override
			public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
				if (capability == CapabilityEnergy.ENERGY)
					return CapabilityEnergy.ENERGY.cast(energyStorage);
				else
					return null;
			}
		};
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		if (ModUtils.getEnergy(stack) != null) {
			IEnergyStorage energyCap = ModUtils.getEnergy(stack);
			return 1 - ((double) energyCap.getEnergyStored() / (double) this.Capacity);
		} else
			return 0;
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		IEnergyStorage energy = ModUtils.getEnergy(stack);
		if (energy != null) {
			tooltip.add("The energy stored is: " + energy.getEnergyStored() + "/" + energy.getMaxEnergyStored());
		} else
			tooltip.add("IT DONT WORKKKK");
	}
/*
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("energy", 0);
		ItemStack stack = new ItemStack(ModItems.FoodRing, 1, 0, tag);
		subItems.add(new ItemStack(itemIn));
	}

	public int getEnergyStored(ItemStack container) {
		return ModUtils.getEnergy(container) != null ? ModUtils.getEnergy(container).getEnergyStored() : 0;
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		return ModUtils.getEnergy(container) != null ? ModUtils.getEnergy(container).receiveEnergy(maxReceive, simulate) : 0;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return this.Capacity;
	}*/
}
