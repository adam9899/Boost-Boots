package com.fhannenheim.boostboots.events;

import com.fhannenheim.boostboots.BoostBoots;
import com.fhannenheim.boostboots.init.Items;
import com.fhannenheim.boostboots.networking.Networking;
import com.fhannenheim.boostboots.networking.PacketDoBoost;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public class ServerEventHandler {
    public Map<ServerPlayerEntity, Boolean> airBorneLastTick = new HashMap<>();
    public Map<ServerPlayerEntity, Boolean> flag = new HashMap<>();

    @SubscribeEvent
    public void onTick(TickEvent event) {
        BoostBoots.server.getPlayerList().getPlayers().forEach((player)->{
            airBorneLastTick.put(player,!player.isOnGround());
            if(player.isOnGround())
            {
                flag.put(player,false);
            }
        });
    }
}
