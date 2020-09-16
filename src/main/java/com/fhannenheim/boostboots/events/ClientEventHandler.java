package com.fhannenheim.boostboots.events;

import com.fhannenheim.boostboots.networking.Networking;
import com.fhannenheim.boostboots.networking.PacketDoBoost;
import com.fhannenheim.boostboots.util.BoostUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class ClientEventHandler {
    boolean onGroundLastTick;

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onTick(TickEvent event) {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;
        if (player != null)
            onGroundLastTick = player.onGround;
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onInput(InputEvent.KeyInputEvent event) {
        if (event.getKey() == 32 && event.getAction() == 1) {
            Minecraft mc = Minecraft.getInstance();
            ClientPlayerEntity player = mc.player;
            if (!onGroundLastTick && BoostUtils.hasBoostBoots(player)) {
                Networking.sendToServer(new PacketDoBoost());
            }
        }
    }


}
