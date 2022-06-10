package org.aeldi.deadlyland.terra;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.SurfaceRuleManager;
import terrablender.worldgen.TBSurfaceRuleData;

import java.util.function.Consumer;

public class ModRegion extends Region {

    public ModRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    public static MaterialRules.MaterialRule surface() {
        return SurfaceRuleManager.getDefaultSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        this.addBiome(mapper,
                ParameterUtils.Temperature.WARM,
                ParameterUtils.Humidity.DRY,
                ParameterUtils.Continentalness.INLAND,
                ParameterUtils.Erosion.EROSION_0,
                ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING,
                ParameterUtils.Depth.SURFACE,
                1.0f,
                ModBiome.DEADLY_PLANTS_KEY);
    }
}
