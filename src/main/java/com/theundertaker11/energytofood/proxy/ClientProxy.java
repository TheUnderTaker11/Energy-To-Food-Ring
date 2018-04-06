package com.theundertaker11.energytofood.proxy;

import com.theundertaker11.energytofood.Reference;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy{

	@Override
	public void registerItemRenderer(Item item, int meta, String id)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reference.MODID + ":" + id, "inventory"));
	}
	@Override
	public void registerRenders()
	{
		//ModelBakery.registerItemVariants(GRItems.GlassSyringe, GlassSyringeTextures);
		//ModelBakery.registerItemVariants(GRItems.MetalSyringe, MetalSyringeTextures);
	}
}
