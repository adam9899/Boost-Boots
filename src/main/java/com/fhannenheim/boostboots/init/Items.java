package com.fhannenheim.boostboots.init;

import com.fhannenheim.boostboots.BoostBoots;
import com.fhannenheim.boostboots.items.BoostBootsItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Items {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, BoostBoots.MOD_ID);

    public static final RegistryObject<Item> BOOST_BOOTS = ITEMS.register("boost_boots", () -> new BoostBootsItem(
            new Item.Properties().group(ItemGroup.TRANSPORTATION)
            , makeArmorMaterial()));


    public static IArmorMaterial makeArmorMaterial() {
        return new IArmorMaterial() {
            @Override
            public int getDurability(EquipmentSlotType slot) {
                return 50;
            }

            @Override
            public int getDamageReductionAmount(EquipmentSlotType slot) {
                return 0;
            }

            @Override
            public int getEnchantability() {
                return 0;
            }

            @Override
            public SoundEvent getSoundEvent() {
                return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
            }

            @Override
            public Ingredient getRepairMaterial() {
                return Ingredient.fromItems(net.minecraft.item.Items.GUNPOWDER);
            }

            @Override
            public String getName() {
                return "boostboots:boost_boots";
            }

            @Override
            public float getToughness() {
                return 1;
            }

        };
    }
}
