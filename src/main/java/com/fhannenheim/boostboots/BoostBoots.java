package com.fhannenheim.boostboots;

import com.fhannenheim.boostboots.events.ClientEventHandler;
import com.fhannenheim.boostboots.events.ServerEventHandler;
import com.fhannenheim.boostboots.init.Items;
import com.fhannenheim.boostboots.networking.Networking;
import com.fhannenheim.boostboots.util.BoostUtils;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.imc.CurioIMCMessage;

import static com.fhannenheim.boostboots.BoostBoots.MOD_ID;

@Mod("boostboots")
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BoostBoots {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "boostboots";
    public static ServerEventHandler serverEventHandler;
    public static MinecraftServer server;

    public BoostBoots() {
        IEventBus modeventbus = FMLJavaModLoadingContext.get().getModEventBus();
        modeventbus.addListener(this::setup);
        Items.ITEMS.register(modeventbus);

        serverEventHandler = new ServerEventHandler();
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void InterModEnqueueEvent(InterModEnqueueEvent event) {
        if (BoostUtils.isCuriosLoaded()) {
            InterModComms.sendTo("curios", CuriosAPI.IMC.REGISTER_TYPE, () -> new CurioIMCMessage("feet"));
            InterModComms.sendTo("curios", CuriosAPI.IMC.REGISTER_ICON, () ->
                    new Tuple<>("feet", new ResourceLocation(MOD_ID, "item/boot_slot"))
            );
        }
        BoostBoots.LOGGER.info("IMC sent");
    }

    @SubscribeEvent
    public void FMLServerStartedEvent(FMLServerStartedEvent event) {
        server = event.getServer();
        MinecraftForge.EVENT_BUS.register(serverEventHandler);
    }

    private void setup(final FMLCommonSetupEvent event) {
        Networking.registerMessages();
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void stitchTextures(TextureStitchEvent.Pre evt) {
        if (evt.getMap().getTextureLocation() == PlayerContainer.LOCATION_BLOCKS_TEXTURE) {
            evt.addSprite(new ResourceLocation(MOD_ID, "item/boot_slot"));
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onRegisterModels(ModelRegistryEvent event) {
        ModelLoader.addSpecialModel(new ResourceLocation(MOD_ID, "item/boost_boots"));
    }

}
