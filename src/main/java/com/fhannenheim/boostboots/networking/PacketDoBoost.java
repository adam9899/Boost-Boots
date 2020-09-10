package com.fhannenheim.boostboots.networking;

import com.fhannenheim.boostboots.BoostBoots;
import com.fhannenheim.boostboots.events.ServerEventHandler;
import com.fhannenheim.boostboots.init.Items;
import com.ibm.icu.text.MessagePattern;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class PacketDoBoost {

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity serverplayer = ctx.get().getSender();
            if(serverplayer!= null){
                ServerEventHandler serverEventHandler = BoostBoots.serverEventHandler;
                if (serverEventHandler.airBorneLastTick.get(serverplayer) && !serverEventHandler.flag.get(serverplayer) && serverplayer.inventory.armorItemInSlot(0).getItem() == Items.BOOST_BOOTS.get()) {
                    float f1 = serverplayer.rotationYaw * ((float) Math.PI / 180F);
                    serverplayer.setVelocity((-MathHelper.sin(f1) * 0.2F), 0.6f, (MathHelper.cos(f1) * 0.2F));
                    serverplayer.velocityChanged = true;
                    serverplayer.world.playSound(null,serverplayer.getPosX(),serverplayer.getPosY(),serverplayer.getPosZ(), SoundEvents.ENTITY_FIREWORK_ROCKET_LAUNCH, SoundCategory.PLAYERS,1,1);
                    serverplayer.getServerWorld().spawnParticle(ParticleTypes.FIREWORK,serverplayer.getPosX(),serverplayer.getPosY(),serverplayer.getPosZ(),20,0.5F,0.5F,0.5F,0.01f);
                    serverEventHandler.flag.put(serverplayer,true);
                }
            }
        });
        return true;
    }
}
