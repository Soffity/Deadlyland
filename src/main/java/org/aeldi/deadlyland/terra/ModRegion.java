package org.aeldi.deadlyland.terra;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ModRegion extends Region {

    public ModRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    public static MaterialRules.MaterialRule makeRule() {
        return MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.biome(DeadlylandPlants.DEADLY_PLANTS_KEY), deadlyland_plants()),
                MaterialRules.condition(MaterialRules.biome(BurntPlants.BURNT_PLANTS_KEY), burnt_plants())
        );
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(BiomeKeys.DESERT, DeadlylandPlants.DEADLY_PLANTS_KEY);
            builder.replaceBiome(BiomeKeys.DESERT, BurntPlants.BURNT_PLANTS_KEY);
        });
//        this.addBiomeSimilar(mapper, BiomeKeys.BADLANDS, ModBiome.DEADLY_PLANTS_KEY);
    }

    private static MaterialRules.MaterialRule burnt_plants() {
        return MaterialRules.sequence(
                MaterialRules.condition(
                        MaterialRules.surface(),
                        MaterialRules.sequence(
                                MaterialRules.condition(
                                        MaterialRules.water(-1, 0),
                                        MaterialRules.sequence(
                                                MaterialRules.condition(
                                                        MaterialRules.noiseThreshold(NoiseParametersKeys.PATCH, 0.05d, 0.4d),
                                                        block(Blocks.MAGMA_BLOCK)
                                                ),
                                                block(Blocks.GRASS_BLOCK)
                                        )
                                ),
                                MaterialRules.condition(
                                        MaterialRules.water(-10, 0),
                                        MaterialRules.block(Blocks.SOUL_SAND.getDefaultState())
                                )
                        )
                ),
                VanillaSurfaceRules.createOverworldSurfaceRule()
        );
    }

    private static MaterialRules.MaterialRule deadlyland_plants() {
        return MaterialRules.sequence(
                MaterialRules.condition(
                        MaterialRules.surface(),
                        MaterialRules.sequence(
                                MaterialRules.condition(
                                        MaterialRules.water(-1, 0),
                                        MaterialRules.sequence(
                                                MaterialRules.condition(
                                                        MaterialRules.noiseThreshold(NoiseParametersKeys.PATCH, 0.05d, 0.4d),
                                                        block(Blocks.COARSE_DIRT)
                                                ),
                                                MaterialRules.condition(
                                                        MaterialRules.noiseThreshold(NoiseParametersKeys.RIDGE, 0.4d, 0.8d),
                                                        block(Blocks.ROOTED_DIRT)
                                                ),
                                                MaterialRules.condition(
                                                        MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE_SWAMP, 0.5d, 1.0d),
                                                        block(Blocks.SOUL_SAND)
                                                ),
                                                block(Blocks.DIRT)
                                        )
                                ),
                                MaterialRules.condition(
                                        MaterialRules.water(-10, 0),
                                        MaterialRules.block(Blocks.SAND.getDefaultState())
                                )
                        )
                ),
                VanillaSurfaceRules.createOverworldSurfaceRule()
        );
    }

    private static MaterialRules.MaterialRule block(Block b) {
        return MaterialRules.block(b.getDefaultState());
    }
}
