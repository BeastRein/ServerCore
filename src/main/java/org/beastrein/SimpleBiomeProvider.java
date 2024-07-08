package org.beastrein;

import com.google.common.collect.Lists;
import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public class SimpleBiomeProvider extends BiomeProvider {

    @Override
    public Biome getBiome(WorldInfo worldInfo, int x, int y, int z) {
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(worldInfo.getSeed()), 6);
        generator.setScale(0.01);
        double noise = generator.noise(x, z, 1, 1, true);

        List<Biome> biomes = getBiomes(worldInfo);
        int noise1 = abs( (int) (noise * 10));
        return biomes.get(noise1 % biomes.size());
    }

    @Override
    public List<Biome> getBiomes(WorldInfo worldInfo) {
        return Lists.newArrayList(Biome.DRIPSTONE_CAVES, Biome.LUSH_CAVES, Biome.FOREST, Biome.PLAINS, Biome.DEEP_DARK, Biome.DEEP_DARK);
    }
}