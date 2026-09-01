package net.tarzan.world_depth.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tarzan.world_depth.item.ModItems;
import net.tarzan.world_depth.item.custom.Material;

@Mod.EventBusSubscriber(value= Dist.CLIENT,bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModClientBusEvents {
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event){
        event.register((Material::getColor), ModItems.CUSTOM_MATERIAL.get());
        event.register(Material.PickaxeMaterial::getColor,ModItems.CUSTOM_MATERIAL_PICKAXE.get());
        event.register(Material.AxeMaterial::getColor,ModItems.CUSTOM_MATERIAL_AXE.get());
        event.register(Material.ShovelMaterial::getColor,ModItems.CUSTOM_MATERIAL_SHOVEL.get());
        event.register(Material.SwordMaterial::getColor,ModItems.CUSTOM_MATERIAL_SWORD.get());
        event.register(Material.HoeMaterial::getColor,ModItems.CUSTOM_MATERIAL_HOE.get());
        event.register(Material.ArmorMaterial::getColor,ModItems.CUSTOM_MATERIAL_HELMET.get());
        event.register(Material.ArmorMaterial::getColor,ModItems.CUSTOM_MATERIAL_CHESTPLATE.get());
        event.register(Material.ArmorMaterial::getColor,ModItems.CUSTOM_MATERIAL_LEGGINGS.get());
        event.register(Material.ArmorMaterial::getColor,ModItems.CUSTOM_MATERIAL_BOOTS.get());
    }
}
