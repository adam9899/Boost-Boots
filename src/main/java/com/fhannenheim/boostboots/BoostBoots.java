package com.fhannenheim.boostboots;

import com.fhannenheim.boostboots.events.ClientEventHandler;
import com.fhannenheim.boostboots.events.ServerEventHandler;
import com.fhannenheim.boostboots.init.Items;
import com.fhannenheim.boostboots.networking.Networking;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("boostboots")
public class BoostBoots {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "boostboots";
    public static ServerEventHandler serverEventHandler;
    public static MinecraftServer server;

    public BoostBoots() {
        IEventBus modeventbus = FMLJavaModLoadingContext.get().getModEventBus();
        modeventbus.addListener(this::setup);
        Items.ITEMS.register(modeventbus);

        serverEventHandler = new ServerEventHandler();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }

    @SubscribeEvent
    public void FMLServerStartedEvent(FMLServerStartedEvent event) {
        event.getServer().setAllowFlight(true);
        LOGGER.info("Enabled Flight");

        server = event.getServer();
        MinecraftForge.EVENT_BUS.register(serverEventHandler);
    }

    @SubscribeEvent
    public void onRegisterModels(ModelRegistryEvent event){
        ModelLoader.addSpecialModel(new ResourceLocation(BoostBoots.MOD_ID,"item/boost_boots"));
    }

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event){
        if(event.getItemStack().getItem() == Items.BOOST_BOOTS.get()){
            event.getToolTip().remove(event.getToolTip().size()-1);
            event.getToolTip().remove(event.getToolTip().size()-2);
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        Networking.registerMessages();
    }
}
