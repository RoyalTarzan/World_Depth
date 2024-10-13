package net.tarzan.world_depth.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.tarzan.world_depth.block.ModBlocks;
import net.tarzan.world_depth.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.ALUMINIUM_BLOCK.get());
        this.dropSelf(ModBlocks.TITANIUM_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_ALUMINIUM_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_TITANIUM_BLOCK.get());
        this.dropSelf(ModBlocks.ENERGIZER.get());
        this.dropOther(ModBlocks.FERYL_STONE.get(), ModBlocks.FERYL_STONE.get());
        this.dropOther(ModBlocks.SOAP_STONE.get(), ModBlocks.SOAP_STONE.get());

        this.dropSelf(ModBlocks.FERYL_WALL.get());
        this.dropSelf(ModBlocks.FERYL_BUTTON.get());
        this.dropSelf(ModBlocks.FERYL_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.FERYL_STAIRS.get());
        this.add(ModBlocks.FERYL_SLAB.get(), block -> createSlabItemTable(ModBlocks.FERYL_SLAB.get()));
        this.dropSelf(ModBlocks.SOAP_STONE_WALL.get());
        this.dropSelf(ModBlocks.SOAP_STONE_BUTTON.get());
        this.dropSelf(ModBlocks.SOAP_STONE_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.SOAP_STONE_STAIRS.get());
        this.add(ModBlocks.SOAP_STONE_SLAB.get(), block -> createSlabItemTable(ModBlocks.SOAP_STONE_SLAB.get()));

        this.add(ModBlocks.ALUMINIUM_ORE.get(), block -> createAlumiuniumOreDrops(ModBlocks.ALUMINIUM_ORE.get()));
        this.add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get(), block -> createAlumiuniumOreDrops(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get()));
        this.add(ModBlocks.SOAP_STONE_ALUMINIUM_ORE.get(), block -> createAlumiuniumOreDrops(ModBlocks.SOAP_STONE_ALUMINIUM_ORE.get()));
        this.add(ModBlocks.GRANITE_ALUMINIUM_ORE.get(), block -> createAlumiuniumOreDrops(ModBlocks.GRANITE_ALUMINIUM_ORE  .get()));
        this.add(ModBlocks.DIORITE_ALUMINIUM_ORE.get(), block -> createAlumiuniumOreDrops(ModBlocks.DIORITE_ALUMINIUM_ORE.get()));
        this.add(ModBlocks.ANDESITE_ALUMINIUM_ORE.get(), block -> createAlumiuniumOreDrops(ModBlocks.ANDESITE_ALUMINIUM_ORE.get()));
        this.add(ModBlocks.TUFF_ALUMINIUM_ORE.get(), block -> createAlumiuniumOreDrops(ModBlocks.TUFF_ALUMINIUM_ORE.get()));
        this.add(ModBlocks.FERYL_STONE_ALUMINIUM_ORE.get(), block -> createAlumiuniumOreDrops(ModBlocks.FERYL_STONE_ALUMINIUM_ORE.get()));
        this.add(ModBlocks.GRANITE_COAL_ORE.get(), block -> createOreDrop(ModBlocks.GRANITE_COAL_ORE.get(), Items.COAL));
        this.add(ModBlocks.DIORITE_COAL_ORE.get(), block -> createOreDrop(ModBlocks.DIORITE_COAL_ORE.get(), Items.COAL));
        this.add(ModBlocks.TUFF_COAL_ORE.get(), block -> createOreDrop(ModBlocks.TUFF_COAL_ORE.get(), Items.COAL));
        this.add(ModBlocks.ANDESITE_COAL_ORE.get(), block -> createOreDrop(ModBlocks.ANDESITE_COAL_ORE.get(), Items.COAL));
        this.add(ModBlocks.GRANITE_IRON_ORE.get(), block -> createOreDrop(ModBlocks.GRANITE_IRON_ORE.get(), Items.RAW_IRON));
        this.add(ModBlocks.DIORITE_IRON_ORE.get(), block -> createOreDrop(ModBlocks.DIORITE_IRON_ORE.get(), Items.RAW_IRON));
        this.add(ModBlocks.TUFF_IRON_ORE.get(), block -> createOreDrop(ModBlocks.TUFF_IRON_ORE.get(), Items.RAW_IRON));
        this.add(ModBlocks.ANDESITE_IRON_ORE.get(), block -> createOreDrop(ModBlocks.ANDESITE_IRON_ORE.get(), Items.RAW_IRON));
        this.add(ModBlocks.GRANITE_GOLD_ORE.get(), block -> createOreDrop(ModBlocks.GRANITE_GOLD_ORE.get(), Items.RAW_GOLD));
        this.add(ModBlocks.DIORITE_GOLD_ORE.get(), block -> createOreDrop(ModBlocks.DIORITE_GOLD_ORE.get(), Items.RAW_GOLD));
        this.add(ModBlocks.TUFF_GOLD_ORE.get(), block -> createOreDrop(ModBlocks.TUFF_GOLD_ORE.get(), Items.RAW_GOLD));
        this.add(ModBlocks.ANDESITE_GOLD_ORE.get(), block -> createOreDrop(ModBlocks.ANDESITE_GOLD_ORE.get(), Items.RAW_GOLD));
        this.add(ModBlocks.GRANITE_COPPER_ORE.get(), block -> createCopperOreDrops(ModBlocks.GRANITE_COPPER_ORE.get()));
        this.add(ModBlocks.DIORITE_COPPER_ORE.get(), block -> createCopperOreDrops(ModBlocks.DIORITE_COPPER_ORE.get()));
        this.add(ModBlocks.TUFF_COPPER_ORE.get(), block -> createCopperOreDrops(ModBlocks.TUFF_COPPER_ORE.get()));
        this.add(ModBlocks.ANDESITE_COPPER_ORE.get(), block -> createCopperOreDrops(ModBlocks.ANDESITE_COPPER_ORE.get()));
        this.add(ModBlocks.GRANITE_DIAMOND_ORE.get(), block -> createOreDrop(ModBlocks.GRANITE_DIAMOND_ORE.get(), Items.DIAMOND));
        this.add(ModBlocks.DIORITE_DIAMOND_ORE.get(), block -> createOreDrop(ModBlocks.DIORITE_DIAMOND_ORE.get(), Items.DIAMOND));
        this.add(ModBlocks.TUFF_DIAMOND_ORE.get(), block -> createOreDrop(ModBlocks.TUFF_DIAMOND_ORE.get(), Items.DIAMOND));
        this.add(ModBlocks.ANDESITE_DIAMOND_ORE.get(), block -> createOreDrop(ModBlocks.ANDESITE_DIAMOND_ORE.get(), Items.DIAMOND));
        this.add(ModBlocks.SOAP_STONE_DIAMOND_ORE.get(), block -> createOreDrop(ModBlocks.SOAP_STONE_DIAMOND_ORE.get(), Items.DIAMOND));
        this.add(ModBlocks.FERYL_STONE_DIAMOND_ORE.get(), block -> createOreDrop(ModBlocks.FERYL_STONE_DIAMOND_ORE.get(), Items.DIAMOND));
        this.add(ModBlocks.GRANITE_EMERALD_ORE.get(), block -> createOreDrop(ModBlocks.GRANITE_EMERALD_ORE.get(), Items.EMERALD));
        this.add(ModBlocks.DIORITE_EMERALD_ORE.get(), block -> createOreDrop(ModBlocks.DIORITE_EMERALD_ORE.get(), Items.EMERALD));
        this.add(ModBlocks.TUFF_EMERALD_ORE.get(), block -> createOreDrop(ModBlocks.TUFF_EMERALD_ORE.get(), Items.EMERALD));
        this.add(ModBlocks.ANDESITE_EMERALD_ORE.get(), block -> createOreDrop(ModBlocks.ANDESITE_EMERALD_ORE.get(), Items.EMERALD));
        this.add(ModBlocks.TUFF_REDSTONE_ORE.get(), block -> createRedstoneOreDrops(ModBlocks.TUFF_REDSTONE_ORE.get()));
        this.add(ModBlocks.ANDESITE_REDSTONE_ORE.get(), block -> createRedstoneOreDrops(ModBlocks.ANDESITE_REDSTONE_ORE.get()));
        this.add(ModBlocks.DIORITE_REDSTONE_ORE.get(), block -> createRedstoneOreDrops(ModBlocks.DIORITE_REDSTONE_ORE.get()));
        this.add(ModBlocks.GRANITE_REDSTONE_ORE.get(), block -> createRedstoneOreDrops(ModBlocks.GRANITE_REDSTONE_ORE.get()));
        this.add(ModBlocks.TUFF_LAPIS_ORE.get(), block -> createLapisOreDrops(ModBlocks.TUFF_LAPIS_ORE.get()));
        this.add(ModBlocks.ANDESITE_LAPIS_ORE.get(), block -> createLapisOreDrops(ModBlocks.ANDESITE_LAPIS_ORE.get()));
        this.add(ModBlocks.DIORITE_LAPIS_ORE.get(), block -> createLapisOreDrops(ModBlocks.DIORITE_LAPIS_ORE.get()));
        this.add(ModBlocks.GRANITE_LAPIS_ORE.get(), block -> createLapisOreDrops(ModBlocks.GRANITE_LAPIS_ORE.get()));
        this.add(ModBlocks.GRANITE_TITANIUM_ORE.get(), block -> createTitaniumOreDrops(ModBlocks.GRANITE_TITANIUM_ORE.get()));
        this.add(ModBlocks.DIORITE_TITANIUM_ORE.get(), block -> createTitaniumOreDrops(ModBlocks.DIORITE_TITANIUM_ORE.get()));
        this.add(ModBlocks.TUFF_TITANIUM_ORE.get(), block -> createTitaniumOreDrops(ModBlocks.TUFF_TITANIUM_ORE.get()));
        this.add(ModBlocks.ANDESITE_TITANIUM_ORE.get(), block -> createTitaniumOreDrops(ModBlocks.ANDESITE_TITANIUM_ORE.get()));
        this.add(ModBlocks.SOAP_STONE_TITANIUM_ORE.get(), block -> createTitaniumOreDrops(ModBlocks.SOAP_STONE_TITANIUM_ORE.get()));
        this.add(ModBlocks.FERYL_STONE_TITANIUM_ORE.get(), block -> createTitaniumOreDrops(ModBlocks.FERYL_STONE_TITANIUM_ORE.get()));
    }
    protected LootTable.Builder createAlumiuniumOreDrops(Block pBlock) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(ModItems.RAW_ALUMINIUM.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }
    protected LootTable.Builder createTitaniumOreDrops(Block pBlock) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(ModItems.RAW_TITANIUM.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks(){
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
