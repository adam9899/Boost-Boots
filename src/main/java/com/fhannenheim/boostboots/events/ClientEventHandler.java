package com.fhannenheim.boostboots.events;

import com.fhannenheim.boostboots.BoostBoots;
import com.fhannenheim.boostboots.init.Items;
import com.fhannenheim.boostboots.networking.Networking;
import com.fhannenheim.boostboots.networking.PacketDoBoost;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientEventHandler {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onInput(InputEvent.KeyInputEvent event) {
        if (event.getKey() == 32 && event.getAction() == 1) {
            Minecraft mc = Minecraft.getInstance();
            ClientPlayerEntity player = mc.player;
            if (player.inventory.armorItemInSlot(0).getItem() == Items.BOOST_BOOTS.get()) {
                Networking.sendToServer(new PacketDoBoost());
                BoostBoots.LOGGER.info("SentBoostPacket");
            }
        }
    }
}
