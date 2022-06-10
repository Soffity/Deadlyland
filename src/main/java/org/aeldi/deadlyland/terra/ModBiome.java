package org.aeldi.deadlyland.terra;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.aeldi.deadlyland.Deadlyland;

public class ModBiome {
    public static final RegistryKey<Biome> DEADLY_PLANTS_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(Deadlyland.mod_namespace, "deadly_plants"));
    public static final Biome DEADLY_PLANTS = Deadly_plants();

    public static Biome Deadly_plants() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addMonsters(spawnSettings, 40, 5, 90, false);
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HUSK, 80, 4, 4));

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, PricklyGrassFeature.GRASS_PLACED);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);

        return (new Biome.Builder())
                .temperatureModifier(Biome.TemperatureModifier.NONE)
                .downfall(0.1F)
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .precipitation(Biome.Precipitation.NONE)
                .temperature(2F)
                .category(Biome.Category.NONE)
                .effects(new BiomeEffects.Builder()
                        .grassColor(0xffff57)
                        .waterColor(0xff9757)
                        .waterFogColor(0xff9757)
                        .fogColor(0xc0d8ff)
                        .skyColor(0x3f76e4).build()).build();
    }

    public static void registerBiome() {
        Registry.register(BuiltinRegistries.BIOME, DEADLY_PLANTS_KEY.getValue(), DEADLY_PLANTS);
    }
}
