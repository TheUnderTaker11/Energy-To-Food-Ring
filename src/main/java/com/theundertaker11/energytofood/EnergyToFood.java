package com.theundertaker11.energytofood;

import com.theundertaker11.energytofood.item.ModItems;
import com.theundertaker11.energytofood.proxy.CommonProxy;
import com.theundertaker11.energytofood.util.ModUtils;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid = Reference.MODID, version = Reference.VERSION, name = Reference.NAME, acceptedMinecraftVersions = Reference.ACCEPTED_MINECRAFT)
public class EnergyToFood
{
	
    @SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.SERVERPROXY)
	public static CommonProxy proxy;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModItems.init();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.registerRenders();
		if(Loader.isModLoaded("cyberware")) {
			GameRegistry.addShapedRecipe(ModItems.FoodRing.getRegistryName(), null, new ItemStack(ModItems.FoodRing), new Object[]{
				"zyz",
				"yxy",
				"zyz", 'x', new ItemStack(ModUtils.getItemByName("cyberware:lowerOrgansUpgrades"), 1, 1), 'y', Items.FERMENTED_SPIDER_EYE, 'z', new ItemStack(ModUtils.getItemByName("cyberware:lowerOrgansUpgrades"), 1, 2)});
        
		}
		else {
			GameRegistry.addShapedRecipe(ModItems.FoodRing.getRegistryName(), null, new ItemStack(ModItems.FoodRing), new Object[]{
					"zyz",
					"yxy",
					"zyz", 'x', Blocks.EMERALD_BLOCK, 'y', Items.DIAMOND, 'z', Items.REDSTONE});
		}
	}
}
