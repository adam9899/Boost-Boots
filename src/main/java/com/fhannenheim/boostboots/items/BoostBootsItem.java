package com.fhannenheim.boostboots.items;

import com.fhannenheim.boostboots.BoostBoots;
import com.fhannenheim.boostboots.compat.CuriosCompat;
import com.fhannenheim.boostboots.model.BootModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.enchantment.IArmorVanishable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.ModList;

import javax.annotation.Nullable;

public class BoostBootsItem extends ArmorItem implements IArmorVanishable {

    public BoostBootsItem(Properties p_i48534_3_, IArmorMaterial material) {
        super(material, EquipmentSlotType.FEET, p_i48534_3_);
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

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused) {
        if (ModList.get() != null && ModList.get().getModContainerById("curios").isPresent()) {
            return CuriosCompat.initCapabilities();
        }
        return super.initCapabilities(stack, unused);
    }

}
