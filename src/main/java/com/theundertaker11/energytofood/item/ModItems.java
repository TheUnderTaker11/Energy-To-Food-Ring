package com.theundertaker11.energytofood.item;

import com.theundertaker11.energytofood.render.IItemModelProvider;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

	public static Item FoodRing;
	public static void init()
	{
		FoodRing = register(new ItemFoodRing("foodring"));
	}
	
	private static <T extends Item> T register(T item) 
	{
		GameRegistry.register(item);
		
		if(item instanceof IItemModelProvider){
			((IItemModelProvider)item).registerItemModel(item);
		}
		return item;
	}
}
