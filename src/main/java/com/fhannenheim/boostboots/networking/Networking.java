package com.fhannenheim.boostboots.networking;
import com.fhannenheim.boostboots.BoostBoots;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Networking {

    private static SimpleChannel INSTANCE;
    private static int ID = 0;

    private static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(BoostBoots.MOD_ID, "boots"),
                () -> "1.0",
                s -> true,
                s -> true);

        INSTANCE.messageBuilder(PacketDoBoost.class,nextID())
                .encoder((packetDoBoost, packetBuffer) -> {})
                .decoder(buf -> new PacketDoBoost())
                .consumer(PacketDoBoost::handle)
                .add();
    }

    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }
}