package com.fhannenheim.boostboots.networking;

import com.fhannenheim.boostboots.BoostBoots;
import com.fhannenheim.boostboots.events.ServerEventHandler;
import com.fhannenheim.boostboots.init.Items;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.network.NetworkEvent;

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
                    serverplayer.world.playSound(null, serverplayer.getPosX(), serverplayer.getPosY(), serverplayer.getPosZ(), SoundEvents.ENTITY_FIREWORK_ROCKET_LAUNCH, SoundCategory.PLAYERS, 1, 1);
                    serverplayer.getServerWorld().spawnParticle(ParticleTypes.FIREWORK, serverplayer.getPosX(), serverplayer.getPosY(), serverplayer.getPosZ(), 20, 0.5F, 0.5F, 0.5F, 0.01f);
                    serverplayer.inventory.armorItemInSlot(0).damageItem(1, serverplayer, (playerEntity) -> {
                        playerEntity.playSound(SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1, 1);
                    });
                    serverEventHandler.flag.put(serverplayer, true);
                }
            }
        });
        return true;
    }
}
