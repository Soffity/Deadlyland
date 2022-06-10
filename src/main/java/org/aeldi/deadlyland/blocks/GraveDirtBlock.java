package org.aeldi.deadlyland.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.apache.http.client.entity.EntityBuilder;

import java.util.Random;

public class GraveDirtBlock extends Block {
    public GraveDirtBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        Random r = world.getRandom();
        World sw = world.getServer().getWorld(World.OVERWORLD);
        if(r.nextFloat() > 0.0f){
            Entity en = new SkeletonEntity(EntityType.SKELETON, sw);
            en.setPosition(pos.getX(), pos.getY(), pos.getZ());
            sw.spawnEntity(en);
        }

    }
}
