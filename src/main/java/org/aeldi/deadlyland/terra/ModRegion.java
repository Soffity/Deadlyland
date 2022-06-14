package org.aeldi.deadlyland.terra;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.BuiltinBiomes;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.SurfaceRuleManager;

import java.util.function.Consumer;

public class ModRegion extends Region {

    public ModRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    public static MaterialRules.MaterialRule surface() {

        return MaterialRules.sequence(
                MaterialRules.condition(
                        MaterialRules.STONE_DEPTH_FLOOR,
                        MaterialRules.sequence(
                                MaterialRules.condition(
                                        MaterialRules.water(-1, 0),
                                        MaterialRules.block(Blocks.DIRT.getDefaultState()
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

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        this.addBiomeSimilar(mapper, BiomeKeys.DESERT, ModBiome.DEADLY_PLANTS_KEY);
    }
}
