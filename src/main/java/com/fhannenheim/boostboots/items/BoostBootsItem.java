package com.fhannenheim.boostboots.items;

import com.fhannenheim.boostboots.BoostBoots;
import com.fhannenheim.boostboots.model.BootModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class BoostBootsItem extends ArmorItem {

    public BoostBootsItem(Properties p_i48534_3_) {
        super(makeArmorMaterial(), EquipmentSlotType.FEET, p_i48534_3_);
    }


    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.GUNPOWDER;
    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public BipedModel getArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlotType slot, BipedModel _default) {
        return new BootModel();
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return type == null ? BoostBoots.MOD_ID + ":textures/armor/boots.png" : "";
    }

    @Nullable
    @Override
    public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
        return EquipmentSlotType.FEET;
    }


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
                return Ingredient.fromItems(Items.GUNPOWDER);
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
    }}
