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
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.aeldi.deadlyland.Deadlyland;

public class BurntPlants {
    public static final RegistryKey<Biome> BURNT_PLANTS_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(Deadlyland.mod_namespace, "burnt_plants"));
    public static final Biome BURNT_PLANTS = Burnt_plants();

    public static Biome Burnt_plants() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addMonsters(spawnSettings, 40, 5, 90, false);
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 80, 4, 4));

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        return (new Biome.Builder())
                .temperatureModifier(Biome.TemperatureModifier.NONE)
                .downfall(0.1F)
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .precipitation(Biome.Precipitation.NONE)
                .temperature(3F)
                .category(Biome.Category.NONE)
                .effects(new BiomeEffects.Builder()
                        .grassColor(0x424242)
                        .waterColor(0x757575)
                        .waterFogColor(0x0f0f0f)
                        .fogColor(0x0f0f0f)
                        .foliageColor(0x4f4f4f)
                        .skyColor(0x757575).build()).build();
    }

    public static void registerBiome() {
        Registry.register(BuiltinRegistries.BIOME, BURNT_PLANTS_KEY.getValue(), BURNT_PLANTS);
    }
}
