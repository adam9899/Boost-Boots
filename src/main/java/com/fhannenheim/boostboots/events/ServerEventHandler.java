package com.fhannenheim.boostboots.events;

import com.fhannenheim.boostboots.BoostBoots;
import net.minecraft.entity.player.ServerPlayerEntity;
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
                player.abilities.allowFlying = false;
                flag.put(player, false);
            }
        });
    }
}
