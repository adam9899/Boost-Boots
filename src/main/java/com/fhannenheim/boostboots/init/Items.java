package com.fhannenheim.boostboots.init;

import com.fhannenheim.boostboots.BoostBoots;
import com.fhannenheim.boostboots.items.BoostBootsItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Items {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, BoostBoots.MOD_ID);
    public static final RegistryObject<Item> BOOST_BOOTS = ITEMS.register("boost_boots", () -> new BoostBootsItem(
            new Item.Properties()
                    .group(ItemGroup.TRANSPORTATION)
    ));
}
