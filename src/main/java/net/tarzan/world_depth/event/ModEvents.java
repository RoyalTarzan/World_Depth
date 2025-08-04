package net.tarzan.world_depth.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tarzan.world_depth.World_Depth;
import net.tarzan.world_depth.item.ModItems;
import net.tarzan.world_depth.villager.ModVillagers;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = World_Depth.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event){
        if (event.getType()== ModVillagers.ENERGIZER_SMITH.get()){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, new Random().nextInt(3,10)),
                    new ItemStack(ModItems.CHARGED_REDSTONE.get(),new Random().nextInt(2,5)),
                    20, 4,0.1f));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemStack(Items.REDSTONE, new Random().nextInt(16,32)),
                    new ItemStack(Items.EMERALD,new Random().nextInt(1,3)),
                    20, 2,0.1f));

            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemStack(Items.REDSTONE, new Random().nextInt(10,64)),
                    new ItemStack(ModItems.CHARGED_REDSTONE.get(),new Random().nextInt(2,10)),
                    20, 6,0.1f));
            
            trades.get(5).add((entity, randomSource) -> new MerchantOffer(
                    new ItemStack(Items.DIAMOND, new Random().nextInt(1,6)),
                    new ItemStack(ModItems.WORLD_GEM.get(),new Random().nextInt(1,3)),
                    20, 9,0.1f));
        }
    }
}
