package org.aeldi.deadlyland.terra;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import org.aeldi.deadlyland.blocks.ModBlocks;

public class PricklyGrassFeature {
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PRICKLY_GRASS =
            ConfiguredFeatures.register("prickly_grass_feature", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(12, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.PRICKLY_GRASS)))));
    public static final RegistryEntry<PlacedFeature> GRASS_PLACED = PlacedFeatures.register("grass_placed",
            PRICKLY_GRASS, RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
}
