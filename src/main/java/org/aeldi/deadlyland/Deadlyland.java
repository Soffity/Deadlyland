package org.aeldi.deadlyland;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.aeldi.deadlyland.blocks.ModBlocks;
import org.aeldi.deadlyland.items.ModItems;
import org.aeldi.deadlyland.terra.CrystalOreFeature;
import org.aeldi.deadlyland.terra.ModBiome;
import org.aeldi.deadlyland.terra.ModRegion;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class Deadlyland implements ModInitializer, TerraBlenderApi {

    public static final String mod_namespace = "deadlyland";

    /*
     * Items
     */
    public static final ItemGroup MOD_GROUP = FabricItemGroupBuilder.build(new Identifier(mod_namespace, "deadlyland_group"), () -> Items.DEAD_BUSH.getDefaultStack());

    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        ModBiome.registerBiome();

        ModItems.registerItems();

        ModBlocks.registerBlocks();

        CrystalOreFeature.register();
    }

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModRegion(new Identifier(mod_namespace, "dl_region"), 1));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, mod_namespace, ModRegion.surface());

    }
}
