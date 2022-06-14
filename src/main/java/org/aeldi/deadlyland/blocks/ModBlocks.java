package org.aeldi.deadlyland.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.aeldi.deadlyland.Deadlyland;

public class ModBlocks {

    public static final PlantBlock PRICKLY_GRASS = new PlantBlock(FabricBlockSettings.copyOf(Blocks.GRASS));
    public static final LeavesBlock BAOBAB_LEAVES = new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES));
    public static final OreBlock CRYSTAL_ORE = new OreBlock(FabricBlockSettings.of(new FabricMaterialBuilder(MapColor.CLEAR).build()).strength(3.5f));
    public static final Block GRAVE_DIRT = new GraveDirtBlock(FabricBlockSettings.copyOf(Blocks.DIRT));

    public static void registerBlocks() {
        registerBlock("prickly_grass", PRICKLY_GRASS);
        registerBlock("baobab_leaves", BAOBAB_LEAVES);
        registerBlock("crystal_ore", CRYSTAL_ORE);
        registerBlock("grave_dirt", GRAVE_DIRT);
    }

    public static void registerBlock(String name, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(Deadlyland.mod_namespace, name), block);
        Registry.register(Registry.ITEM, new Identifier(Deadlyland.mod_namespace, name), new BlockItem(block, new FabricItemSettings().group(Deadlyland.MOD_GROUP)));
    }
}
