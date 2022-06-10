package org.aeldi.deadlyland.terra;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import org.aeldi.deadlyland.Deadlyland;
import org.aeldi.deadlyland.blocks.ModBlocks;

import java.util.Arrays;

public class CrystalOreFeature {
    public static final ConfiguredFeature<?, ?> CRYSTAL_ORE_FEATURE = new ConfiguredFeature(
            Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.CRYSTAL_ORE.getDefaultState(), 6)
    );

    public static PlacedFeature CRYSTAL_ORE_PLACED_FEATURE = new PlacedFeature(RegistryEntry.of(CRYSTAL_ORE_FEATURE), Arrays.asList(
            CountPlacementModifier.of(5),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(0))
    ));

    public static void register() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Deadlyland.mod_namespace, "crystal_ore_feature"), CRYSTAL_ORE_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Deadlyland.mod_namespace, "crystal_ore_feature"), CRYSTAL_ORE_PLACED_FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Deadlyland.mod_namespace, "crystal_ore_feature")));
    }
}
